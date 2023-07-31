package com.mastercard.developer.transactionapi.context;

import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.enums.FlowType;
import com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator;
import com.mastercard.developer.transactionapi.utils.ClientUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.model.InitiationAuthorisationInitiationV02;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.time.Duration;

import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CORRELATION_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
@ExtendWith(OutputCaptureExtension.class)
class RequestContextManagerTest {

    private RequestContextManager requestContextManager;

    private final InitiationAuthorisationInitiationV02 testRequest = TestRequestResponseGenerator.getTestAuthorisationInitiationV02();

    private final String incorrectTestCorrId = "6789";

    @BeforeEach
    void setUp() {
        TransactionApiProperties transactionApiProperties = new TransactionApiProperties();
        transactionApiProperties.setRequestTimeout(Duration.ofSeconds(5));

        requestContextManager = spy(new RequestContextManager(transactionApiProperties));
    }

    @AfterEach
    void cleanUp() {
        requestContextManager.shutdown();
    }

    @Test
    void givenFilledCacheAuth_whenHaveOngoingRequests_returnTrue() {
        // setup
        requestContextManager.onRequestSent(FlowType.AUTHORISATION, TEST_CORRELATION_ID, testRequest);

        // call
        boolean result = requestContextManager.haveOngoingRequests(FlowType.AUTHORISATION);

        // verify
        assertThat(result).isTrue();
    }

    @Test
    void givenEmptyCacheAuth_whenHaveOngoingRequests_returnFalse() {
        // call
        boolean result = requestContextManager.haveOngoingRequests(FlowType.AUTHORISATION);

        // verify
        assertThat(result).isFalse();
    }

    @Test
    void givenFilledCacheAuth_whenHaveAnyOngoingRequests_returnTrue() {
        // setup
        requestContextManager.onRequestSent(FlowType.AUTHORISATION, TEST_CORRELATION_ID, testRequest);

        // call
        boolean result = requestContextManager.haveAnyOngoingRequests();

        // verify
        assertThat(result).isTrue();
    }

    @Test
    void givenEmptyCacheAuth_whenHaveAnyOngoingRequests_returnFalse() {
        // call
        boolean result = requestContextManager.haveAnyOngoingRequests();

        // verify
        assertThat(result).isFalse();
    }

    @Test
    void givenFilledCacheAuth_whenOnResponseReceived_verifyCacheEntryIsInvalidated() {
        // setup
        requestContextManager.onRequestSent(FlowType.AUTHORISATION, TEST_CORRELATION_ID, testRequest);

        // call
        requestContextManager.onResponseReceived(FlowType.AUTHORISATION, TEST_CORRELATION_ID);

        // verify
        assertThat(requestContextManager.haveAnyOngoingRequests()).isFalse();

    }

    @Test
    void givenFilledCacheAuth_whenOnResponseReceivedForIncorrectCorrId_verifyCacheEntryIsNotInvalidated() {
        // setup
        requestContextManager.onRequestSent(FlowType.AUTHORISATION, TEST_CORRELATION_ID, testRequest);

        // call
        requestContextManager.onResponseReceived(FlowType.AUTHORISATION, incorrectTestCorrId);

        // verify
        assertThat(requestContextManager.haveAnyOngoingRequests()).isTrue();

    }

    @Test
    void givenFilledAuthCache_whenOnResponseReceivedForIncorrectFlow_verifyCacheEntryIsNotInvalidated() {
        // setup
        requestContextManager.onRequestSent(FlowType.AUTHORISATION, TEST_CORRELATION_ID, testRequest);

        // call
        requestContextManager.onResponseReceived(FlowType.REVERSAL, TEST_CORRELATION_ID);

        // verify
        assertThat(requestContextManager.haveAnyOngoingRequests()).isTrue();
    }


    @Test
    void givenFilledCacheInq_whenHaveOngoingRequests_returnTrue() {
        // setup
        requestContextManager.onRequestSent(FlowType.INQUIRY, TEST_CORRELATION_ID, testRequest);

        // call
        boolean result = requestContextManager.haveOngoingRequests(FlowType.INQUIRY);

        // verify
        assertThat(result).isTrue();
    }

    @Test
    void givenEmptyCacheInq_whenHaveOngoingRequests_returnFalse() {
        // call
        boolean result = requestContextManager.haveOngoingRequests(FlowType.INQUIRY);

        // verify
        assertThat(result).isFalse();
    }

    @Test
    void givenFilledCacheInq_whenHaveAnyOngoingRequests_returnTrue() {
        // setup
        requestContextManager.onRequestSent(FlowType.INQUIRY, TEST_CORRELATION_ID, testRequest);

        // call
        boolean result = requestContextManager.haveAnyOngoingRequests();

        // verify
        assertThat(result).isTrue();
    }

    @Test
    void givenFilledCacheInq_whenOnResponseReceived_verifyCacheEntryIsInvalidated() {
        // setup
        requestContextManager.onRequestSent(FlowType.INQUIRY, TEST_CORRELATION_ID, testRequest);

        // call
        requestContextManager.onResponseReceived(FlowType.INQUIRY, TEST_CORRELATION_ID);

        // verify
        assertThat(requestContextManager.haveAnyOngoingRequests()).isFalse();

    }

    @Test
    void givenFilledCacheInq_whenOnResponseReceivedForIncorrectCorrId_verifyCacheEntryIsNotInvalidated() {
        // setup
        requestContextManager.onRequestSent(FlowType.INQUIRY, TEST_CORRELATION_ID, testRequest);

        // call
        requestContextManager.onResponseReceived(FlowType.INQUIRY, incorrectTestCorrId);

        // verify
        assertThat(requestContextManager.haveAnyOngoingRequests()).isTrue();

    }

