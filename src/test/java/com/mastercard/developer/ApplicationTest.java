package com.mastercard.developer;

import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.executor.ServicesExecutor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationTest {

    @InjectMocks
    private Application application;

    @Mock
    private ServicesExecutor servicesExecutor;

    @Test
    void testRun() throws ServiceException {
        doNothing().when(servicesExecutor).execute();

        application.run();

        verify(servicesExecutor, atMostOnce()).execute();
    }

    @Test
    void testScheduleRun() throws ServiceException {
        doNothing().when(servicesExecutor).execute();

        application.scheduleExecution();

        verify(servicesExecutor, atMostOnce()).execute();
    }

    @Test
    void test1Run() throws Exception {
        String MESSAGE = "size must be 6 bytes";
        doThrow(new ServiceException(MESSAGE)).when(servicesExecutor).execute();

        application.run();

        verify(servicesExecutor, atMostOnce()).execute();
    }
}
