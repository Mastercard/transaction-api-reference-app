package com.mastercard.developer.transactionapi.job.poller;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.enums.FlowType;
import com.mastercard.developer.transactionapi.job.processor.ResponseProcessor;
import org.openapitools.client.model.ReversalFinancialAdviceResponseReversalResponseV02;
import org.springframework.stereotype.Service;

/**
 * Polls for financial reversal advice responses from the Transaction API.
 */
@Service
public class FinancialReversalAdviceResponsePoller extends ResponsePoller<ReversalFinancialAdviceResponseReversalResponseV02> {

    public FinancialReversalAdviceResponsePoller(RequestContextManager requestContextManager,
                                                 TransactionApiClient transactionApiClient,
                                                 ResponseProcessor responseProcessor) {
        super(FlowType.FINANCIAL_REVERSAL_ADVICE, requestContextManager, transactionApiClient, responseProcessor);
    }

    @Override
    protected BatchResponse<ReversalFinancialAdviceResponseReversalResponseV02> getResponses() {
        return getTransactionApiClient().getFinancialReversalAdviceResponses();
    }
}
