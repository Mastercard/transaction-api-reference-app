package com.mastercard.developer.transactionapi.client.model;

import lombok.RequiredArgsConstructor;
import org.openapitools.client.model.AuthorisationResponseAuthorisationResponseV02;
import org.openapitools.client.model.AuthorisationResponseV02Status;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.FinancialAdviceResponseV02Status;
import org.openapitools.client.model.FinancialRequestResponseV02Status;
import org.openapitools.client.model.FinancialResponseFinancialResponseV02;

import java.util.List;
import java.util.Optional;

/**
 * Adapter class for a Financial Advice response status.
 */
@RequiredArgsConstructor
public class FinancialAdviceResponseStatus implements ResponseStatus<FinancialResponseFinancialResponseV02> {

    private final FinancialAdviceResponseV02Status responseItem;

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
    public FinancialResponseFinancialResponseV02 getPayload() {
        return responseItem.getPayload();
    }

    @Override
    public List<Error> getErrors() {
        return responseItem.getErrors();
    }

    public String getPayloadAsJson() {
        return Optional.ofNullable(responseItem)
                .map(FinancialAdviceResponseV02Status::getPayload)
                .map(FinancialResponseFinancialResponseV02::toJson)
                .orElse(null);
    }
}
