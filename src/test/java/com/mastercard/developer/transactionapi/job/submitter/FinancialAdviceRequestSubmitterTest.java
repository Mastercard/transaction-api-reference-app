package com.mastercard.developer.transactionapi.job.submitter;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.FinancialAdviceResponseStatus;
import com.mastercard.developer.transactionapi.client.model.ResponseStatus;
import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.example.RequestExampleGenerator;
import com.mastercard.developer.transactionapi.job.processor.ResponseProcessor;
import com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.JSON;
import org.openapitools.client.model.FinancialInitiationFinancialInitiationV02;
import org.openapitools.client.model.FinancialResponseFinancialResponseV02;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CORRELATION_ID;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CUSTOMER_CONTEXT_KEY;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getErrorWrapper;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getFinAdvErrorPayload;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.responseMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(OutputCaptureExtension.class)
class FinancialAdviceRequestSubmitterTest {

    @Spy
    @InjectMocks
    private FinancialAdviceRequestSubmitter financialAdviceRequestSubmitter;

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

    private FinancialInitiationFinancialInitiationV02 testRequest = TestRequestResponseGenerator.getTestFinancialInitiationV02();

    private ApiResponse<FinancialResponseFinancialResponseV02> testResponse = TestRequestResponseGenerator.getFinancialAdvResponse(HttpStatus.SC_OK);

    private FinancialAdviceResponseStatus testResponseStatus = TestRequestResponseGenerator.getFinAdvResponseItem(TEST_CORRELATION_ID,TEST_CUSTOMER_CONTEXT_KEY,HttpStatus.SC_OK);
    private FinancialAdviceResponseStatus testResponseStatusError = TestRequestResponseGenerator.getFinAdvResponseItemWithErrors(TEST_CORRELATION_ID,TEST_CUSTOMER_CONTEXT_KEY,HttpStatus.SC_BAD_REQUEST);

    @BeforeEach
    void setup() {
        new JSON();     // required to initialise Json.getGson() method
    }

    @Test
    void whenSubmitRequest_thenVerifyRequestSent() {
        // call
        financialAdviceRequestSubmitter.submitRequest(TEST_CUSTOMER_CONTEXT_KEY, testRequest);

        // verify
        verify(transactionApiClient).submitFinancialAdviceRequest(TEST_CUSTOMER_CONTEXT_KEY, testRequest);
    }

    @Test
    void givenApiResponse_whenToResponseStatus_thenReturnResponseStatus() {
        // call
        ResponseStatus<FinancialResponseFinancialResponseV02> actual = financialAdviceRequestSubmitter.toResponseStatus(TEST_CUSTOMER_CONTEXT_KEY, testResponse);

        // verify
        assertThat(actual.getHttpStatus()).isEqualTo(testResponseStatus.getHttpStatus());
        assertThat(actual.getPayload()).isEqualTo(testResponseStatus.getPayload());
        assertThat(actual.getErrors()).isEqualTo(testResponseStatus.getErrors());
        assertThat(actual.getCorrelationId()).isEqualTo(testResponseStatus.getCorrelationId());
        assertThat(actual.getCustomerContextKey()).isEqualTo(testResponseStatus.getCustomerContextKey());
    }

    @Test
    void givenApiException_whenToResponseStatus_thenReturnResponseStatus() {
        // setup
        FinancialResponseFinancialResponseV02 errorPayload = testResponseStatusError.getErrors().get(0).getPayload().getFinancialResponse();

        String errorJson = getErrorWrapper(getFinAdvErrorPayload()).toJson();
        ApiException exception = new ApiException("Bad Request",HttpStatus.SC_BAD_REQUEST,responseMap,errorJson);

        // call
        ResponseStatus<FinancialResponseFinancialResponseV02> actual = financialAdviceRequestSubmitter.toResponseStatus(TEST_CUSTOMER_CONTEXT_KEY, exception);

        // verify
        assertThat(actual.getHttpStatus()).isEqualTo(testResponseStatusError.getHttpStatus());
        assertThat(actual.getErrors().get(0).getPayload().getFinancialResponse()).isEqualTo(errorPayload);
        assertThat(actual.getErrors()).isEqualTo(testResponseStatusError.getErrors());
        assertThat(actual.getCorrelationId()).isEqualTo(testResponseStatusError.getCorrelationId());
        assertThat(actual.getCustomerContextKey()).isEqualTo(testResponseStatusError.getCustomerContextKey());
    }

    @Test
    void givenSubmitter_whenGenerateRequest_thenReturnRequestPayload() {
        // setup
        when(requestExampleGenerator.buildFinancialAdviceRequest()).thenReturn(testRequest);

        // call

        FinancialInitiationFinancialInitiationV02 actual = financialAdviceRequestSubmitter.generateRequest();

        // verify
        assertThat(actual).isEqualTo(testRequest);
    }

    @Test
    void givenSubmitter_whenRequestToJson_thenReturnJSONPayload() {
        // setup
        String expected = "{\"hdr\":{\"msgFctn\":\"ADVC\",\"initgPty\":{";

        // call
        String actual = financialAdviceRequestSubmitter.requestToJson(testRequest);

        // verify
        assertThat(actual).contains(expected);
    }
}