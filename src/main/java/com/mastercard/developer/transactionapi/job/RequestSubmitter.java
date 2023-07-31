package com.mastercard.developer.transactionapi.job;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.example.RequestExampleGenerator;
import com.mastercard.developer.transactionapi.utils.ClientUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Random;

/**
 * Submits requests to the Transaction API.
 */
@RequiredArgsConstructor
public abstract class RequestSubmitter {

    private final Logger log = LoggerFactory.getLogger(getClass());     // use child class logger

    private final TransactionApiClient transactionApiClient;
    private final RequestContextManager requestContextManager;
    private final RequestExampleGenerator requestExampleGenerator;
    private final TransactionApiProperties transactionApiProperties;

    private final Random random = new Random(); // NOSONAR used for test data only

    protected TransactionApiClient getTransactionApiClient() {
        return transactionApiClient;
    }

    protected RequestContextManager getRequestContextManager() {
        return requestContextManager;
    }

    protected RequestExampleGenerator getRequestExampleGenerator() {
        return requestExampleGenerator;
    }

    protected TransactionApiProperties getTransactionApiProperties() {
        return transactionApiProperties;
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

            try {
                submitRequest();
            } catch (Exception ex) {
                log.error("Failed to submit a request", ex);
            }
        }
    }

    /**
     * Submits a single request.
     */
    protected abstract void submitRequest();
}
