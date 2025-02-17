package com.mastercard.developer.transactionapi.client.impl;

import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import com.mastercard.developer.transactionapi.client.model.ResponseStatus;
import com.mastercard.developer.transactionapi.exception.TransactionApiException;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.api.TransactionApiApi;
import org.openapitools.client.model.AuthorisationAdviceResponseV02List;
import org.openapitools.client.model.AuthorisationResponseV02Status;
import org.openapitools.client.model.AuthorisationResponseV02List;
import org.openapitools.client.model.FinancialAdviceResponseV02Status;
import org.openapitools.client.model.FinancialAdviceResponseV02List;
import org.openapitools.client.model.AuthorisationInitiationAuthorisationInitiationV02;
import org.openapitools.client.model.FinancialInitiationFinancialInitiationV02;
import org.openapitools.client.model.FinancialRequestInitiationFinancialInitiationV02;
import org.openapitools.client.model.FinancialRequestResponseFinancialResponseV02;
import org.openapitools.client.model.FinancialRequestResponseV02List;
import org.openapitools.client.model.FinancialRequestResponseV02Status;
import org.openapitools.client.model.FinancialReversalAdviceResponseV02List;
import org.openapitools.client.model.FinancialReversalAdviceResponseV02Status;
import org.openapitools.client.model.InquiryInitiationInquiryInitiationV01;
import org.openapitools.client.model.ReversalFinancialAdviceInitiationReversalInitiationV02;
import org.openapitools.client.model.ReversalFinancialAdviceResponseReversalResponseV02;
import org.openapitools.client.model.ReversalInitiationReversalInitiationV02;
import org.openapitools.client.model.InquiryResponseV01Status;
import org.openapitools.client.model.InquiryResponseV01List;
import org.openapitools.client.model.AuthorisationResponseAuthorisationResponseV02;
import org.openapitools.client.model.FinancialResponseFinancialResponseV02;
import org.openapitools.client.model.InquiryResponseInquiryResponseV01;
import org.openapitools.client.model.ReversalResponseReversalResponseV02;
import org.openapitools.client.model.ReversalResponseV02Status;
import org.openapitools.client.model.ReversalResponseV02List;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mastercard.developer.transactionapi.test.TestConstants.CORRELATION_ID_HEADER;
import static com.mastercard.developer.transactionapi.test.TestConstants.HTTP_STATUS_TOO_EARLY;
import static com.mastercard.developer.transactionapi.test.TestConstants.RETRY_AFTER_MS_HEADER;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_API_EXCEPTION_MESSAGE_AUTH;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_API_EXCEPTION_MESSAGE_AUTHADVC;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_API_EXCEPTION_MESSAGE_FIN;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_API_EXCEPTION_MESSAGE_FINREQ;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_API_EXCEPTION_MESSAGE_FINREV;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_API_EXCEPTION_MESSAGE_INQ;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_API_EXCEPTION_MESSAGE_REV;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_BATCH_LIMIT;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CORRELATION_ID;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CUSTOMER_CONTEXT_KEY;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_RETRY_AFTER;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_RETRY_AFTER_MS;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getAuthorisationAdviceResponse;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getAuthorisationAdviceResponseList;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getAuthorisationResponse;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getAuthorisationResponseList;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getFinancialAdvResponse;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getFinancialAdvResponseList;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getFinancialReqResponse;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getFinancialReqResponseList;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getFinancialRevAdvResponse;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getFinancialRevAdvResponseList;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getInquiryResponse;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getInquiryResponseList;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getReversalResponse;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getReversalResponseList;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getTestAuthorisationAdviceInitiationV02;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getTestAuthorisationInitiationV02;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getTestFinancialInitiationV02;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getTestFinancialRequestInitiationV02;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getTestFinancialReversalAdviceInitiationV02;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getTestInquiryInitiationV01;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getTestReversalInitiationV02;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(OutputCaptureExtension.class)
@ExtendWith(MockitoExtension.class)
class TransactionApiClientImplTest {

    @InjectMocks
    private TransactionApiClientImpl transactionApiClient;
    @Mock
    private TransactionApiApi transactionApiApi;

    private final Map<String, List<String>> testPostResponseHeaders = new HashMap<String, List<String>>() {{
        put(CORRELATION_ID_HEADER, Collections.singletonList(TEST_CORRELATION_ID));
    }};

