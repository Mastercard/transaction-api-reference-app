package com.mastercard.developer.transactionapi.client.model;

import org.openapitools.client.model.Error;

import java.util.List;

/**
 * Adapter class for response items of different types.
 * So that they all items can be accessed using a common interface.
 * @param <P>  response item payload type
 */
public interface ResponseItem<P> {

    String getCorrelationId();

    int getHttpStatus();

    P getPayload();

    List<Error> getErrors();
}
