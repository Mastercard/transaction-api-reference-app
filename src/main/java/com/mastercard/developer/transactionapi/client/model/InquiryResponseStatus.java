package com.mastercard.developer.transactionapi.client.model;

import lombok.RequiredArgsConstructor;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.FinancialReversalAdviceResponseV02Status;
import org.openapitools.client.model.InquiryResponseV01Status;
import org.openapitools.client.model.InquiryResponseInquiryResponseV01;
import org.openapitools.client.model.ReversalFinancialAdviceResponseReversalResponseV02;

import java.util.List;
import java.util.Optional;

/**
 * Adapter class for an Inquiry response status.
 */
@RequiredArgsConstructor
public class InquiryResponseStatus implements ResponseStatus<InquiryResponseInquiryResponseV01> {

    private final InquiryResponseV01Status responseItem;

    @Override
    public String getCorrelationId() {
        return responseItem.getCorrelationId();
    }

    @Override
    public String getCustomerContextKey() {
        return responseItem.getCustomerContextKey();
    }

    @Override
    public int getHttpStatus() {
        return responseItem.getHttpStatus();
    }

    @Override
    public InquiryResponseInquiryResponseV01 getPayload() {
        return responseItem.getPayload();
    }

    @Override
    public List<Error> getErrors() {
        return responseItem.getErrors();
    }

    public String getPayloadAsJson() {
        return Optional.ofNullable(responseItem)
                .map(InquiryResponseV01Status::getPayload)
                .map(InquiryResponseInquiryResponseV01::toJson)
                .orElse(null);
    }
}
