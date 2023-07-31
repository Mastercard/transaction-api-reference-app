package com.mastercard.developer.transactionapi.job;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.enums.FlowType;
import org.openapitools.client.model.ResponseInquiryResponseV01;
import org.springframework.stereotype.Service;

/**
 * Polls for inquiry responses from the Transaction API.
 */
@Service
public class InquiryResponsePoller extends ResponsePoller<ResponseInquiryResponseV01> {

    public InquiryResponsePoller(RequestContextManager requestContextManager, TransactionApiClient transactionApiClient, TransactionApiProperties transactionApiProperties) {
        super(FlowType.INQUIRY, requestContextManager, transactionApiClient, transactionApiProperties);
    }

    @Override
    protected BatchResponse<ResponseInquiryResponseV01> getResponses() {
        return getTransactionApiClient().getInquiryResponses();
    }

    @Override
    protected String toJson(ResponseInquiryResponseV01 payload) {
        return payload.toJson();
    }

}
