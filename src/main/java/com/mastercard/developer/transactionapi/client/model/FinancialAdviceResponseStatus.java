package com.mastercard.developer.transactionapi.client.model;

import lombok.RequiredArgsConstructor;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.FinancialAdviceResponseV02Status;
import org.openapitools.client.model.FinancialResponseFinancialResponseV02;

import java.util.List;

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
}
