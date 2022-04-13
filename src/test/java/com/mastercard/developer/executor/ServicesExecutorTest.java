package com.mastercard.developer.executor;

import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.service.TransactionApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;

@ExtendWith(MockitoExtension.class)
public class ServicesExecutorTest {

    @InjectMocks
    ServicesExecutor servicesExecutor;

    @Mock
    private TransactionApiService transactionApiService;

    @Test
    public void executeTest() throws ServiceException {
        servicesExecutor.execute();
    }
}
