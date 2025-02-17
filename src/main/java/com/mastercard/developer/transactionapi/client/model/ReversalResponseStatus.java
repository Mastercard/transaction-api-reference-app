package com.mastercard.developer.transactionapi.client.model;

import lombok.RequiredArgsConstructor;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.InquiryResponseInquiryResponseV01;
import org.openapitools.client.model.InquiryResponseV01Status;
import org.openapitools.client.model.ReversalResponseReversalResponseV02;
import org.openapitools.client.model.ReversalResponseV02Status;

import java.util.List;
import java.util.Optional;

/**
 * Adapter class for a Reversal response status.
 */
@RequiredArgsConstructor
public class ReversalResponseStatus implements ResponseStatus<ReversalResponseReversalResponseV02> {

    private final ReversalResponseV02Status responseItem;

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
    public ReversalResponseReversalResponseV02 getPayload() {
        return responseItem.getPayload();
    }

    @Override
    public List<Error> getErrors() {
        return responseItem.getErrors();
    }

    public String getPayloadAsJson() {
        return Optional.ofNullable(responseItem)
                .map(ReversalResponseV02Status::getPayload)
                .map(ReversalResponseReversalResponseV02::toJson)
                .orElse(null);
    }
}
