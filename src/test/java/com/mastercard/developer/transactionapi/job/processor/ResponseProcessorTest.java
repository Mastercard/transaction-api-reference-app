package com.mastercard.developer.transactionapi.job.processor;

import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import com.mastercard.developer.transactionapi.client.model.ResponseStatus;
import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.enums.FlowType;
import com.mastercard.developer.transactionapi.exception.TransactionApiException;
import com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.JSON;
import org.openapitools.client.model.AuthorisationResponseAuthorisationResponseV02;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CORRELATION_ID;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CUSTOMER_CONTEXT_KEY;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(OutputCaptureExtension.class)
class ResponseProcessorTest {

    @InjectMocks
    private ResponseProcessor responseProcessor;

    @Mock
    private TransactionApiProperties transactionApiProperties;

    private BatchResponse.BatchResponseBuilder<AuthorisationResponseAuthorisationResponseV02> testResponseStatus = TestRequestResponseGenerator.generateBatchBuilder(TEST_CORRELATION_ID,TEST_CUSTOMER_CONTEXT_KEY,HttpStatus.SC_OK);
    private BatchResponse.BatchResponseBuilder<AuthorisationResponseAuthorisationResponseV02> testResponseStatusPayloadError = TestRequestResponseGenerator.generateBatchBuilderWithPayloadAndErrors(TEST_CORRELATION_ID,TEST_CUSTOMER_CONTEXT_KEY,HttpStatus.SC_BAD_REQUEST);
    private BatchResponse.BatchResponseBuilder<AuthorisationResponseAuthorisationResponseV02> testResponseStatusError = TestRequestResponseGenerator.generateBatchBuilderWithErrors(TEST_CORRELATION_ID,TEST_CUSTOMER_CONTEXT_KEY,HttpStatus.SC_BAD_REQUEST);

    @BeforeEach
    void setup() {
        new JSON();
    }

    @Test
    void givenResponseWithHttpCodeOK_whenProcessResponseStatus_thenResponseWithPayload(CapturedOutput output) throws TransactionApiException {
        // setup
        ResponseStatus<AuthorisationResponseAuthorisationResponseV02> responseStatusItem = testResponseStatus.build().getItems().get(0);
        when(transactionApiProperties.isPayloadLoggingEnabled()).thenReturn(true);

        // call
        responseProcessor.processResponseStatus(responseStatusItem, FlowType.AUTHORISATION);

        // verify
        assertThat(output.getAll()).contains("Received successful AUTHORISATION response with correlationId=12345, customerContextKey=12345, httpStatus=200: {\"hdr\":{\"msgFctn\":\"UNSPECIFIED\"},\"body\":{}}");
    }

    @Test
    void givenResponseItemWithHttpStatusNOOK_whenProcessResponseStatus_thenResponseWithPayloadAndError(CapturedOutput output) throws TransactionApiException {
        // setup
        ResponseStatus<AuthorisationResponseAuthorisationResponseV02> responseStatusItem = testResponseStatusPayloadError.build().getItems().get(0);

        when(transactionApiProperties.isPayloadLoggingEnabled()).thenReturn(true);

        // call
        responseProcessor.processResponseStatus(responseStatusItem, FlowType.AUTHORISATION);

        // verify
        assertThat(output.getAll()).contains("Received unsuccessful AUTHORISATION response with correlationId=12345, customerContextKey=12345, httpStatus=400, errors=[class Error {\n" +
                "    source: Test\n" +
                "    reasonCode: FAILED\n" +
                "    description: Test Exception\n" +
                "    recoverable: false\n" +
                "    details: Test Failure\n" +
                "    payload: null\n" +
                "}]: {\"hdr\":{\"msgFctn\":\"UNSPECIFIED\"},\"body\":{}}");
    }

    @Test
    void givenResponseItemWithEmptyPayload_whenProcessResponseStatus_thenResponseWithError(CapturedOutput output) throws TransactionApiException {
        // setup
        ResponseStatus<AuthorisationResponseAuthorisationResponseV02> responseStatusItem = testResponseStatusError.build().getItems().get(0);

        // call
        responseProcessor.processResponseStatus(responseStatusItem, FlowType.AUTHORISATION);

        // verify
        assertThat(output.getAll()).contains("Received failed AUTHORISATION response with correlationId=12345, customerContextKey=12345, httpStatus=400, errors=[class Error {\n" +
                "    source: Test\n" +
                "    reasonCode: FAILED\n" +
                "    description: Test Exception\n" +
                "    recoverable: false\n" +
                "    details: Test Failure\n" +
                "    payload: null\n" +
                "}]");
    }

}