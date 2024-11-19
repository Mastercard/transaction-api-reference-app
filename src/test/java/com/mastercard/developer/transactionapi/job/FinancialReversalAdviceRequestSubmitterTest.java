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
import org.openapitools.client.model.ReversalFinancialAdviceInitiationReversalInitiationV02;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CORRELATION_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(OutputCaptureExtension.class)
class FinancialReversalAdviceRequestSubmitterTest {

    @InjectMocks
    private FinancialReversalAdviceRequestSubmitter financialReversalAdviceResponseSubmitter;

    @Mock
    private TransactionApiClient transactionApiClient;
    @Mock
    private RequestContextManager requestContextManager;
    @Mock
    private RequestExampleGenerator requestExampleGenerator;
    @Mock
    private TransactionApiProperties transactionApiProperties;

    private ReversalFinancialAdviceInitiationReversalInitiationV02 testRequest = TestRequestResponseGenerator.getTestFinancialReversalAdviceInitiationV02();

    @BeforeEach
    void setup() {
        new JSON();     // required to initialise Json.getGson() method
        when(transactionApiProperties.isPayloadLoggingEnabled()).thenReturn(true);
    }

    @Test
    void whenSubmitRequest_thenVerifyRequestSent(CapturedOutput output) {
        // setup
        when(requestExampleGenerator.buildFinancialReversalAdvice()).thenReturn(testRequest);
        when(transactionApiClient.submitFinancialReversalAdvice(testRequest)).thenReturn(TEST_CORRELATION_ID);

        // call
        financialReversalAdviceResponseSubmitter.submitRequest();

        // verify
        verify(requestExampleGenerator).buildFinancialReversalAdvice();
        verify(transactionApiClient).submitFinancialReversalAdvice(testRequest);
        verify(requestContextManager).onRequestSent(FlowType.FINANCIAL_REVERSAL_ADVICE, TEST_CORRELATION_ID, testRequest);

        assertThat(output.getAll()).contains("Submitted financial reversal advice request with correlationId=12345: {\"hdr\":{\"msgFctn\":");
    }

    @Test
    void givenLoggingDisabled_whenSubmitRequest_thenVerifyRequestSent(CapturedOutput output) {
        // setup
        when(transactionApiProperties.isPayloadLoggingEnabled()).thenReturn(false);
        when(requestExampleGenerator.buildFinancialReversalAdvice()).thenReturn(testRequest);
        when(transactionApiClient.submitFinancialReversalAdvice(testRequest)).thenReturn(TEST_CORRELATION_ID);

        // call
        financialReversalAdviceResponseSubmitter.submitRequest();

        // verify
        verify(requestExampleGenerator).buildFinancialReversalAdvice();
        verify(transactionApiClient).submitFinancialReversalAdvice(testRequest);
        verify(requestContextManager).onRequestSent(FlowType.FINANCIAL_REVERSAL_ADVICE, TEST_CORRELATION_ID, testRequest);

        assertThat(output.getAll()).contains("Submitted financial reversal advice request with correlationId=12345: omitted payload");
    }
}