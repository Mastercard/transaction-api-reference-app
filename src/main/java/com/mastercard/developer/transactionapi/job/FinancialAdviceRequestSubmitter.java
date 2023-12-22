package com.mastercard.developer.transactionapi.job;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.enums.FlowType;
import com.mastercard.developer.transactionapi.example.RequestExampleGenerator;
import com.mastercard.developer.transactionapi.utils.ClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.model.FinancialinitiationFinancialInitiationV02;
import org.springframework.stereotype.Service;

/**
 * Emulates a stream of financial advice requests for the Transaction API.
 */
@Slf4j
@Service
public class FinancialAdviceRequestSubmitter extends RequestSubmitter {

    public FinancialAdviceRequestSubmitter(TransactionApiClient transactionApiClient, RequestContextManager requestContextManager, RequestExampleGenerator requestExampleGenerator, TransactionApiProperties transactionApiProperties) {
        super(transactionApiClient, requestContextManager, requestExampleGenerator, transactionApiProperties);
    }

    /**
     * Submits a financial advice.
     */
    @Override
    protected void submitRequest() {
        FinancialinitiationFinancialInitiationV02 request = getRequestExampleGenerator().buildFinancialAdviceRequest();

        String correlationId = getTransactionApiClient().submitFinancialAdviceRequest(request);

        log.info("Submitted financial advice request with correlationId={}: {}", correlationId, ClientUtils.convertPayloadForLogging(request.toJson(), getTransactionApiProperties().isPayloadLoggingEnabled()));

        getRequestContextManager().onRequestSent(FlowType.FINANCIAL_ADVICE, correlationId, request);
    }
}
