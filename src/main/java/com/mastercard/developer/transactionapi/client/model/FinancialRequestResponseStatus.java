package com.mastercard.developer.transactionapi.client.model;

import lombok.RequiredArgsConstructor;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.FinancialAdviceResponseV02Status;
import org.openapitools.client.model.FinancialRequestResponseFinancialResponseV02;
import org.openapitools.client.model.FinancialRequestResponseV02Status;
import org.openapitools.client.model.FinancialResponseFinancialResponseV02;

import java.util.List;
import java.util.Optional;

/**
 * Adapter class for a Financial Request response status.
 */
@RequiredArgsConstructor
public class FinancialRequestResponseStatus implements ResponseStatus<FinancialRequestResponseFinancialResponseV02> {

    private final FinancialRequestResponseV02Status responseItem;

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
    public FinancialRequestResponseFinancialResponseV02 getPayload() {
        return responseItem.getPayload();
    }

    @Override
    public List<Error> getErrors() {
        return responseItem.getErrors();
    }

    public String getPayloadAsJson() {
        return Optional.ofNullable(responseItem)
                .map(FinancialRequestResponseV02Status::getPayload)
                .map(FinancialRequestResponseFinancialResponseV02::toJson)
                .orElse(null);
    }
}
