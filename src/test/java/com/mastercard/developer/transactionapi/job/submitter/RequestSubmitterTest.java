package com.mastercard.developer.transactionapi.job.submitter;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.AuthorisationResponseStatus;
import com.mastercard.developer.transactionapi.client.model.ResponseStatus;
import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.enums.FlowType;
import com.mastercard.developer.transactionapi.example.RequestExampleGenerator;
import com.mastercard.developer.transactionapi.exception.TransactionApiException;
import com.mastercard.developer.transactionapi.job.processor.ResponseProcessor;
import com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.JSON;
import org.openapitools.client.model.AuthorisationInitiationAuthorisationInitiationV02;
import org.openapitools.client.model.AuthorisationResponseAuthorisationResponseV02;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CORRELATION_ID;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CUSTOMER_CONTEXT_KEY;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getAuthErrorPayload;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getErrorWrapper;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.responseMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(OutputCaptureExtension.class)
class RequestSubmitterTest {

    private static final int TEST_COUNT = 2;

    @Mock
    private TransactionApiClient transactionApiClient;
    @Mock
    private RequestContextManager requestContextManager;
    @Mock
    private RequestExampleGenerator requestExampleGenerator;
    @Mock
    private TransactionApiProperties transactionApiProperties;
    @Mock
    private ResponseProcessor responseProcessor;

    private RequestSubmitter requestSubmitter;

    private AuthorisationInitiationAuthorisationInitiationV02 testRequest = TestRequestResponseGenerator.getTestAuthorisationInitiationV02();

    private ApiResponse<AuthorisationResponseAuthorisationResponseV02> testResponse = TestRequestResponseGenerator.getAuthorisationResponse(HttpStatus.SC_OK);
    private ApiResponse<AuthorisationResponseAuthorisationResponseV02> testResponseAccepted = TestRequestResponseGenerator.getAuthorisationResponse(HttpStatus.SC_ACCEPTED);

    private AuthorisationResponseStatus testStatus = TestRequestResponseGenerator
            .getAuthResponseItem(testResponse.getData(), TEST_CORRELATION_ID, TEST_CUSTOMER_CONTEXT_KEY, HttpStatus.SC_OK);
    private AuthorisationResponseStatus testResponseStatusError = TestRequestResponseGenerator.getAuthResponseItemWithErrors(TEST_CORRELATION_ID,TEST_CUSTOMER_CONTEXT_KEY,HttpStatus.SC_BAD_REQUEST);

    @BeforeEach
    void setup() {
        RequestSubmitter requestSubmitterSpy = new RequestSubmitter(FlowType.AUTHORISATION, transactionApiClient, requestContextManager, requestExampleGenerator, transactionApiProperties, responseProcessor) {
            @Override
            protected ApiResponse submitRequest(String customerContextKey, Object request) {
                return null;
            }

            @Override
            protected ResponseStatus toResponseStatus(String customerContextKey, ApiResponse response) {
                return null;
            }

            @Override
            protected ResponseStatus toResponseStatus(String customerContextKey, ApiException apiException) {
                return null;
            }

            @Override
            protected Object generateRequest() {
                return null;
            }

            @Override
            protected String requestToJson(Object request) {
                return "";
            }
        };
        requestSubmitter = spy(requestSubmitterSpy);

        new JSON();

        when(transactionApiProperties.isPayloadLoggingEnabled()).thenReturn(true);
        when(requestSubmitter.getRandomCustomerContextKey()).thenReturn(TEST_CUSTOMER_CONTEXT_KEY);
    }

    @Test
    void givenHttpOKResponses_whenSubmitRequests_thenProcessResponseStatus(CapturedOutput output) {
        // setup
        when(requestSubmitter.toResponseStatus(TEST_CUSTOMER_CONTEXT_KEY,testResponse)).thenReturn(testStatus);

        when(requestSubmitter.requestToJson(testRequest)).thenReturn(testRequest.toJson());
        when(requestSubmitter.generateRequest()).thenReturn(testRequest);
        when(requestSubmitter.submitRequest(TEST_CUSTOMER_CONTEXT_KEY, testRequest)).thenReturn(testResponse);

        // call
        requestSubmitter.submitRequests(TEST_COUNT);

        // verify
        verify(requestSubmitter, times(TEST_COUNT)).getRandomCustomerContextKey();
        verify(requestSubmitter, times(TEST_COUNT)).submitRequest(TEST_CUSTOMER_CONTEXT_KEY, testRequest);
        verify(responseProcessor, times(TEST_COUNT)).processResponseStatus(testStatus, FlowType.AUTHORISATION);

        assertThat(output.getAll()).contains("Submitting AUTHORISATION request with customerContextKey=12345: {\"hdr\":");
    }

