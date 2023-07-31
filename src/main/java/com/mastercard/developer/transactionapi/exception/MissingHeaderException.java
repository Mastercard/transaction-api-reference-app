package com.mastercard.developer.transactionapi.exception;

/**
 * Exception thrown if a required header is not present in the response.
 */
public class MissingHeaderException extends RuntimeException {

    public MissingHeaderException(String message) {
        super(message);
    }

}