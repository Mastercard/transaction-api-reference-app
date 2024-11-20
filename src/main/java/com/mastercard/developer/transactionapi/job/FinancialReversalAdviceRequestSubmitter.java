package com.mastercard.developer.transactionapi.job;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.enums.FlowType;
import com.mastercard.developer.transactionapi.example.RequestExampleGenerator;
import com.mastercard.developer.transactionapi.utils.ClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.model.ReversalFinancialAdviceInitiationReversalInitiationV02;
import org.springframework.stereotype.Service;

/**
 * Emulates a stream of financial reversal advice for the Transaction API.
 */
@Slf4j
@Service
public class FinancialReversalAdviceRequestSubmitter extends RequestSubmitter {

    public FinancialReversalAdviceRequestSubmitter(TransactionApiClient transactionApiClient, RequestContextManager requestContextManager, RequestExampleGenerator requestExampleGenerator, TransactionApiProperties transactionApiProperties) {
        super(transactionApiClient, requestContextManager, requestExampleGenerator, transactionApiProperties);
    }

    /**
     * Submits a financial reversal advice.
     */
    @Override
    protected void submitRequest() {
        ReversalFinancialAdviceInitiationReversalInitiationV02 request = getRequestExampleGenerator().buildFinancialReversalAdvice();

        String correlationId = getTransactionApiClient().submitFinancialReversalAdvice(request);

        log.info("Submitted financial reversal advice request with correlationId={}: {}", correlationId, ClientUtils.convertPayloadForLogging(request.toJson(), getTransactionApiProperties().isPayloadLoggingEnabled()));

        getRequestContextManager().onRequestSent(FlowType.FINANCIAL_REVERSAL_ADVICE, correlationId, request);
    }
}
