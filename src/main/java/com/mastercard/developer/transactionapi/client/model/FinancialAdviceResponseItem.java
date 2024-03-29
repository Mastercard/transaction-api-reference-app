package com.mastercard.developer.transactionapi.client.model;

import lombok.RequiredArgsConstructor;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.FinancialAdviceResponseV02Item;
import org.openapitools.client.model.FinancialresponseFinancialResponseV02;

import java.util.List;

/**
 * Adapter class for a Financial Advice response item.
 */
@RequiredArgsConstructor
public class FinancialAdviceResponseItem implements ResponseItem<FinancialresponseFinancialResponseV02> {

    private final FinancialAdviceResponseV02Item responseItem;

    @Override
    public String getCorrelationId() {
        return responseItem.getCorrelationId();
    }

    @Override
    public int getHttpStatus() {
        return responseItem.getHttpStatus();
    }

    @Override
    public FinancialresponseFinancialResponseV02 getPayload() {
        return responseItem.getPayload();
    }

    @Override
    public List<Error> getErrors() {
        return responseItem.getErrors();
    }
}
