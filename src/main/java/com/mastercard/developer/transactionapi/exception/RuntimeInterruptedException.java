package com.mastercard.developer.transactionapi.exception;

/**
 * Exception thrown if a thread is interrupted.
 */
public class RuntimeInterruptedException extends RuntimeException {

    public RuntimeInterruptedException() {
        super("Thread interrupted");
    }
}
