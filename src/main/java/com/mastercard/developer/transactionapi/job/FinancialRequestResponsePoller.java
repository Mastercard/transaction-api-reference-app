package com.mastercard.developer.transactionapi.job;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.enums.FlowType;
import org.openapitools.client.model.FinancialRequestResponseFinancialResponseV02;
import org.springframework.stereotype.Service;

/**
 * Polls for financial request responses from the Transaction API.
 */
@Service
public class FinancialRequestResponsePoller extends ResponsePoller<FinancialRequestResponseFinancialResponseV02> {

    public FinancialRequestResponsePoller(RequestContextManager requestContextManager, TransactionApiClient transactionApiClient, TransactionApiProperties transactionApiProperties) {
        super(FlowType.FINANCIAL_REQUEST, requestContextManager, transactionApiClient, transactionApiProperties);
    }

    @Override
    protected BatchResponse<FinancialRequestResponseFinancialResponseV02> getResponses() {
        return getTransactionApiClient().getFinancialRequestResponses();
    }

    @Override
    protected String toJson(FinancialRequestResponseFinancialResponseV02 payload) {
        return payload.toJson();
    }

}
