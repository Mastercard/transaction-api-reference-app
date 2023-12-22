package com.mastercard.developer.transactionapi.job;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.JSON;
import org.openapitools.client.model.FinancialresponseFinancialResponseV02;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FinancialAdviceResponsePollerTest {

    @Mock
    private TransactionApiClient mockTransactionApiClient;

    @InjectMocks
    private FinancialAdviceResponsePoller financialAdviceResponsePoller;

    @Mock
    private BatchResponse<FinancialresponseFinancialResponseV02> testBatchResponse;

    @BeforeAll
    static void setup() {
        new JSON();     // required to initialise Json.getGson() method
    }

    @Test
    void whenGetResponses_verifyGetFinancialAdviceResponses() {
        // setup
        when(mockTransactionApiClient.getFinancialAdviceResponses()).thenReturn(testBatchResponse);

        // call
        BatchResponse<FinancialresponseFinancialResponseV02> actual = financialAdviceResponsePoller.getResponses();

        // verify
        assertThat(actual).isSameAs(testBatchResponse);
    }

    @Test
    void whenToJson_verifyJsonOutput() {
        // setup
        FinancialresponseFinancialResponseV02 testResponse = TestRequestResponseGenerator.getResponseFinancialAdvResponseItem();
        // call
        String result = testResponse.toJson();

        // verify
        assertThat(result).isEqualTo("{\"hdr\":{\"msgFctn\":\"UNSPECIFIED\"},\"body\":{}}");
    }

}