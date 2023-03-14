package com.mastercard.developer.service.impl;

import com.mastercard.developer.example.TransactionApiExample;
import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.response.MockTransactionApiResponse;
import okhttp3.Call;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.model.*;

import java.lang.reflect.Type;
import java.util.HashMap;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionApiServiceImplTest {

    @InjectMocks
    private TransactionApiServiceImpl transactionApiService;

    @Mock
    private ApiClient apiClient;

    @BeforeEach
    void setUp() throws Exception {
        when(apiClient.buildCall(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any())).thenReturn(mock(Call.class));
    }

    @Test
    void givenAuthorisationRequest_whenInitiate_thenSuccess() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(SC_OK, new HashMap<>(), MockTransactionApiResponse.getMockAuthorisationResponse()));
        ResponseAuthorisationResponseV02 authorisationResponseV02 = transactionApiService.initiateAuthorisation(TransactionApiExample.buildAuthorisationRequest());
        verify(apiClient, atMostOnce()).buildCall(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));
        assertAll(() -> assertNotNull(authorisationResponseV02), () -> assertEquals(MsgauthorisationresponseMessageFunction16Code.ADVC, authorisationResponseV02.getHdr().getMsgFctn()), () -> assertEquals(MockTransactionApiResponse.PAN, authorisationResponseV02.getBody().getEnvt().getCard().getPan()));
    }

    @Test
    void givenAuthorisationRequest_whenInitiate_thenNull() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(SC_OK, new HashMap<>(), null));
        ResponseAuthorisationResponseV02 authorisationResponseV02 = transactionApiService.initiateAuthorisation(TransactionApiExample.buildAuthorisationRequest());
        verify(apiClient, atMostOnce()).buildCall(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));
        assertNull(authorisationResponseV02);
    }

    @Test
    void givenAuthorisationRequest_whenInitiate_thenFailure() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenThrow(new ApiException());
        InitiationAuthorisationInitiationV02 authorisationRequest = TransactionApiExample.buildAuthorisationRequest();
        assertThrows(ServiceException.class, () -> transactionApiService.initiateAuthorisation(authorisationRequest));
        verify(apiClient, atMostOnce()).buildCall(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));
    }

    @Test
    void givenReversalRequest_whenInitiate_thenSuccess() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(SC_OK, new HashMap<>(), MockTransactionApiResponse.getMockReversalResponse()));
        ResponseReversalResponseV02 reversalResponseV02 = transactionApiService.initiateReversal(TransactionApiExample.buildReversalRequest());
        verify(apiClient, atMostOnce()).buildCall(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));
        assertAll(() -> assertNotNull(reversalResponseV02), () -> assertEquals(MsgreversalresponseMessageFunction16Code.ADVC, reversalResponseV02.getHdr().getMsgFctn()), () -> assertEquals("100", reversalResponseV02.getBody().getTx().getTxAmts().getTxAmt().getAmt()));
    }

    @Test
    void givenReversalRequest_whenInitiate_thenNull() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(SC_OK, new HashMap<>(), null));
        ResponseReversalResponseV02 reversalResponseV02 = transactionApiService.initiateReversal(TransactionApiExample.buildReversalRequest());
        verify(apiClient, atMostOnce()).buildCall(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));
        assertNull(reversalResponseV02);
    }

    @Test
    void givenReversalRequest_whenInitiate_thenFailure() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenThrow(new ApiException());
        assertThrows(ServiceException.class, () -> transactionApiService.initiateReversal(TransactionApiExample.buildReversalRequest()));
        verify(apiClient, atMostOnce()).buildCall(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));
    }

    @Test
    void givenInquiryRequest_whenInitiate_thenSuccess() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(SC_OK, new HashMap<>(), MockTransactionApiResponse.getMockInquiryResponse()));
        ResponseInquiryResponseV01 inquiryResponseV01 = transactionApiService.initiateInquiry(TransactionApiExample.buildInquiryRequest());
        verify(apiClient, atMostOnce()).buildCall(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));
        assertAll(() -> assertNotNull(inquiryResponseV01), () -> assertEquals(MockTransactionApiResponse.PAN, inquiryResponseV01.getBody().getEnvt().getCard().getPan()));
    }

    @Test
    void givenInquiryRequest_whenInitiate_thenNull() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(SC_OK, new HashMap<>(), null));
        ResponseInquiryResponseV01 inquiryResponseV01 = transactionApiService.initiateInquiry(TransactionApiExample.buildInquiryRequest());
        verify(apiClient, atMostOnce()).buildCall(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));
        assertNull(inquiryResponseV01);
    }

    @Test
    void givenInquiryRequest_whenInitiate_thenFailure() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenThrow(new ApiException());
        InitiationInquiryInitiationV01 inquiryRequest = TransactionApiExample.buildInquiryRequest();
        assertThrows(ServiceException.class, () -> transactionApiService.initiateInquiry(inquiryRequest));
        verify(apiClient, atMostOnce()).buildCall(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));
    }
}