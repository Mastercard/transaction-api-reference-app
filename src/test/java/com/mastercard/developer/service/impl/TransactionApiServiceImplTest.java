package com.mastercard.developer.service.impl;

import com.mastercard.developer.example.TransactionApiExample;
import com.mastercard.developer.response.MockTransactionApiResponse;
import okhttp3.Call;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.model.MsgauthorisationresponseMessageFunction16Code;
import org.openapitools.client.model.MsgreversalresponseMessageFunction16Code;
import org.openapitools.client.model.ResponseAuthorisationResponseV02;
import org.openapitools.client.model.ResponseReversalResponseV02;

import java.lang.reflect.Type;
import java.util.HashMap;

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
    void testInitiateAuthorisation() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(201, new HashMap<>(), MockTransactionApiResponse.getMockAuthorisationResponse()));

        ResponseAuthorisationResponseV02 authorisationResponseV02 = transactionApiService.initiateAuthorisation(TransactionApiExample.buildAuthorisationRequest());

        verify(apiClient, atMostOnce()).buildCall(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        assertAll(() -> assertNotNull(authorisationResponseV02), () -> assertEquals(MsgauthorisationresponseMessageFunction16Code.ADVC, authorisationResponseV02.getHdr().getMsgFctn()), () -> assertEquals(MockTransactionApiResponse.PAN, authorisationResponseV02.getBody().getEnvt().getCard().getPan()));
    }

    @Test
    void testInitiateReversal() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(201, new HashMap<>(), MockTransactionApiResponse.getMockReversalResponse()));

        ResponseReversalResponseV02 reversalResponseV02 = transactionApiService.initiateReversal(TransactionApiExample.buildReversalRequest());

        verify(apiClient, atMostOnce()).buildCall(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        assertAll(() -> assertNotNull(reversalResponseV02), () -> assertEquals(MsgreversalresponseMessageFunction16Code.ADVC, reversalResponseV02.getHdr().getMsgFctn()), () -> assertEquals("100", reversalResponseV02.getBody().getTx().getTxAmts().getTxAmt().getAmt()));
    }
}