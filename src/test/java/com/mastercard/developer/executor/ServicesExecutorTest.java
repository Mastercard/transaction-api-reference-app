package com.mastercard.developer.executor;

import com.mastercard.developer.config.MastercardProperties;
import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.service.TransactionApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.model.ResponseAuthorisationResponseV02;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServicesExecutorTest {

    @InjectMocks
    ServicesExecutor servicesExecutor;

    @Mock
    private TransactionApiService transactionApiService;

    @Mock
    private MastercardProperties mcProperties;

    @Mock
    private ResponseAuthorisationResponseV02 responseV02;

    @Test
    public void executeTest() throws ServiceException {
        given(transactionApiService.initiateAuthorisation(any(), any())).willReturn(responseV02);
        servicesExecutor.execute();
        verify(transactionApiService, times(0)).health(any());
        verify(transactionApiService, times(1)).initiateAuthorisation(any(), any());
    }

    @Test
    public void executeTestWithHealth() throws ServiceException {
        given(transactionApiService.initiateAuthorisation(any(), any())).willReturn(responseV02);
        when(mcProperties.isHealthEnable()).thenReturn(true);
        servicesExecutor.execute();
        verify(transactionApiService, times(1)).health(any());
        verify(transactionApiService, times(1)).initiateAuthorisation(any(), any());
    }
}
