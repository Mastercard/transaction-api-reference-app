package com.mastercard.developer.transactionapi.client.model;

import lombok.RequiredArgsConstructor;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.ResponseReversalResponseV02;
import org.openapitools.client.model.ReversalResponseV02Item;

import java.util.List;

/**
 * Adapter class for a Reversal response item.
 */
@RequiredArgsConstructor
public class ReversalResponseItem implements ResponseItem<ResponseReversalResponseV02> {

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
    public ResponseReversalResponseV02 getPayload() {
        return responseItem.getPayload();
    }

    @Override
    public List<Error> getErrors() {
        return responseItem.getErrors();
    }
}
