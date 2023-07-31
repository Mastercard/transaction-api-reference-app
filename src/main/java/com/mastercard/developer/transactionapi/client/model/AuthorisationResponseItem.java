package com.mastercard.developer.transactionapi.client.model;

import lombok.RequiredArgsConstructor;
import org.openapitools.client.model.AuthorisationResponseV02Item;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.ResponseAuthorisationResponseV02;

import java.util.List;

/**
 * Adapter class for an Authorisation response item.
 */
@RequiredArgsConstructor
public class AuthorisationResponseItem implements ResponseItem<ResponseAuthorisationResponseV02> {

    private final AuthorisationResponseV02Item responseItem;

    @Override
    public String getCorrelationId() {
        return responseItem.getCorrelationId();
    }

    @Override
    public int getHttpStatus() {
        return responseItem.getHttpStatus();
    }

    @Override
    public ResponseAuthorisationResponseV02 getPayload() {
        return responseItem.getPayload();
    }

    @Override
    public List<Error> getErrors() {
        return responseItem.getErrors();
    }
}
