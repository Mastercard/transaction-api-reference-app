package com.mastercard.developer.transactionapi.job;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.example.RequestExampleGenerator;
import com.mastercard.developer.transactionapi.exception.TransactionApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ExtendWith(OutputCaptureExtension.class)
class RequestSubmitterTest {

    private static final int TEST_COUNT = 5;
    @Mock
    private TransactionApiClient transactionApiClient;
    @Mock
    private RequestContextManager requestContextManager;
    @Mock
    private RequestExampleGenerator requestExampleGenerator;
    @Mock
    private TransactionApiProperties transactionApiProperties;

    private RequestSubmitter requestSubmitterSpy;

    @BeforeEach
    void setup() {
        RequestSubmitter requestSubmitter = new RequestSubmitter(transactionApiClient, requestContextManager, requestExampleGenerator, transactionApiProperties) {
            @Override
            protected void submitRequest() {
            }
        };
        requestSubmitterSpy = spy(requestSubmitter);

    }

    @Test
    void whenSubmitRequests_thenVerifyRequestsSubmitted() {
        // call
        requestSubmitterSpy.submitRequests(TEST_COUNT);

        // verify
        verify(requestSubmitterSpy, times(TEST_COUNT)).submitRequest();
    }

    @Test
    void givenException_whenSubmitRequests_thenContinue(CapturedOutput output) throws TransactionApiException {
        // setup
        doThrow(new TransactionApiException("Failed to call getInquiryResponses", new Exception()))
                .when(requestSubmitterSpy).submitRequest();

        // call
        requestSubmitterSpy.submitRequests(TEST_COUNT);

        // verify
        verify(requestSubmitterSpy, times(TEST_COUNT)).submitRequest();
        assertThat(output.getAll()).contains("Failed to submit a request");
    }

}