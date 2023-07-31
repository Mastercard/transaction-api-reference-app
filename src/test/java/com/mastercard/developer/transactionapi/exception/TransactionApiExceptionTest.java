package com.mastercard.developer.transactionapi.exception;

import org.junit.jupiter.api.Test;
import org.openapitools.client.ApiException;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionApiExceptionTest {

    private static final String CONNECTION_ERROR = "connection error";

    @Test
    void testThrowableConstructor() {
        ApiException cause = new ApiException();

        TransactionApiException transactionApiException = new TransactionApiException(CONNECTION_ERROR, cause);

        assertThat(transactionApiException).hasMessage(CONNECTION_ERROR);
        assertThat(transactionApiException.getCause()).isSameAs(cause);
    }

}
