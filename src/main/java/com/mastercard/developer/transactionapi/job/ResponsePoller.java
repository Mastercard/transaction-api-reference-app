package com.mastercard.developer.transactionapi.job;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import com.mastercard.developer.transactionapi.client.model.ResponseStatus;
import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.enums.FlowType;
import com.mastercard.developer.transactionapi.exception.TransactionApiException;
import com.mastercard.developer.transactionapi.utils.ClientUtils;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * Polls for responses of a particular type from the Transaction API.
 *
 * @param <P> response status payload type
 */
@RequiredArgsConstructor
public abstract class ResponsePoller<P> {

    private static final Duration WAIT_FOR_REQUESTS_DURATION = Duration.ofMillis(250);
    private static final Duration FAILURE_RECOVERY_DURATION = Duration.ofMillis(1000);

    private final Logger log = LoggerFactory.getLogger(getClass());     // use child class logger

    private final FlowType flowType;
    private final RequestContextManager requestContextManager;
    private final TransactionApiClient transactionApiClient;

    private final TransactionApiProperties transactionApiProperties;

    private volatile boolean canRun = true;

    protected abstract BatchResponse<P> getResponses();

    protected TransactionApiClient getTransactionApiClient() {
        return transactionApiClient;
    }


    /**
     * Saves results of the last poll.
     */
    public static class LastPollResults {
        boolean hasMore = false;
        Duration retryAfter = Duration.ZERO;
    }

    /**
     * Stops the polling.
     */
    public void stop() {
        log.info("Poller is stopped");
        canRun = false;
    }

    /**
     * Starts the polling.
     */
    public void run() {
        try {
            log.info("Poller started");
            LastPollResults lastPollResults = new LastPollResults();

            while (canRun) {
                pollOnce(lastPollResults);
            }

        } catch (Exception ex) {
            log.error("Poller terminated", ex);
        }
    }

    /**
     * Polls for responses.
     */
    void pollOnce(LastPollResults lastPollResults) {
        Duration nowNeedToWait;
        boolean nowCanGet;

        if (lastPollResults.hasMore) {
            // if we were told the last time that more responses await already, then we can retry immediately
            nowNeedToWait = Duration.ZERO;
            nowCanGet = true;
        } else if (requestContextManager.haveOngoingRequests(flowType)) {
            // if no responses now but more expected, then wait for the required delay before polling again
            nowNeedToWait = lastPollResults.retryAfter;
            nowCanGet = true;
        } else {
            // if no ongoing requests at the moment then no need to poll, just wait till requests are sent
            nowNeedToWait = WAIT_FOR_REQUESTS_DURATION;
            nowCanGet = false;
        }

        ClientUtils.sleep(nowNeedToWait);
        if (nowCanGet) {
            try {
                // poll for responses
                BatchResponse<P> batch = getResponses();

                // process received responses
                for (ResponseStatus<P> item : batch.getItems()) {
                    processResponseStatus(item);
                }

                // save the results
                lastPollResults.hasMore = batch.isHasMore();
                lastPollResults.retryAfter = batch.getRetryAfter();

            } catch (TransactionApiException ex) {
                log.error("Failed to get {} responses; Cause: {}", flowType, ex.getMessage(), ex);
                lastPollResults.hasMore = false;
                lastPollResults.retryAfter = FAILURE_RECOVERY_DURATION;
            }
        }
    }

    /**
     * Processes a received response status.
     */
    void processResponseStatus(ResponseStatus<P> item) {


        if (item.getPayload() != null) {
            String payloadString = ClientUtils.convertPayloadForLogging(toJson(item.getPayload()), transactionApiProperties.isPayloadLoggingEnabled());

            if (item.getHttpStatus() == HttpStatus.SC_OK) {
                log.info("Received successful {} response with correlationId={}: {}",
                        flowType, item.getCorrelationId(), payloadString);
            } else {
                log.error("Received unsuccessful {} response with correlationId={}, httpStatus={}: {}",
                        flowType, item.getCorrelationId(), item.getHttpStatus(), payloadString);
            }
        } else {
            log.error("Received failed {} response with correlationId={}, httpStatus={}, errors={}",
                    flowType, item.getCorrelationId(), item.getHttpStatus(), item.getErrors());
        }
        requestContextManager.onResponseReceived(flowType, item.getCorrelationId());
    }

    /**
     * Abstract class converts payload to Json
     */
    protected abstract String toJson(P payload);

}
