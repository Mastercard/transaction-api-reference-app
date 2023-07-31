package com.mastercard.developer.transactionapi.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MissingHeaderExceptionTest {

    private static final String MISSING_HEADER_ERROR = "missing header";

    @Test
    void testThrowableConstructor() {
        MissingHeaderException missingHeaderException = new MissingHeaderException(MISSING_HEADER_ERROR);

        assertThat(missingHeaderException).hasMessage(MISSING_HEADER_ERROR);
    }
}