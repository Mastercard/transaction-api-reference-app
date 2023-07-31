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
import org.openapitools.client.model.ResponseInquiryResponseV01;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InquiryResponsePollerTest {
    @Mock
    private TransactionApiClient mockTransactionApiClient;

    @InjectMocks
    private InquiryResponsePoller inquiryResponsePoller;

    @Mock
    private BatchResponse<ResponseInquiryResponseV01> testBatchResponse;

    @BeforeAll
    static void setup() {
        new JSON();     // required to initialise Json.getGson() method
    }


    @Test
    void whenGetResponses_verifyGetInquiryResponses() {
        // setup
        when(mockTransactionApiClient.getInquiryResponses()).thenReturn(testBatchResponse);

        // call
        BatchResponse<ResponseInquiryResponseV01> actual = inquiryResponsePoller.getResponses();

        // verify
        assertThat(actual).isSameAs(testBatchResponse);
    }

    @Test
    void whenToJson_verifyJsonOutput() {
        // setup
        ResponseInquiryResponseV01 testResponse = TestRequestResponseGenerator.getResponseInquiryResponseItem();
        // call
        String result = testResponse.toJson();

        // verify
        assertThat(result).isEqualTo("{\"hdr\":{\"msgFctn\":\"MESSAGE_FUNCTION17_CODE_UNSPECIFIED\"},\"body\":{}}");
    }

}