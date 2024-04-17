package com.mastercard.developer.transactionapi.job;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.enums.FlowType;
import com.mastercard.developer.transactionapi.example.RequestExampleGenerator;
import com.mastercard.developer.transactionapi.utils.ClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.model.ReversalInitiationReversalInitiationV02;
import org.springframework.stereotype.Service;

/**
 * Emulates a stream of reversal requests for the Transaction API.
 */
@Slf4j
@Service
public class ReversalRequestSubmitter extends RequestSubmitter {

    public ReversalRequestSubmitter(TransactionApiClient transactionApiClient, RequestContextManager requestContextManager, RequestExampleGenerator requestExampleGenerator, TransactionApiProperties transactionApiProperties) {
        super(transactionApiClient, requestContextManager, requestExampleGenerator, transactionApiProperties);
    }

    /**
     * Submits a reversal request.
     */
    @Override
    protected void submitRequest() {
        ReversalInitiationReversalInitiationV02 request = getRequestExampleGenerator().buildReversalRequest();
        String correlationId = getTransactionApiClient().submitReversalRequest(request);

        log.info("Submitted reversal request with correlationId={}: {}", correlationId, ClientUtils.convertPayloadForLogging(request.toJson(), getTransactionApiProperties().isPayloadLoggingEnabled()));

        getRequestContextManager().onRequestSent(FlowType.REVERSAL, correlationId, request);
    }
}
