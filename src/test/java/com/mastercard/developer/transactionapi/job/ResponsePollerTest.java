package com.mastercard.developer.transactionapi.job;

import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.enums.FlowType;
import com.mastercard.developer.transactionapi.exception.TransactionApiException;
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
import org.openapitools.client.model.ResponseAuthorisationResponseV02;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Duration;

import static com.mastercard.developer.transactionapi.test.TestConstants.FAILURE_RECOVERY_DURATION;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_API_EXCEPTION_MESSAGE_AUTH;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CORRELATION_ID;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_RETRY_AFTER;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_TRANSACTION_API_EXCEPTION_INQ_CAUSE;
import static com.mastercard.developer.transactionapi.test.TestConstants.WAIT_FOR_REQUESTS_DURATION;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.generateBatchBuilder;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.generateBatchBuilderWithErrors;
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

    private final ResponsePoller.LastPollResults results = new ResponsePoller.LastPollResults();

    private final BatchResponse.BatchResponseBuilder<ResponseAuthorisationResponseV02> batchBuilderOkay =
            generateBatchBuilder(TEST_CORRELATION_ID, HttpStatus.SC_OK);


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
        when(transactionApiProperties.isPayloadLoggingEnabled()).thenReturn(true);
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

        verify(requestContextManager).onResponseReceived(FlowType.AUTHORISATION, TEST_CORRELATION_ID);

        assertThat(results.hasMore).isFalse();
        assertThat(results.retryAfter).isEqualTo(TEST_NEW_RETRY_AFTER);
    }

    @Test
    void givenHasNoMore_andHasOnGoingRequests_whenPollOnce_thenProcessResponseBatch() {
        // setup
        when(transactionApiProperties.isPayloadLoggingEnabled()).thenReturn(true);
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

        verify(requestContextManager).onResponseReceived(FlowType.AUTHORISATION, TEST_CORRELATION_ID);

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


    @Test
    void givenItemNotNullAndHttpStatusIsOkay_whenProcessResponseItem_thenVerifyOutput(CapturedOutput output) {
        // setup
        when(transactionApiProperties.isPayloadLoggingEnabled()).thenReturn(true);
        results.hasMore = true;
        results.retryAfter = Duration.ZERO;

        doReturn(batchBuilderOkay
                .hasMore(false)
                .retryAfter(TEST_NEW_RETRY_AFTER)
                .build()).when(poller).getResponses();

        // call
        poller.pollOnce(results);

        // verify
        assertThat(output.getAll()).contains("Received successful AUTHORISATION response with correlationId=12345: {\"hdr\":{\"msgFctn\":\"UNSPECIFIED\"},\"body\":");
    }

    @Test
    void givenItemNotNullAndHttpStatusIsNotOkay_whenProcessResponseItem_thenVerifyOutput(CapturedOutput output) {
        // setup
        when(transactionApiProperties.isPayloadLoggingEnabled()).thenReturn(true);
        results.hasMore = true;
        results.retryAfter = Duration.ZERO;

        BatchResponse.BatchResponseBuilder<ResponseAuthorisationResponseV02> batchBuilderNotOkay =
                generateBatchBuilder(TEST_CORRELATION_ID, HttpStatus.SC_BAD_REQUEST);

        BatchResponse<ResponseAuthorisationResponseV02> batchResponse = batchBuilderNotOkay
                .hasMore(false)
                .retryAfter(TEST_NEW_RETRY_AFTER)
                .build();

        doReturn(batchResponse).when(poller).getResponses();

        // call
        poller.pollOnce(results);

        // verify
        assertThat(output.getAll()).contains("Received unsuccessful AUTHORISATION response with correlationId=12345, httpStatus=400: {\"hdr\":{\"msgFctn\":\"");
    }

    @Test
    void givenItemNotNullAndHttpStatusIsNotOkayAndLogsDisabled_whenProcessResponseItem_thenVerifyOutput(CapturedOutput output) {
        // setup
        when(transactionApiProperties.isPayloadLoggingEnabled()).thenReturn(false);
        results.hasMore = true;
        results.retryAfter = Duration.ZERO;

        BatchResponse.BatchResponseBuilder<ResponseAuthorisationResponseV02> batchBuilderNotOkay =
                generateBatchBuilder(TEST_CORRELATION_ID, HttpStatus.SC_BAD_REQUEST);

        doReturn(batchBuilderNotOkay
                .hasMore(false)
                .retryAfter(TEST_NEW_RETRY_AFTER)
                .build()).when(poller).getResponses();

        // call
        poller.pollOnce(results);

        // verify
        assertThat(output.getAll()).contains("Received unsuccessful AUTHORISATION response with correlationId=12345, httpStatus=400: omitted payload");
    }

    @Test
    void givenItemWithoutPayload_whenProcessResponseItem_thenVerifyOutput(CapturedOutput output) {
        // setup
        results.hasMore = true;
        results.retryAfter = Duration.ZERO;

        BatchResponse.BatchResponseBuilder<ResponseAuthorisationResponseV02> batchBuilderFailure =
                generateBatchBuilderWithErrors(TEST_CORRELATION_ID, HttpStatus.SC_BAD_REQUEST);

        doReturn(batchBuilderFailure
                .hasMore(false)
                .retryAfter(TEST_NEW_RETRY_AFTER)
                .build()).when(poller).getResponses();

        // call
        poller.pollOnce(results);

        // verify
        assertThat(output.getAll()).contains("Received failed AUTHORISATION response with correlationId=12345, httpStatus=400, errors=[class Error {\n" + "    source: Test\n" + "    reasonCode: FAILED\n" + "    description: Test Exception\n" + "    recoverable: false\n" + "    details: Test Failure\n" + "}]");
    }

}