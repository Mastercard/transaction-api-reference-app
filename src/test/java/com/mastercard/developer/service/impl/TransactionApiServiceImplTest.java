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
import org.openapitools.client.model.ResponseAuthorisationResponseV02;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionApiServiceImplTest {

    @InjectMocks
    private TransactionApiServiceImpl transactionApiService;

    @Mock
    private ApiClient apiClient;

    @BeforeEach
    void setUp() throws Exception {
        when(apiClient.buildCall(anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any())).thenReturn(mock(Call.class));
    }

    @Test
    void testHealthCheck() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(201, new HashMap<>(), MockTransactionApiResponse.getMockHealthResponse()));

        String status = transactionApiService.health(any());

        verify(apiClient, atMostOnce()).buildCall(anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        assertAll(() -> assertNotNull(status), () -> assertEquals("{\"status\": \"up\"}", status));
    }

    @Test
    void testInitiateAuthorisation() throws Exception {
        when(apiClient.execute(any(Call.class), any(Type.class))).thenReturn(new ApiResponse<>(201, new HashMap<>(), MockTransactionApiResponse.getMockAuthrisationResponse()));

        ResponseAuthorisationResponseV02 authorisationResponseV02 = transactionApiService.initiateAuthorisation(TransactionApiExample.buildAuthorisationRequest(), UUID.randomUUID().toString());

        verify(apiClient, atMostOnce()).buildCall(anyString(), anyString(), anyList(), anyList(), any(), anyMap(), anyMap(), anyMap(), any(), any());
        verify(apiClient, atMostOnce()).execute(any(Call.class), any(Type.class));

        assertAll(() -> assertNotNull(authorisationResponseV02), () -> assertEquals(MsgauthorisationresponseMessageFunction16Code.ADVC, authorisationResponseV02.getHdr().getMsgFctn()), () -> assertEquals(MockTransactionApiResponse.PAN, authorisationResponseV02.getBody().getEnvt().getCard().getpAN()));
    }
}