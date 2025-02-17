package com.mastercard.developer.transactionapi.job.poller;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.enums.FlowType;
import com.mastercard.developer.transactionapi.job.processor.ResponseProcessor;
import org.openapitools.client.model.ReversalResponseReversalResponseV02;
import org.springframework.stereotype.Service;

/**
 * Polls for reversal responses from the Transaction API.
 */
@Service
public class ReversalResponsePoller extends ResponsePoller<ReversalResponseReversalResponseV02> {

    public ReversalResponsePoller(RequestContextManager requestContextManager,
                                  TransactionApiClient transactionApiClient,
                                  ResponseProcessor responseProcessor) {
        super(FlowType.REVERSAL, requestContextManager, transactionApiClient, responseProcessor);
    }

    @Override
    protected BatchResponse<ReversalResponseReversalResponseV02> getResponses() {
        return getTransactionApiClient().getReversalResponses();
    }
}
