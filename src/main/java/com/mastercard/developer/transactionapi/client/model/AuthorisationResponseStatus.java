package com.mastercard.developer.transactionapi.client.model;

import lombok.RequiredArgsConstructor;
import org.openapitools.client.model.AuthorisationResponseV02Status;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.AuthorisationResponseAuthorisationResponseV02;

import java.util.List;

/**
 * Adapter class for an Authorisation response status.
 */
@RequiredArgsConstructor
public class AuthorisationResponseStatus implements ResponseStatus<AuthorisationResponseAuthorisationResponseV02> {

    private final AuthorisationResponseV02Status responseItem;

    @Override
    public String getCorrelationId() {
        return responseItem.getCorrelationId();
    }

    @Override
    public int getHttpStatus() {
        return responseItem.getHttpStatus();
    }

    @Override
    public AuthorisationResponseAuthorisationResponseV02 getPayload() {
        return responseItem.getPayload();
    }

    @Override
    public List<Error> getErrors() {
        return responseItem.getErrors();
    }
}