    private final Map<String, List<String>> testGetResponseHeaders = new HashMap<String, List<String>>() {{
        put(CORRELATION_ID_HEADER, Collections.singletonList(TEST_CORRELATION_ID));
        put(RETRY_AFTER_MS_HEADER, Collections.singletonList(Long.toString(TEST_RETRY_AFTER_MS)));
    }};

    private final Map<String, List<String>> testGetPartialResponseHeaders = new HashMap<String, List<String>>() {{
        put(CORRELATION_ID_HEADER, Collections.singletonList(TEST_CORRELATION_ID));
    }};

    private final ApiResponse<Void> testPostApiResponse = new ApiResponse<>(HttpStatus.SC_ACCEPTED, testPostResponseHeaders);

    private final ApiException testApiException = new ApiException(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Failed");

    private final AuthorisationInitiationAuthorisationInitiationV02 testAuthInitiation = getTestAuthorisationInitiationV02();
    private final ReversalInitiationReversalInitiationV02 testRevInitiation = getTestReversalInitiationV02();
    private final InquiryInitiationInquiryInitiationV01 testInqInitiation = getTestInquiryInitiationV01();
    private final FinancialInitiationFinancialInitiationV02 testFinAdvInitiation = getTestFinancialInitiationV02();
    private final FinancialRequestInitiationFinancialInitiationV02 testFinReqInitiation = getTestFinancialRequestInitiationV02();
    private final ReversalFinancialAdviceInitiationReversalInitiationV02 testFinRevAdvInitiation = getTestFinancialReversalAdviceInitiationV02();
    private final AuthorisationInitiationAuthorisationInitiationV02 testAuthAdviceInitiation = getTestAuthorisationAdviceInitiationV02();

    private final ApiResponse<AuthorisationResponseAuthorisationResponseV02> authResponse = getAuthorisationResponse(HttpStatus.SC_OK);
    private final ApiResponse<FinancialResponseFinancialResponseV02> financialAdvResponse = getFinancialAdvResponse(HttpStatus.SC_OK);
    private final ApiResponse<InquiryResponseInquiryResponseV01> inquiryResponse = getInquiryResponse(HttpStatus.SC_OK);
    private final ApiResponse<ReversalResponseReversalResponseV02> reversalResponse = getReversalResponse(HttpStatus.SC_OK);
    private final ApiResponse<FinancialRequestResponseFinancialResponseV02> finReqResResponse = getFinancialReqResponse(HttpStatus.SC_OK);
    private final ApiResponse<ReversalFinancialAdviceResponseReversalResponseV02> finRevAdvResponse = getFinancialRevAdvResponse(HttpStatus.SC_OK);
    private final ApiResponse<AuthorisationResponseAuthorisationResponseV02> authAdviceResponse = getAuthorisationAdviceResponse(HttpStatus.SC_OK);

    private final AuthorisationResponseV02List authList = getAuthorisationResponseList(HttpStatus.SC_OK);
    private final FinancialAdviceResponseV02List financialAdvList = getFinancialAdvResponseList(HttpStatus.SC_OK);
    private final InquiryResponseV01List inquiryList = getInquiryResponseList(HttpStatus.SC_OK);
    private final ReversalResponseV02List reversalList = getReversalResponseList(HttpStatus.SC_OK);
    private final FinancialRequestResponseV02List finReqResList = getFinancialReqResponseList(HttpStatus.SC_OK);
    private final FinancialReversalAdviceResponseV02List finRevAdvList = getFinancialRevAdvResponseList(HttpStatus.SC_OK);
    private final AuthorisationAdviceResponseV02List authAdviceList = getAuthorisationAdviceResponseList(HttpStatus.SC_OK);

    @Test
    void givenHappyPath_whenSubmitAuthorisationRequest_thenReturnResponse() throws ApiException {
        // setup
        when(transactionApiApi.processAuthorisationRequestWithHttpInfo(TEST_CUSTOMER_CONTEXT_KEY, testAuthInitiation))
                .thenReturn(authResponse);

        // call
        ApiResponse<AuthorisationResponseAuthorisationResponseV02> actual = transactionApiClient.submitAuthorisationRequest(TEST_CUSTOMER_CONTEXT_KEY, testAuthInitiation);

        // verify
        assertThat(actual).isEqualTo(authResponse);
    }

    @Test
    void givenException_whenSubmitAuthorisationRequest_thenThrowException() throws ApiException {
        // setup
        when(transactionApiApi.processAuthorisationRequestWithHttpInfo(TEST_CUSTOMER_CONTEXT_KEY, testAuthInitiation))
                .thenThrow(testApiException);

        // call
        Exception e = assertThrows(TransactionApiException.class, () ->
                transactionApiClient.submitAuthorisationRequest(TEST_CUSTOMER_CONTEXT_KEY, testAuthInitiation));

        // verify
        assertThat(e.getMessage()).isEqualTo(TEST_API_EXCEPTION_MESSAGE_AUTH);
        assertThat(e.getCause()).isSameAs(testApiException);
    }

    @Test
    void givenHappyPath_whenSubmitAuthorisationAdviceRequest_thenReturnResponse() throws ApiException {
        // setup
        when(transactionApiApi.processAuthorisationAdviceRequestWithHttpInfo(TEST_CUSTOMER_CONTEXT_KEY, testAuthAdviceInitiation))
                .thenReturn(authAdviceResponse);

        // call
        ApiResponse<AuthorisationResponseAuthorisationResponseV02> actual = transactionApiClient.submitAuthorisationAdviceRequest(TEST_CUSTOMER_CONTEXT_KEY, testAuthAdviceInitiation);

        // verify
        assertThat(actual).isEqualTo(authAdviceResponse);
    }

    @Test
    void givenException_whenSubmitAuthorisationAdviceRequest_thenThrowException() throws ApiException {
        // setup
        when(transactionApiApi.processAuthorisationAdviceRequestWithHttpInfo(TEST_CUSTOMER_CONTEXT_KEY, testAuthAdviceInitiation))
                .thenThrow(testApiException);

        // call
        Exception e = assertThrows(TransactionApiException.class, () ->
                transactionApiClient.submitAuthorisationAdviceRequest(TEST_CUSTOMER_CONTEXT_KEY, testAuthAdviceInitiation));

        // verify
        assertThat(e.getMessage()).isEqualTo(TEST_API_EXCEPTION_MESSAGE_AUTHADVC);
        assertThat(e.getCause()).isSameAs(testApiException);
    }

    @Test
    void givenHappyPath_whenSubmitReversalRequest_thenReturnResponse() throws ApiException {
        // setup
        when(transactionApiApi.processReversalRequestWithHttpInfo(TEST_CUSTOMER_CONTEXT_KEY, testRevInitiation))
                .thenReturn(reversalResponse);

        // call
        ApiResponse<ReversalResponseReversalResponseV02> actual = transactionApiClient.submitReversalRequest(TEST_CUSTOMER_CONTEXT_KEY, testRevInitiation);

        // verify
        assertThat(actual).isEqualTo(reversalResponse);
    }

    @Test
    void givenException_whenSubmitReversalRequest_thenThrowException() throws ApiException {
        // setup
        when(transactionApiApi.processReversalRequestWithHttpInfo(TEST_CUSTOMER_CONTEXT_KEY, testRevInitiation))
                .thenThrow(testApiException);

        // call
        Exception e = assertThrows(TransactionApiException.class, () ->
                transactionApiClient.submitReversalRequest(TEST_CUSTOMER_CONTEXT_KEY, testRevInitiation));

        // verify
        assertThat(e.getMessage()).isEqualTo(TEST_API_EXCEPTION_MESSAGE_REV);
        assertThat(e.getCause()).isSameAs(testApiException);
    }

    @Test
    void givenHappyPath_whenSubmitInquiryRequest_thenReturnResponse() throws ApiException {
        // setup
        when(transactionApiApi.processInquiryRequestWithHttpInfo(TEST_CUSTOMER_CONTEXT_KEY, testInqInitiation))
                .thenReturn(inquiryResponse);

        // call
        ApiResponse<InquiryResponseInquiryResponseV01> actual = transactionApiClient.submitInquiryRequest(TEST_CUSTOMER_CONTEXT_KEY, testInqInitiation);

        // verify
        assertThat(actual).isEqualTo(inquiryResponse);
    }

    @Test
    void givenException_whenSubmitInquiryRequest_thenThrowException() throws ApiException {
        // setup
        when(transactionApiApi.processInquiryRequestWithHttpInfo(TEST_CUSTOMER_CONTEXT_KEY, testInqInitiation))
                .thenThrow(testApiException);

        // call
        Exception e = assertThrows(TransactionApiException.class, () ->
                transactionApiClient.submitInquiryRequest(TEST_CUSTOMER_CONTEXT_KEY, testInqInitiation));

        // verify
        assertThat(e.getMessage()).isEqualTo(TEST_API_EXCEPTION_MESSAGE_INQ);
        assertThat(e.getCause()).isSameAs(testApiException);
    }

    @Test
    void givenHappyPath_whenSubmitFinancialAdviceRequest_thenReturnResponse() throws ApiException {
        // setup
        when(transactionApiApi.processFinancialAdviceRequestWithHttpInfo(TEST_CUSTOMER_CONTEXT_KEY, testFinAdvInitiation))
                .thenReturn(financialAdvResponse);

        // call
        ApiResponse<FinancialResponseFinancialResponseV02> actual = transactionApiClient.submitFinancialAdviceRequest(TEST_CUSTOMER_CONTEXT_KEY, testFinAdvInitiation);

        // verify
        assertThat(actual).isEqualTo(financialAdvResponse);
    }

    @Test
    void givenException_whenSubmitFinancialAdviceRequest_thenThrowException() throws ApiException {
        // setup
        when(transactionApiApi.processFinancialAdviceRequestWithHttpInfo(TEST_CUSTOMER_CONTEXT_KEY, testFinAdvInitiation))
                .thenThrow(testApiException);

        // call
        Exception e = assertThrows(TransactionApiException.class, () ->
                transactionApiClient.submitFinancialAdviceRequest(TEST_CUSTOMER_CONTEXT_KEY, testFinAdvInitiation));

        // verify
        assertThat(e.getMessage()).isEqualTo(TEST_API_EXCEPTION_MESSAGE_FIN);
        assertThat(e.getCause()).isSameAs(testApiException);
    }

    @Test
    void givenHappyPath_whenSubmitFinancialRequest_thenReturnResponse() throws ApiException {
        // setup
        when(transactionApiApi.processFinancialRequestWithHttpInfo(TEST_CUSTOMER_CONTEXT_KEY, testFinReqInitiation))
                .thenReturn(finReqResResponse);

        // call
        ApiResponse<FinancialRequestResponseFinancialResponseV02> actual = transactionApiClient.submitFinancialRequest(TEST_CUSTOMER_CONTEXT_KEY, testFinReqInitiation);

        // verify
        assertThat(actual).isEqualTo(finReqResResponse);
    }

    @Test
    void givenException_whenSubmitFinancialRequest_thenThrowException() throws ApiException {
        // setup
        when(transactionApiApi.processFinancialRequestWithHttpInfo(TEST_CUSTOMER_CONTEXT_KEY, testFinReqInitiation))
                .thenThrow(testApiException);

        // call
        Exception e = assertThrows(TransactionApiException.class, () ->
                transactionApiClient.submitFinancialRequest(TEST_CUSTOMER_CONTEXT_KEY, testFinReqInitiation));

        // verify
        assertThat(e.getMessage()).isEqualTo(TEST_API_EXCEPTION_MESSAGE_FINREQ);
        assertThat(e.getCause()).isSameAs(testApiException);
    }

    @Test
    void givenHappyPath_whenSubmitFinancialReversalAdvice_thenReturnResponse() throws ApiException {
        // setup
        when(transactionApiApi.processFinancialReversalAdviceWithHttpInfo(TEST_CUSTOMER_CONTEXT_KEY, testFinRevAdvInitiation))
                .thenReturn(finRevAdvResponse);

        // call
        ApiResponse<ReversalFinancialAdviceResponseReversalResponseV02> actual = transactionApiClient.submitFinancialReversalAdvice(TEST_CUSTOMER_CONTEXT_KEY, testFinRevAdvInitiation);

        // verify
        assertThat(actual).isEqualTo(finRevAdvResponse);
    }

    @Test
    void givenException_whenSubmitFinancialReversalAdvice_thenThrowException() throws ApiException {
        // setup
        when(transactionApiApi.processFinancialReversalAdviceWithHttpInfo(TEST_CUSTOMER_CONTEXT_KEY, testFinRevAdvInitiation))
                .thenThrow(testApiException);

        // call
        Exception e = assertThrows(TransactionApiException.class, () ->
                transactionApiClient.submitFinancialReversalAdvice(TEST_CUSTOMER_CONTEXT_KEY, testFinRevAdvInitiation));

        // verify
        assertThat(e.getMessage()).isEqualTo(TEST_API_EXCEPTION_MESSAGE_FINREV);
        assertThat(e.getCause()).isSameAs(testApiException);
    }

    @Test
    void givenHappyPath_whenGetAuthorisationResponsesByCustomerContextKey_thenReturnResponse(CapturedOutput output) throws ApiException {

        // setup
        AuthorisationResponseV02List list = new AuthorisationResponseV02List().items(Arrays.asList(new AuthorisationResponseV02Status()
                .customerContextKey(TEST_CUSTOMER_CONTEXT_KEY)
                .payload(authResponse.getData())
                .httpStatus(HttpStatus.SC_OK)));
        ApiResponse<AuthorisationResponseV02List> response =
                new ApiResponse<>(HttpStatus.SC_OK, testGetResponseHeaders, list);
        when(transactionApiApi.getAuthorisationResponsesWithHttpInfo(TEST_BATCH_LIMIT, null, null)).thenReturn(response);

        AuthorisationResponseV02Status expectedItem1 = list.getItems().get(0);

        // call
        BatchResponse<AuthorisationResponseAuthorisationResponseV02> responseActual = transactionApiClient.getAuthorisationResponses();

        // verify
        ResponseStatus<AuthorisationResponseAuthorisationResponseV02> responseActualItem1 = responseActual.getItems().get(0);

        assertThat(responseActual.isHasMore()).isFalse();
        assertThat(responseActual.getRetryAfter()).isEqualTo(TEST_RETRY_AFTER);

        assertThat(responseActualItem1.getCorrelationId()).isEqualTo(expectedItem1.getCorrelationId());

        assertThat(responseActualItem1.getHttpStatus()).isEqualTo(expectedItem1.getHttpStatus());

        assertThat(responseActualItem1.getPayload()).isEqualTo(expectedItem1.getPayload());

        assertThat(responseActualItem1.getErrors()).isEqualTo(expectedItem1.getErrors());

        assertThat(output.getAll()).contains("Completed Transaction API getAuthorisationResponses, httpStatus=200, itemsCount=1");
    }

    @Test
    void givenHappyPath_whenGetFinancialAdvResponsesByCustomerContextKey_thenReturnResponse(CapturedOutput output) throws ApiException {
        // setup
        FinancialAdviceResponseV02List list = new FinancialAdviceResponseV02List().items(Arrays.asList(new FinancialAdviceResponseV02Status()
                .customerContextKey(TEST_CUSTOMER_CONTEXT_KEY)
                .payload(financialAdvResponse.getData())
                .httpStatus(HttpStatus.SC_OK)));
        ApiResponse<FinancialAdviceResponseV02List> response =
                new ApiResponse<>(HttpStatus.SC_OK, testGetResponseHeaders, list);
        when(transactionApiApi.getFinancialAdviceResponsesWithHttpInfo(TEST_BATCH_LIMIT, null, null)).thenReturn(response);

        FinancialAdviceResponseV02Status expectedItem1 = list.getItems().get(0);

        // call
        BatchResponse<FinancialResponseFinancialResponseV02> responseActual = transactionApiClient.getFinancialAdviceResponses();

        // verify
        ResponseStatus<FinancialResponseFinancialResponseV02> responseActualItem1 = responseActual.getItems().get(0);

        assertThat(responseActual.isHasMore()).isFalse();
        assertThat(responseActual.getRetryAfter()).isEqualTo(TEST_RETRY_AFTER);

        assertThat(responseActualItem1.getCorrelationId()).isEqualTo(expectedItem1.getCorrelationId());

        assertThat(responseActualItem1.getHttpStatus()).isEqualTo(expectedItem1.getHttpStatus());

        assertThat(responseActualItem1.getPayload()).isEqualTo(expectedItem1.getPayload());

        assertThat(responseActualItem1.getErrors()).isEqualTo(expectedItem1.getErrors());

        assertThat(output.getAll()).contains("Completed Transaction API getFinancialAdviceResponses, httpStatus=200, itemsCount=1");
    }

    @Test
    void givenHappyPath_whenGetFinancialReqResponsesByCustomerContextKey_thenReturnResponse_thenReturnBatchResponse(CapturedOutput output) throws ApiException {
        // setup
        FinancialRequestResponseV02List list = new FinancialRequestResponseV02List().items(Arrays.asList(new FinancialRequestResponseV02Status()
                .customerContextKey(TEST_CUSTOMER_CONTEXT_KEY)
                .payload(finReqResResponse.getData())
                .httpStatus(HttpStatus.SC_OK)));
        ApiResponse<FinancialRequestResponseV02List> response =
                new ApiResponse<>(HttpStatus.SC_OK, testGetResponseHeaders, list);
        when(transactionApiApi.getFinancialRequestResponsesWithHttpInfo(TEST_BATCH_LIMIT, null, null)).thenReturn(response);

        FinancialRequestResponseV02Status expectedItem1 = list.getItems().get(0);

        // call
        BatchResponse<FinancialRequestResponseFinancialResponseV02> responseActual = transactionApiClient.getFinancialRequestResponses();

        // verify
        ResponseStatus<FinancialRequestResponseFinancialResponseV02> responseActualItem1 = responseActual.getItems().get(0);

        assertThat(responseActual.isHasMore()).isFalse();
        assertThat(responseActual.getRetryAfter()).isEqualTo(TEST_RETRY_AFTER);

        assertThat(responseActualItem1.getCorrelationId()).isEqualTo(expectedItem1.getCorrelationId());

        assertThat(responseActualItem1.getHttpStatus()).isEqualTo(expectedItem1.getHttpStatus());

        assertThat(responseActualItem1.getPayload()).isEqualTo(expectedItem1.getPayload());

        assertThat(responseActualItem1.getErrors()).isEqualTo(expectedItem1.getErrors());

        assertThat(output.getAll()).contains("Completed Transaction API getFinancialRequestResponses, httpStatus=200, itemsCount=1");
    }

    @Test
    void givenHappyPath_whenGetFinancialReversalAdvResponsesByCustomerContextKey_thenReturnResponse(CapturedOutput output) throws ApiException {
        // setup
        FinancialReversalAdviceResponseV02List list = new FinancialReversalAdviceResponseV02List().items(Arrays.asList(new FinancialReversalAdviceResponseV02Status()
                .customerContextKey(TEST_CUSTOMER_CONTEXT_KEY)
                .payload(finRevAdvResponse.getData())
                .httpStatus(HttpStatus.SC_OK)));
        ApiResponse<FinancialReversalAdviceResponseV02List> response =
                new ApiResponse<>(HttpStatus.SC_OK, testGetResponseHeaders, list);
        when(transactionApiApi.getFinancialReversalAdviceResponsesWithHttpInfo(TEST_BATCH_LIMIT, null, null)).thenReturn(response);

        FinancialReversalAdviceResponseV02Status expectedItem1 = list.getItems().get(0);

        // call
        BatchResponse<ReversalFinancialAdviceResponseReversalResponseV02> responseActual = transactionApiClient.getFinancialReversalAdviceResponses();

        // verify
        ResponseStatus<ReversalFinancialAdviceResponseReversalResponseV02> responseActualItem1 = responseActual.getItems().get(0);

        assertThat(responseActual.isHasMore()).isFalse();
        assertThat(responseActual.getRetryAfter()).isEqualTo(TEST_RETRY_AFTER);

        assertThat(responseActualItem1.getCorrelationId()).isEqualTo(expectedItem1.getCorrelationId());

        assertThat(responseActualItem1.getHttpStatus()).isEqualTo(expectedItem1.getHttpStatus());

        assertThat(responseActualItem1.getPayload()).isEqualTo(expectedItem1.getPayload());

        assertThat(responseActualItem1.getErrors()).isEqualTo(expectedItem1.getErrors());

        assertThat(output.getAll()).contains("Completed Transaction API getFinancialReversalAdviceResponses, httpStatus=200, itemsCount=1");
    }

    @Test
    void givenHappyPath_whenGetInquiryResponsesByCustomerContextKey_thenReturnResponse(CapturedOutput output) throws ApiException {
        // setup
        InquiryResponseV01List list = new InquiryResponseV01List().items(Arrays.asList(new InquiryResponseV01Status()
                .customerContextKey(TEST_CUSTOMER_CONTEXT_KEY)
                .payload(inquiryResponse.getData())
                .httpStatus(HttpStatus.SC_OK)));
        ApiResponse<InquiryResponseV01List> response =
                new ApiResponse<>(HttpStatus.SC_OK, testGetResponseHeaders, list);
        when(transactionApiApi.getInquiryResponsesWithHttpInfo(TEST_BATCH_LIMIT, null, null)).thenReturn(response);

        InquiryResponseV01Status expectedItem1 = list.getItems().get(0);

        // call
        BatchResponse<InquiryResponseInquiryResponseV01> responseActual = transactionApiClient.getInquiryResponses();

        // verify
        ResponseStatus<InquiryResponseInquiryResponseV01> responseActualItem1 = responseActual.getItems().get(0);

        assertThat(responseActual.isHasMore()).isFalse();
        assertThat(responseActual.getRetryAfter()).isEqualTo(TEST_RETRY_AFTER);

        assertThat(responseActualItem1.getCorrelationId()).isEqualTo(expectedItem1.getCorrelationId());

        assertThat(responseActualItem1.getHttpStatus()).isEqualTo(expectedItem1.getHttpStatus());

        assertThat(responseActualItem1.getPayload()).isEqualTo(expectedItem1.getPayload());

        assertThat(responseActualItem1.getErrors()).isEqualTo(expectedItem1.getErrors());

        assertThat(output.getAll()).contains("Completed Transaction API getInquiryResponses, httpStatus=200, itemsCount=1");
    }

    @Test
    void givenHappyPath_whenGetReversalResponsesByCustomerContextKey_thenReturnResponse(CapturedOutput output) throws ApiException {
        // setup
        ReversalResponseV02List list = new ReversalResponseV02List().items(Arrays.asList(new ReversalResponseV02Status()
                .customerContextKey(TEST_CUSTOMER_CONTEXT_KEY)
                .payload(reversalResponse.getData())
                .httpStatus(HttpStatus.SC_OK)));
        ApiResponse<ReversalResponseV02List> response =
                new ApiResponse<>(HttpStatus.SC_OK, testGetResponseHeaders, list);
        when(transactionApiApi.getReversalResponsesWithHttpInfo(TEST_BATCH_LIMIT, null, null)).thenReturn(response);

        ReversalResponseV02Status expectedItem1 = list.getItems().get(0);

        // call
        BatchResponse<ReversalResponseReversalResponseV02> responseActual = transactionApiClient.getReversalResponses();

        // verify
        ResponseStatus<ReversalResponseReversalResponseV02> responseActualItem1 = responseActual.getItems().get(0);

        assertThat(responseActual.isHasMore()).isFalse();
        assertThat(responseActual.getRetryAfter()).isEqualTo(TEST_RETRY_AFTER);

        assertThat(responseActualItem1.getCorrelationId()).isEqualTo(expectedItem1.getCorrelationId());

        assertThat(responseActualItem1.getHttpStatus()).isEqualTo(expectedItem1.getHttpStatus());

        assertThat(responseActualItem1.getPayload()).isEqualTo(expectedItem1.getPayload());

        assertThat(responseActualItem1.getErrors()).isEqualTo(expectedItem1.getErrors());

        assertThat(output.getAll()).contains("Completed Transaction API getReversalResponses, httpStatus=200, itemsCount=1");
    }

    @Test
    void givenTooEarlyStatusCode_whenGetReversalResponses_thenVerifyTooEarlyResponse(CapturedOutput output) throws ApiException {
        // setup
        when(transactionApiApi.getReversalResponsesWithHttpInfo(TEST_BATCH_LIMIT, null, null))
                .thenThrow(new ApiException(HTTP_STATUS_TOO_EARLY, testGetResponseHeaders, null));

        // call
        BatchResponse<ReversalResponseReversalResponseV02> responseActual = transactionApiClient.getReversalResponses();

        // verify
        assertThat(responseActual.getItems()).isEqualTo(Collections.EMPTY_LIST);
        assertThat(responseActual.isHasMore()).isFalse();
        assertThat(responseActual.getRetryAfter()).isEqualTo(TEST_RETRY_AFTER);

        assertThat(output.getAll()).contains("Completed Transaction API getReversalResponses, httpStatus=425, itemsCount=0");
    }

    @Test
    void givenException_whenGetAuthorisationResponses_thenThrowTransactionApiException() throws ApiException {
        // setup
        doThrow(testApiException).when(transactionApiApi).getAuthorisationResponsesWithHttpInfo(TEST_BATCH_LIMIT, null, null);

        // call
        Exception e = assertThrows(TransactionApiException.class, () ->
                transactionApiClient.getAuthorisationResponses());

        // verify
        assertThat(e.getMessage()).contains("Failed to call getAuthorisationResponses");
        assertThat(e.getCause()).isSameAs(testApiException);
    }

    @Test
    void givenException_whenGetAuthorisationAdviceResponses_thenThrowTransactionApiException() throws ApiException {
        // setup
        doThrow(testApiException).when(transactionApiApi).getAuthorisationAdviceResponsesWithHttpInfo(TEST_BATCH_LIMIT, null, null);

        // call
        Exception e = assertThrows(TransactionApiException.class, () ->
                transactionApiClient.getAuthorisationAdviceResponses());

        // verify
        assertThat(e.getMessage()).contains("Failed to call getAuthorisationAdviceResponses");
        assertThat(e.getCause()).isSameAs(testApiException);
    }

    @Test
    void givenException_whenGetFinancialAdvResponses_thenThrowTransactionApiException() throws ApiException {
        // setup
        doThrow(testApiException).when(transactionApiApi).getFinancialAdviceResponsesWithHttpInfo(TEST_BATCH_LIMIT, null, null);

        // call
        Exception e = assertThrows(TransactionApiException.class, () -> transactionApiClient.getFinancialAdviceResponses());

        // verify
        assertThat(e.getMessage()).contains("Failed to call getFinancialAdviceResponses");
        assertThat(e.getCause()).isSameAs(testApiException);
    }

    @Test
    void givenException_whenGetFinancialReqResponses_thenThrowTransactionApiException() throws ApiException {
        // setup
        doThrow(testApiException).when(transactionApiApi).getFinancialRequestResponsesWithHttpInfo(TEST_BATCH_LIMIT, null, null);

        // call
        Exception e = assertThrows(TransactionApiException.class, () -> transactionApiClient.getFinancialRequestResponses());

        // verify
        assertThat(e.getMessage()).contains("Failed to call getFinancialRequestResponses");
        assertThat(e.getCause()).isSameAs(testApiException);
    }

    @Test
    void givenException_whenGetFinancialReversalAdvResponses_thenThrowTransactionApiException() throws ApiException {
        // setup
        doThrow(testApiException).when(transactionApiApi).getFinancialReversalAdviceResponsesWithHttpInfo(TEST_BATCH_LIMIT, null, null);

        // call
        Exception e = assertThrows(TransactionApiException.class, () -> transactionApiClient.getFinancialReversalAdviceResponses());

        // verify
        assertThat(e.getMessage()).contains("Failed to call getFinancialReversalAdviceResponses");
        assertThat(e.getCause()).isSameAs(testApiException);
    }

    @Test
    void givenException_whenGetInquiryResponses_thenThrowTransactionApiException() throws ApiException {
        // setup
        doThrow(testApiException).when(transactionApiApi).getInquiryResponsesWithHttpInfo(TEST_BATCH_LIMIT, null, null);

        // call
        Exception e = assertThrows(TransactionApiException.class, () -> transactionApiClient.getInquiryResponses());

        // verify
        assertThat(e.getMessage()).contains("Failed to call getInquiryResponses");
        assertThat(e.getCause()).isSameAs(testApiException);
    }

    @Test
    void givenException_whenGetReversalResponses_thenThrowTransactionApiException() throws ApiException {
        // setup
        doThrow(testApiException).when(transactionApiApi).getReversalResponsesWithHttpInfo(TEST_BATCH_LIMIT, null, null);

        // call
        Exception e = assertThrows(TransactionApiException.class, () -> transactionApiClient.getReversalResponses());

        // verify
        assertThat(e.getMessage()).contains("Failed to call getReversalResponses");
        assertThat(e.getCause()).isSameAs(testApiException);
    }

}