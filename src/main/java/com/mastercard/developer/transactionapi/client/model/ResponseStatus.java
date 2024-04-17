package com.mastercard.developer.transactionapi.client.model;

import org.openapitools.client.model.Error;

import java.util.List;

/**
 * Adapter class for response status of different types.
 * So that they all items can be accessed using a common interface.
 * @param <P>  response status payload type
 */
public interface ResponseStatus<P> {

    String getCorrelationId();

    int getHttpStatus();

    P getPayload();

    List<Error> getErrors();
}
