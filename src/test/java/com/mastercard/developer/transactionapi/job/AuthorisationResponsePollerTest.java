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
import org.openapitools.client.model.AuthorisationresponseAuthorisationResponseV02;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorisationResponsePollerTest {

    @Mock
    private TransactionApiClient mockTransactionApiClient;

    @InjectMocks
    private AuthorisationResponsePoller authorisationResponsePoller;

    @Mock
    private BatchResponse<AuthorisationresponseAuthorisationResponseV02> testBatchResponse;

    @BeforeAll
    static void setup() {
        new JSON();               // required to initialise Json.getGson() method
    }

    @Test
    void whenGetResponses_verifyGetAuthorisationResponses() {
        // setup
        when(mockTransactionApiClient.getAuthorisationResponses()).thenReturn(testBatchResponse);

        // call
        BatchResponse<AuthorisationresponseAuthorisationResponseV02> actual = authorisationResponsePoller.getResponses();

        // verify
        assertThat(actual).isSameAs(testBatchResponse);
    }

    @Test
    void whenToJson_verifyJsonOutput() {
        // setup
        AuthorisationresponseAuthorisationResponseV02 testResponse = TestRequestResponseGenerator.getResponseAuthResponseItem();
        // call
        String result = testResponse.toJson();

        // verify
        assertThat(result).isEqualTo(
                "{\"hdr\":{\"msgFctn\":\"UNSPECIFIED\"},\"body\":{}}");
    }
}