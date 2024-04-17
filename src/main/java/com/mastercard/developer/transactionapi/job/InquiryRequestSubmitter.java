package com.mastercard.developer.transactionapi.job;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.enums.FlowType;
import com.mastercard.developer.transactionapi.example.RequestExampleGenerator;
import com.mastercard.developer.transactionapi.utils.ClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.model.InquiryInitiationInquiryInitiationV01;
import org.springframework.stereotype.Service;

/**
 * Emulates a stream of inquiry requests for the Transaction API.
 */
@Slf4j
@Service
public class InquiryRequestSubmitter extends RequestSubmitter {

    public InquiryRequestSubmitter(TransactionApiClient transactionApiClient, RequestContextManager requestContextManager, RequestExampleGenerator requestExampleGenerator, TransactionApiProperties transactionApiProperties) {
        super(transactionApiClient, requestContextManager, requestExampleGenerator, transactionApiProperties);
    }

    /**
     * Submits an inquiry request.
     */
    @Override
    protected void submitRequest() {
        InquiryInitiationInquiryInitiationV01 request = getRequestExampleGenerator().buildInquiryRequest();

        String correlationId = getTransactionApiClient().submitInquiryRequest(request);

        log.info("Submitted inquiry request with correlationId={}: {}", correlationId, ClientUtils.convertPayloadForLogging(request.toJson(), getTransactionApiProperties().isPayloadLoggingEnabled()));

        getRequestContextManager().onRequestSent(FlowType.INQUIRY, correlationId, request);
    }
}
