package com.mastercard.developer.transactionapi.client.model;

import lombok.RequiredArgsConstructor;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.ReversalResponseV02Item;
import org.openapitools.client.model.ReversalresponseReversalResponseV02;

import java.util.List;

/**
 * Adapter class for a Reversal response item.
 */
@RequiredArgsConstructor
public class ReversalResponseItem implements ResponseItem<ReversalresponseReversalResponseV02> {

    private final ReversalResponseV02Item responseItem;

    @Override
    public String getCorrelationId() {
        return responseItem.getCorrelationId();
    }

    @Override
    public int getHttpStatus() {
        return responseItem.getHttpStatus();
    }

    @Override
    public ReversalresponseReversalResponseV02 getPayload() {
        return responseItem.getPayload();
    }

    @Override
    public List<Error> getErrors() {
        return responseItem.getErrors();
    }
}
