package com.mastercard.developer.transactionapi.job.submitter;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.ResponseStatus;
import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.enums.FlowType;
import com.mastercard.developer.transactionapi.example.RequestExampleGenerator;
import com.mastercard.developer.transactionapi.exception.TransactionApiException;
import com.mastercard.developer.transactionapi.job.processor.ResponseProcessor;
import com.mastercard.developer.transactionapi.utils.ClientUtils;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Random;
import java.util.UUID;

/**
 * Submits requests to the Transaction API.
 */
@RequiredArgsConstructor
public abstract class RequestSubmitter<R,P> {

    private final Logger log = LoggerFactory.getLogger(getClass());     // use child class logger

    private final FlowType flowType;
    private final TransactionApiClient transactionApiClient;
    private final RequestContextManager requestContextManager;
    private final RequestExampleGenerator requestExampleGenerator;
    private final TransactionApiProperties transactionApiProperties;
    private final ResponseProcessor responseProcessor;

    private final Random random = new Random(); // NOSONAR used for test data only

    protected TransactionApiClient getTransactionApiClient() {
        return transactionApiClient;
    }

    protected RequestExampleGenerator getRequestExampleGenerator() {
        return requestExampleGenerator;
    }

    /**
     * Submits the given number of requests to the Transaction API with random delays.
     *
     * @param count number of requests to submit
     */
    public void submitRequests(int count) {
        for (int i = 0; i < count; i++) {
            // random delay between requests to emulate a real stream of requests
            ClientUtils.sleep(Duration.ofMillis(random.nextInt(1000)));

            String customerContextKey = getRandomCustomerContextKey();
            try {
                R request = generateRequest();
                log.info("Submitting {} request with customerContextKey={}: {}", flowType, customerContextKey, ClientUtils
                        .convertPayloadForLogging(requestToJson(request), transactionApiProperties.isPayloadLoggingEnabled()));

                ApiResponse<P> response = submitRequest(customerContextKey, request);

                if (response.getStatusCode() == HttpStatus.SC_ACCEPTED) {
                    requestContextManager.onRequestSent(flowType, customerContextKey, request);
                } else {
                    responseProcessor.processResponseStatus(
                            toResponseStatus(customerContextKey, response), flowType);
                }

            } catch (Exception e) {
                if(e instanceof TransactionApiException && e.getCause() instanceof ApiException){
                    responseProcessor.processResponseStatus(
                            toResponseStatus(customerContextKey, (ApiException) e.getCause()), flowType);
                }else{
                    log.error("Failed to submit a request", e);
                }
            }
        }
    }

    /**
     * Get random Customer Context Key
     */
    protected String getRandomCustomerContextKey(){
        return UUID.randomUUID().toString();
    }

    /**
     * Submits a single request.
     */
    protected abstract ApiResponse<P> submitRequest(String customerContextKey, R request);

    /**
     * Converts OpenAPI ApiResponse to ResponseStatus
     */
    protected abstract ResponseStatus<P> toResponseStatus(String customerContextKey, ApiResponse<P> response);

    /**
     * Converts OpenAPI ApiException to ResponseStatus
     */
    protected abstract ResponseStatus<P> toResponseStatus(String customerContextKey, ApiException apiException);

    /**
     * Generate request payload for transaction
     */
    protected abstract R generateRequest();

    /**
     * Generate request payload for transaction
     */
    protected abstract String requestToJson(R request);
}
