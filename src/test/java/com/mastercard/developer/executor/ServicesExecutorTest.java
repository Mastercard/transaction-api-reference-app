package com.mastercard.developer.executor;

import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.service.TransactionApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.model.ResponseAuthorisationResponseV02;
import org.openapitools.client.model.ResponseInquiryResponseV01;
import org.openapitools.client.model.ResponseReversalResponseV02;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ServicesExecutorTest {

    @InjectMocks
    ServicesExecutor servicesExecutor;
    @Mock
    private TransactionApiService transactionApiService;
    @Mock
    private ResponseAuthorisationResponseV02 authorisationResponseV02;
    @Mock
    private ResponseReversalResponseV02 reversalResponseV02;
    @Mock
    private ResponseInquiryResponseV01 inquiryResponseV02;

    @Test
    void executeTest() throws ServiceException {
        given(transactionApiService.initiateAuthorisation(any())).willReturn(authorisationResponseV02);
        given(transactionApiService.initiateReversal(any())).willReturn(reversalResponseV02);
        given(transactionApiService.initiateInquiry(any())).willReturn(inquiryResponseV02);
        servicesExecutor.execute();
        verify(transactionApiService, times(1)).initiateAuthorisation(any());
        verify(transactionApiService, times(1)).initiateReversal(any());
        verify(transactionApiService, times(1)).initiateInquiry(any());
    }
}