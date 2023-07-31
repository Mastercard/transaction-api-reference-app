package com.mastercard.developer.transactionapi.exception;

/**
 * Exception thrown on API failures.
 */
public class TransactionApiException extends RuntimeException {

    public TransactionApiException(String message, Throwable e) {
        super(message, e);
    }
}