    @Test
    void givenHttpAcceptedResponses_whenSubmitRequests_thenRegisterRequestSent(CapturedOutput output) {
        // setup
        when(requestSubmitter.generateRequest()).thenReturn(testRequest);

        when(requestSubmitter.requestToJson(testRequest)).thenReturn(testRequest.toJson());
        when(requestSubmitter.submitRequest(TEST_CUSTOMER_CONTEXT_KEY, testRequest)).thenReturn(testResponseAccepted);

        // call
        requestSubmitter.submitRequests(TEST_COUNT);

        // verify
        verify(requestSubmitter, times(TEST_COUNT)).getRandomCustomerContextKey();
        verify(requestSubmitter, times(TEST_COUNT)).submitRequest(TEST_CUSTOMER_CONTEXT_KEY, testRequest);
        verify(requestContextManager, times(TEST_COUNT)).onRequestSent(FlowType.AUTHORISATION, TEST_CUSTOMER_CONTEXT_KEY, testRequest);

        verifyNoInteractions(responseProcessor);

        assertThat(output.getAll()).contains("Submitting AUTHORISATION request with customerContextKey=12345: {\"hdr\":");
    }

    @Test
    void givenApiException_whenSubmitRequests_thenProcessResponseStatus(CapturedOutput output) {
        // setup
        String errorJson = getErrorWrapper(getAuthErrorPayload()).toJson();
        ApiException exception = new ApiException("Bad Request",HttpStatus.SC_BAD_REQUEST,responseMap,errorJson);

        when(requestSubmitter.toResponseStatus(TEST_CUSTOMER_CONTEXT_KEY,exception)).thenReturn(testResponseStatusError);
        when(requestSubmitter.generateRequest()).thenReturn(testRequest);
        when(requestSubmitter.submitRequest(TEST_CUSTOMER_CONTEXT_KEY, testRequest)).thenThrow(new TransactionApiException(exception.getMessage(),exception));

        // call
        requestSubmitter.submitRequests(TEST_COUNT);

        // verify
        verify(requestSubmitter, times(TEST_COUNT)).getRandomCustomerContextKey();
        verify(requestSubmitter, times(TEST_COUNT)).submitRequest(TEST_CUSTOMER_CONTEXT_KEY, testRequest);
        verify(responseProcessor, times(TEST_COUNT)).processResponseStatus(testResponseStatusError, FlowType.AUTHORISATION);

        verifyNoInteractions(requestContextManager);

        assertThat(output.getAll()).contains("Submitting AUTHORISATION request with customerContextKey=12345");
    }

    @Test
    void givenTransactionApiCauseNonApiException_whenSubmitRequests_thenLogError(CapturedOutput output) {
        // setup
        NullPointerException exception = new NullPointerException("NullPointerException");

        when(requestSubmitter.generateRequest()).thenReturn(testRequest);
        when(requestSubmitter.submitRequest(TEST_CUSTOMER_CONTEXT_KEY, testRequest)).thenThrow(new TransactionApiException(exception.getMessage(),exception));

        // call
        requestSubmitter.submitRequests(TEST_COUNT);

        // verify
        verify(requestSubmitter, times(TEST_COUNT)).getRandomCustomerContextKey();
        verify(requestSubmitter, times(TEST_COUNT)).submitRequest(TEST_CUSTOMER_CONTEXT_KEY, testRequest);

        assertThat(output.getAll()).contains("Failed to submit a request");
    }

    @Test
    void givenException_whenSubmitRequests_thenLogError(CapturedOutput output) {
        // setup
        NullPointerException exception = new NullPointerException("NullPointerException");

        when(requestSubmitter.generateRequest()).thenReturn(testRequest);
        when(requestSubmitter.submitRequest(TEST_CUSTOMER_CONTEXT_KEY, testRequest)).thenThrow(exception);

        // call
        requestSubmitter.submitRequests(TEST_COUNT);

        // verify
        verify(requestSubmitter, times(TEST_COUNT)).getRandomCustomerContextKey();
        verify(requestSubmitter, times(TEST_COUNT)).submitRequest(TEST_CUSTOMER_CONTEXT_KEY, testRequest);

        assertThat(output.getAll()).contains("Failed to submit a request");
    }

}