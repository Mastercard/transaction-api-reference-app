package com.mastercard.developer.exception;

import org.junit.jupiter.api.Test;
import org.openapitools.client.ApiException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class ServiceExceptionTest {

    public static final String CONNECTION_ERROR = "connection error";

    @Test
    public void testMessageConstructor() {
        ServiceException serviceException = new ServiceException(CONNECTION_ERROR);
        assertEquals(CONNECTION_ERROR, serviceException.getMessage());
    }

    @Test
    public void testThrowableConstructor() {
        ApiException e = new ApiException();
        ServiceException serviceException = new ServiceException(e);
        assertSame(e, serviceException.getCause());
    }


}
