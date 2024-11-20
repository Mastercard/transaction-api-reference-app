package com.mastercard.developer.transactionapi.job;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.enums.FlowType;
import com.mastercard.developer.transactionapi.example.RequestExampleGenerator;
import com.mastercard.developer.transactionapi.utils.ClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.model.FinancialRequestInitiationFinancialInitiationV02;
import org.springframework.stereotype.Service;

/**
 * Emulates a stream of financial requests for the Transaction API.
 */
@Slf4j
@Service
public class FinancialRequestSubmitter extends RequestSubmitter {

    public FinancialRequestSubmitter(TransactionApiClient transactionApiClient, RequestContextManager requestContextManager, RequestExampleGenerator requestExampleGenerator, TransactionApiProperties transactionApiProperties) {
        super(transactionApiClient, requestContextManager, requestExampleGenerator, transactionApiProperties);
    }

    /**
     * Submits a financial request.
     */
    @Override
    protected void submitRequest() {
        FinancialRequestInitiationFinancialInitiationV02 request = getRequestExampleGenerator().buildFinancialRequest();

        String correlationId = getTransactionApiClient().submitFinancialRequest(request);

        log.info("Submitted financial request with correlationId={}: {}", correlationId, ClientUtils.convertPayloadForLogging(request.toJson(), getTransactionApiProperties().isPayloadLoggingEnabled()));

        getRequestContextManager().onRequestSent(FlowType.FINANCIAL_REQUEST, correlationId, request);
    }
}
