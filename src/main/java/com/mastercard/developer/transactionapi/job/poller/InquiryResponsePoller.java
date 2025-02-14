package com.mastercard.developer.transactionapi.job.poller;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.enums.FlowType;
import com.mastercard.developer.transactionapi.job.processor.ResponseProcessor;
import org.openapitools.client.model.InquiryResponseInquiryResponseV01;
import org.springframework.stereotype.Service;

/**
 * Polls for inquiry responses from the Transaction API.
 */
@Service
public class InquiryResponsePoller extends ResponsePoller<InquiryResponseInquiryResponseV01> {

    public InquiryResponsePoller(RequestContextManager requestContextManager,
                                 TransactionApiClient transactionApiClient,
                                 ResponseProcessor responseProcessor) {
        super(FlowType.INQUIRY, requestContextManager, transactionApiClient, responseProcessor);
    }

    @Override
    protected BatchResponse<InquiryResponseInquiryResponseV01> getResponses() {
        return getTransactionApiClient().getInquiryResponses();
    }
}
