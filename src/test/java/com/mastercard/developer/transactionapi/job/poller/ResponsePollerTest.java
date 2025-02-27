package com.mastercard.developer.transactionapi.job.poller;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.enums.FlowType;
import com.mastercard.developer.transactionapi.exception.TransactionApiException;
import com.mastercard.developer.transactionapi.job.processor.ResponseProcessor;
import com.mastercard.developer.transactionapi.utils.ClientUtils;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.ApiException;
import org.openapitools.client.JSON;
import org.openapitools.client.model.AuthorisationResponseAuthorisationResponseV02;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Duration;

import static com.mastercard.developer.transactionapi.test.TestConstants.FAILURE_RECOVERY_DURATION;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_API_EXCEPTION_MESSAGE_AUTH;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CORRELATION_ID;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CUSTOMER_CONTEXT_KEY;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_RETRY_AFTER;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_TRANSACTION_API_EXCEPTION_INQ_CAUSE;
import static com.mastercard.developer.transactionapi.test.TestConstants.WAIT_FOR_REQUESTS_DURATION;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.generateBatchBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(OutputCaptureExtension.class)
class ResponsePollerTest {

    private static final Duration TEST_NEW_RETRY_AFTER = Duration.ofMillis(100);

    @InjectMocks
    private AuthorisationResponsePoller poller;

    @Mock
    private RequestContextManager requestContextManager;

    @Mock
    private TransactionApiProperties transactionApiProperties;

    @Mock
    private TransactionApiClient transactionApiClient;

    @Mock
    private ResponseProcessor responseProcessor;

    private final ResponsePoller.LastPollResults results = new ResponsePoller.LastPollResults();

    private final BatchResponse.BatchResponseBuilder<AuthorisationResponseAuthorisationResponseV02> batchBuilderOkay =
            generateBatchBuilder(TEST_CORRELATION_ID, TEST_CUSTOMER_CONTEXT_KEY, HttpStatus.SC_OK);

    @BeforeEach
    void setup() {
        poller = spy(poller);
        new JSON(); // Initialises required JSON.getGson() which is required for Json translation
    }

    @Test
    void stop() {
        // call
        poller.stop();

        // verify
        assertThat(ReflectionTestUtils.getField(poller, "canRun")).isEqualTo(false);
    }

    @Test
    void whenRun_thenPoll() {
        // setup
        doAnswer(i -> null).doAnswer(i -> {
            poller.stop();
            return null;
        }).when(poller).pollOnce(any());

        // call
        poller.run();

        // verify
        verify(poller, times(2)).pollOnce(any());
    }

    @Test
    void givenException_whenRun_thenVerifyException(CapturedOutput output) {
        // setup
        results.hasMore = false;
        results.retryAfter = TEST_NEW_RETRY_AFTER;

        doThrow(new TransactionApiException(TEST_API_EXCEPTION_MESSAGE_AUTH, new ApiException()))
                .when(requestContextManager).haveOngoingRequests(FlowType.AUTHORISATION);

        // call
        poller.run();

        // verify
        assertThat(output.getAll()).contains("Poller terminated");
    }

    @Test
    void givenHasMore_whenPollOnce_thenProcessResponseBatch() {
        // setup
        results.hasMore = true;
        results.retryAfter = Duration.ZERO;

        doReturn(batchBuilderOkay
                .hasMore(false)
                .retryAfter(TEST_NEW_RETRY_AFTER)
                .build()).when(poller).getResponses();

        try (MockedStatic<ClientUtils> mockClientUtils = mockStatic(ClientUtils.class)) {
            // call
            poller.pollOnce(results);

            // verify
            mockClientUtils.verify(() -> ClientUtils.sleep(Duration.ZERO));
            verify(poller).getResponses();
        }

        verify(requestContextManager).onResponseReceived(FlowType.AUTHORISATION, TEST_CUSTOMER_CONTEXT_KEY);
        verify(responseProcessor).processResponseStatus(batchBuilderOkay.build().getItems().get(0),FlowType.AUTHORISATION);

        assertThat(results.hasMore).isFalse();
        assertThat(results.retryAfter).isEqualTo(TEST_NEW_RETRY_AFTER);
    }

    @Test
    void givenHasNoMore_andHasOnGoingRequests_whenPollOnce_thenProcessResponseBatch() {
        // setup
        results.hasMore = false;
        results.retryAfter = TEST_RETRY_AFTER;

        when(requestContextManager.haveOngoingRequests(FlowType.AUTHORISATION)).thenReturn(true);
        doReturn(batchBuilderOkay
                .hasMore(false)
                .retryAfter(TEST_NEW_RETRY_AFTER)
                .build()).when(poller).getResponses();

        try (MockedStatic<ClientUtils> mockClientUtils = mockStatic(ClientUtils.class)) {
            // call
            poller.pollOnce(results);

            // verify
            mockClientUtils.verify(() -> ClientUtils.sleep(TEST_RETRY_AFTER));
            verify(poller).getResponses();
        }

        verify(requestContextManager).onResponseReceived(FlowType.AUTHORISATION, TEST_CUSTOMER_CONTEXT_KEY);
        verify(responseProcessor).processResponseStatus(batchBuilderOkay.build().getItems().get(0),FlowType.AUTHORISATION);

        assertThat(results.hasMore).isFalse();
        assertThat(results.retryAfter).isEqualTo(TEST_NEW_RETRY_AFTER);
    }

    @Test
    void givenHasNoMore_andNoOnGoingRequests_whenPollOnce_thenVerifyNoInteractions() {
        // setup
        results.hasMore = false;
        results.retryAfter = TEST_RETRY_AFTER;

        when(requestContextManager.haveOngoingRequests(FlowType.AUTHORISATION)).thenReturn(false);

        try (MockedStatic<ClientUtils> mockClientUtils = mockStatic(ClientUtils.class)) {
            // call
            poller.pollOnce(results);

            // verify
            mockClientUtils.verify(() -> ClientUtils.sleep(WAIT_FOR_REQUESTS_DURATION));
            verify(poller, never()).getResponses();
        }

        assertThat(results.hasMore).isFalse();
        assertThat(results.retryAfter).isEqualTo(TEST_RETRY_AFTER);
    }

    @Test
    void givenHasMore_andException_whenPollOnce_thenVerifyException(CapturedOutput output) {
        // setup
        results.hasMore = true;
        results.retryAfter = Duration.ZERO;

        doThrow(new TransactionApiException(TEST_API_EXCEPTION_MESSAGE_AUTH, TEST_TRANSACTION_API_EXCEPTION_INQ_CAUSE))
                .when(poller).getResponses();

        try (MockedStatic<ClientUtils> mockClientUtils = mockStatic(ClientUtils.class)) {
            // call
            poller.pollOnce(results);

            // verify
            mockClientUtils.verify(() -> ClientUtils.sleep(Duration.ZERO));
            verify(poller).getResponses();
        }

        assertThat(output.getAll()).contains("Failed to get AUTHORISATION responses; Cause: Failed to call processAuthorisationRequest");

        assertThat(results.hasMore).isFalse();
        assertThat(results.retryAfter).isEqualTo(FAILURE_RECOVERY_DURATION);
    }

}