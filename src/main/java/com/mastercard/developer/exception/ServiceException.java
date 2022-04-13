package com.mastercard.developer.exception;

import org.openapitools.client.ApiException;

public class ServiceException extends RuntimeException {

    /**
     * @param message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * @param e
     */
    public ServiceException(Throwable e) {
        super(e);
    }
}