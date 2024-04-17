package com.mastercard.developer.transactionapi.job;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.enums.FlowType;
import com.mastercard.developer.transactionapi.example.RequestExampleGenerator;
import com.mastercard.developer.transactionapi.utils.ClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.model.AuthorisationInitiationAuthorisationInitiationV02;
import org.springframework.stereotype.Service;

/**
 * Emulates a stream of authorisation requests for the Transaction API.
 */
@Slf4j
@Service
public class AuthorisationRequestSubmitter extends RequestSubmitter {

    public AuthorisationRequestSubmitter(TransactionApiClient transactionApiClient, RequestContextManager requestContextManager, RequestExampleGenerator requestExampleGenerator, TransactionApiProperties transactionApiProperties) {
        super(transactionApiClient, requestContextManager, requestExampleGenerator, transactionApiProperties);
    }

    /**
     * Submits an authorisation request.
     */
    @Override
    protected void submitRequest() {
        AuthorisationInitiationAuthorisationInitiationV02 request = getRequestExampleGenerator().buildAuthorisationRequest();

        String correlationId = getTransactionApiClient().submitAuthorisationRequest(request);

        log.info("Submitted authorisation request with correlationId={}: {}", correlationId, ClientUtils.convertPayloadForLogging(request.toJson(), getTransactionApiProperties().isPayloadLoggingEnabled()));

        getRequestContextManager().onRequestSent(FlowType.AUTHORISATION, correlationId, request);
    }
}
