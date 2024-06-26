package com.mastercard.developer.transactionapi.job;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.enums.FlowType;
import org.openapitools.client.model.FinancialResponseFinancialResponseV02;
import org.springframework.stereotype.Service;

/**
 * Polls for financial advice responses from the Transaction API.
 */
@Service
public class FinancialAdviceResponsePoller extends ResponsePoller<FinancialResponseFinancialResponseV02> {

    public FinancialAdviceResponsePoller(RequestContextManager requestContextManager, TransactionApiClient transactionApiClient, TransactionApiProperties transactionApiProperties) {
        super(FlowType.FINANCIAL_ADVICE, requestContextManager, transactionApiClient, transactionApiProperties);
    }

    @Override
    protected BatchResponse<FinancialResponseFinancialResponseV02> getResponses() {
        return getTransactionApiClient().getFinancialAdviceResponses();
    }

    @Override
    protected String toJson(FinancialResponseFinancialResponseV02 payload) {
        return payload.toJson();
    }

}
