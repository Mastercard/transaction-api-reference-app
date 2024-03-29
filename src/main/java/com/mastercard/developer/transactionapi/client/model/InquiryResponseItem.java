package com.mastercard.developer.transactionapi.client.model;

import lombok.RequiredArgsConstructor;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.InquiryResponseV01Item;
import org.openapitools.client.model.InquiryresponseInquiryResponseV01;

import java.util.List;

/**
 * Adapter class for an Inquiry response item.
 */
@RequiredArgsConstructor
public class InquiryResponseItem implements ResponseItem<InquiryresponseInquiryResponseV01> {

    private final InquiryResponseV01Item responseItem;

    @Override
    public String getCorrelationId() {
        return responseItem.getCorrelationId();
    }

    @Override
    public int getHttpStatus() {
        return responseItem.getHttpStatus();
    }

    @Override
    public InquiryresponseInquiryResponseV01 getPayload() {
        return responseItem.getPayload();
    }

    @Override
    public List<Error> getErrors() {
        return responseItem.getErrors();
    }
}
