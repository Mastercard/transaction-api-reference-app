package com.mastercard.developer.transactionapi.client.model;

import lombok.RequiredArgsConstructor;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.FinancialReversalAdviceResponseV02Status;
import org.openapitools.client.model.ReversalFinancialAdviceResponseReversalResponseV02;

import java.util.List;

/**
 * Adapter class for a Financial Reversal Advice response status.
 */
@RequiredArgsConstructor
public class FinancialReversalAdviceResponseStatus implements ResponseStatus<ReversalFinancialAdviceResponseReversalResponseV02> {

    private final FinancialReversalAdviceResponseV02Status responseItem;

    @Override
    public String getCorrelationId() {
        return responseItem.getCorrelationId();
    }

    @Override
    public int getHttpStatus() {
        return responseItem.getHttpStatus();
    }

    @Override
    public ReversalFinancialAdviceResponseReversalResponseV02 getPayload() {
        return responseItem.getPayload();
    }

    @Override
    public List<Error> getErrors() {
        return responseItem.getErrors();
    }
}
