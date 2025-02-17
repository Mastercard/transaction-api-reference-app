package com.mastercard.developer.transactionapi.job.poller;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.enums.FlowType;
import com.mastercard.developer.transactionapi.job.processor.ResponseProcessor;
import org.openapitools.client.model.FinancialRequestResponseFinancialResponseV02;
import org.springframework.stereotype.Service;

/**
 * Polls for financial request responses from the Transaction API.
 */
@Service
public class FinancialRequestResponsePoller extends ResponsePoller<FinancialRequestResponseFinancialResponseV02> {

    public FinancialRequestResponsePoller(RequestContextManager requestContextManager,
                                          TransactionApiClient transactionApiClient,
                                          ResponseProcessor responseProcessor) {
        super(FlowType.FINANCIAL_REQUEST, requestContextManager, transactionApiClient, responseProcessor);
    }

    @Override
    protected BatchResponse<FinancialRequestResponseFinancialResponseV02> getResponses() {
        return getTransactionApiClient().getFinancialRequestResponses();
    }
}
