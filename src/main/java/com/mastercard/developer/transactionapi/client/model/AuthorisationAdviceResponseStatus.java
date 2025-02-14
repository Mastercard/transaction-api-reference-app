package com.mastercard.developer.transactionapi.client.model;

import lombok.RequiredArgsConstructor;
import org.openapitools.client.model.AuthorisationAdviceResponseV02Status;
import org.openapitools.client.model.AuthorisationResponseAuthorisationResponseV02;
import org.openapitools.client.model.AuthorisationResponseV02Status;
import org.openapitools.client.model.Error;

import java.util.List;
import java.util.Optional;

/**
 * Adapter class for an Authorisation Advice response status.
 */
@RequiredArgsConstructor
public class AuthorisationAdviceResponseStatus implements ResponseStatus<AuthorisationResponseAuthorisationResponseV02> {

    private final AuthorisationAdviceResponseV02Status responseItem;

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
    public AuthorisationResponseAuthorisationResponseV02 getPayload() {
        return responseItem.getPayload();
    }

    @Override
    public List<Error> getErrors() {
        return responseItem.getErrors();
    }

    public String getPayloadAsJson() {
        return Optional.ofNullable(responseItem)
                .map(AuthorisationAdviceResponseV02Status::getPayload)
                .map(AuthorisationResponseAuthorisationResponseV02::toJson)
                .orElse(null);
    }
}