    @Test
    void givenFilledCacheRev_whenHaveOngoingRequests_returnTrue() {
        // setup
        requestContextManager.onRequestSent(FlowType.REVERSAL, TEST_CORRELATION_ID, testRequest);

        // call
        boolean result = requestContextManager.haveOngoingRequests(FlowType.REVERSAL);

        // verify
        assertThat(result).isTrue();
    }

    @Test
    void givenEmptyCacheRev_whenHaveOngoingRequests_returnFalse() {
        // call
        boolean result = requestContextManager.haveOngoingRequests(FlowType.REVERSAL);

        // verify
        assertThat(result).isFalse();
    }

    @Test
    void givenFilledCacheRev_whenHaveAnyOngoingRequests_returnTrue() {
        // setup
        requestContextManager.onRequestSent(FlowType.REVERSAL, TEST_CORRELATION_ID, testRequest);

        // call
        boolean result = requestContextManager.haveAnyOngoingRequests();

        // verify
        assertThat(result).isTrue();
    }

    @Test
    void givenFilledCacheRev_whenOnResponseReceived_verifyCacheEntryIsInvalidated() {
        // setup
        requestContextManager.onRequestSent(FlowType.REVERSAL, TEST_CORRELATION_ID, testRequest);

        // call
        requestContextManager.onResponseReceived(FlowType.REVERSAL, TEST_CORRELATION_ID);

        // verify
        assertThat(requestContextManager.haveAnyOngoingRequests()).isFalse();

    }

    @Test
    void givenFilledCacheRev_whenOnResponseReceivedForIncorrectCorrId_verifyCacheEntryIsNotInvalidated() {
        // setup
        requestContextManager.onRequestSent(FlowType.REVERSAL, TEST_CORRELATION_ID, testRequest);

        // call
        requestContextManager.onResponseReceived(FlowType.REVERSAL, incorrectTestCorrId);

        // verify
        assertThat(requestContextManager.haveAnyOngoingRequests()).isTrue();

    }

    @Test
    void givenFilledCacheFinAdv_whenHaveOngoingRequests_returnTrue() {
        // setup
        requestContextManager.onRequestSent(FlowType.FINANCIAL_ADVICE, TEST_CORRELATION_ID, testRequest);

        // call
        boolean result = requestContextManager.haveOngoingRequests(FlowType.FINANCIAL_ADVICE);

        // verify
        assertThat(result).isTrue();
    }

    @Test
    void givenEmptyCacheFinAdv_whenHaveOngoingRequests_returnFalse() {
        // call
        boolean result = requestContextManager.haveOngoingRequests(FlowType.FINANCIAL_ADVICE);

        // verify
        assertThat(result).isFalse();
    }

    @Test
    void givenFilledCacheFinAdv_whenHaveAnyOngoingRequests_returnTrue() {
        // setup
        requestContextManager.onRequestSent(FlowType.FINANCIAL_ADVICE, TEST_CORRELATION_ID, testRequest);

        // call
        boolean result = requestContextManager.haveAnyOngoingRequests();

        // verify
        assertThat(result).isTrue();
    }

    @Test
    void givenFilledCacheFinAdv_whenOnResponseReceived_verifyCacheEntryIsInvalidated() {
        // setup
        requestContextManager.onRequestSent(FlowType.FINANCIAL_ADVICE, TEST_CORRELATION_ID, testRequest);

        // call
        requestContextManager.onResponseReceived(FlowType.FINANCIAL_ADVICE, TEST_CORRELATION_ID);

        // verify
        assertThat(requestContextManager.haveAnyOngoingRequests()).isFalse();
    }

    @Test
    void givenFilledCacheFinAdv_whenOnResponseReceivedForIncorrectCorrId_verifyCacheEntryIsNotInvalidated() {
        // setup
        requestContextManager.onRequestSent(FlowType.FINANCIAL_ADVICE, TEST_CORRELATION_ID, testRequest);

        // call
        requestContextManager.onResponseReceived(FlowType.FINANCIAL_ADVICE, incorrectTestCorrId);

        // verify
        assertThat(requestContextManager.haveAnyOngoingRequests()).isTrue();
    }

    @Test
    void givenFilledCacheAuth_whenHaveOngoingRequestsInq_returnFalse() {
        // setup
        requestContextManager.onRequestSent(FlowType.AUTHORISATION, TEST_CORRELATION_ID, testRequest);

        // call
        boolean result = requestContextManager.haveOngoingRequests(FlowType.INQUIRY);

        // verify
        assertThat(result).isFalse();
    }


    @Test
    void whenWaitUntilAllCompleted_thenVerifySleep() {
        // setup
        doReturn(true, false)
                .when(requestContextManager).haveAnyOngoingRequests();

        try (MockedStatic<ClientUtils> mockClientUtils = mockStatic(ClientUtils.class)) {
            mockClientUtils.when(() -> ClientUtils.sleep(any())).then(invocationOnMock -> null);

            // call
            requestContextManager.waitUntilAllCompleted();

            //verify
            mockClientUtils.verify(() -> ClientUtils.sleep(Duration.ofMillis(250)));
        }
    }

    @Test
    void whenRequestExpired_thenLog(CapturedOutput output) {
        // setup
        RequestContextManager.onRequestExpired(TEST_CORRELATION_ID, testRequest);

        // verify
        assertThat(output.getAll()).contains("Request InitiationAuthorisationInitiationV02 with correlationId=12345 has expired");
    }
}
