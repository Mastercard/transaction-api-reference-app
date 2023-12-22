package com.mastercard.developer.transactionapi.job;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.enums.FlowType;
import com.mastercard.developer.transactionapi.example.RequestExampleGenerator;
import com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.JSON;
import org.openapitools.client.model.ReversalinitiationReversalInitiationV02;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CORRELATION_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(OutputCaptureExtension.class)
class ReversalRequestSubmitterTest {

    @InjectMocks
    private ReversalRequestSubmitter reversalRequestSubmitter;

    @Mock
    private TransactionApiClient transactionApiClient;
    @Mock
    private RequestContextManager requestContextManager;
    @Mock
    private RequestExampleGenerator requestExampleGenerator;
    @Mock
    private TransactionApiProperties transactionApiProperties;

    private ReversalinitiationReversalInitiationV02 testRequest = TestRequestResponseGenerator.getTestReversalInitiationV02();

    @BeforeEach
    void setup() {
        new JSON();     // required to initialise Json.getGson() method
        when(transactionApiProperties.isPayloadLoggingEnabled()).thenReturn(true);
    }

    @Test
    void whenSubmitRequest_thenVerifyRequestSent(CapturedOutput output) {
        // setup
        when(requestExampleGenerator.buildReversalRequest()).thenReturn(testRequest);
        when(transactionApiClient.submitReversalRequest(testRequest)).thenReturn(TEST_CORRELATION_ID);

        // call
        reversalRequestSubmitter.submitRequest();

        // verify
        verify(requestExampleGenerator).buildReversalRequest();
        verify(transactionApiClient).submitReversalRequest(testRequest);
        verify(requestContextManager).onRequestSent(FlowType.REVERSAL, TEST_CORRELATION_ID, testRequest);

        assertThat(output.getAll()).contains("Submitted reversal request with correlationId=12345: {\"hdr\":{\"msgFctn\":");
    }

    @Test
    void givenLoggingDisabled_whenSubmitRequest_thenVerifyRequestSent(CapturedOutput output) {
        // setup
        when(transactionApiProperties.isPayloadLoggingEnabled()).thenReturn(false);
        when(requestExampleGenerator.buildReversalRequest()).thenReturn(testRequest);
        when(transactionApiClient.submitReversalRequest(testRequest)).thenReturn(TEST_CORRELATION_ID);

        // call
        reversalRequestSubmitter.submitRequest();

        // verify
        verify(requestExampleGenerator).buildReversalRequest();
        verify(transactionApiClient).submitReversalRequest(testRequest);
        verify(requestContextManager).onRequestSent(FlowType.REVERSAL, TEST_CORRELATION_ID, testRequest);

        assertThat(output.getAll()).contains("Submitted reversal request with correlationId=12345: omitted payload");
    }
}