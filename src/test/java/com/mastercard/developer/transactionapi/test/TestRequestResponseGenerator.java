package com.mastercard.developer.transactionapi.test;

import com.mastercard.developer.transactionapi.client.model.AuthorisationAdviceResponseStatus;
import com.mastercard.developer.transactionapi.client.model.AuthorisationResponseStatus;
import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import com.mastercard.developer.transactionapi.client.model.FinancialAdviceResponseStatus;
import com.mastercard.developer.transactionapi.client.model.FinancialRequestResponseStatus;
import com.mastercard.developer.transactionapi.client.model.FinancialReversalAdviceResponseStatus;
import com.mastercard.developer.transactionapi.client.model.InquiryResponseStatus;
import com.mastercard.developer.transactionapi.client.model.ResponseStatus;
import com.mastercard.developer.transactionapi.client.model.ReversalResponseStatus;
import com.mastercard.developer.transactionapi.example.RequestExampleGenerator;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.model.AuthorisationAdviceResponseV02List;
import org.openapitools.client.model.AuthorisationAdviceResponseV02Status;
import org.openapitools.client.model.AuthorisationInitiationAuthorisationInitiationV02;
import org.openapitools.client.model.AuthorisationResponseAuthorisationResponse1;
import org.openapitools.client.model.AuthorisationResponseAuthorisationResponseV02;
import org.openapitools.client.model.AuthorisationResponseHeader42;
import org.openapitools.client.model.AuthorisationResponseV02List;
import org.openapitools.client.model.AuthorisationResponseV02Status;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.ErrorPayload;
import org.openapitools.client.model.ErrorWrapper;
import org.openapitools.client.model.Errors;
import org.openapitools.client.model.FinancialAdviceResponseV02List;
import org.openapitools.client.model.FinancialAdviceResponseV02Status;
import org.openapitools.client.model.FinancialInitiationFinancialInitiationV02;
import org.openapitools.client.model.FinancialRequestInitiationFinancialInitiationV02;
import org.openapitools.client.model.FinancialRequestResponseFinancialResponse1;
import org.openapitools.client.model.FinancialRequestResponseFinancialResponseV02;
import org.openapitools.client.model.FinancialRequestResponseHeader42;
import org.openapitools.client.model.FinancialRequestResponseV02List;
import org.openapitools.client.model.FinancialRequestResponseV02Status;
import org.openapitools.client.model.FinancialResponseFinancialResponse1;
import org.openapitools.client.model.FinancialResponseFinancialResponseV02;
import org.openapitools.client.model.FinancialResponseHeader42;
import org.openapitools.client.model.FinancialReversalAdviceResponseV02List;
import org.openapitools.client.model.FinancialReversalAdviceResponseV02Status;
import org.openapitools.client.model.InquiryInitiationInquiryInitiationV01;
import org.openapitools.client.model.InquiryResponseHeader39;
import org.openapitools.client.model.InquiryResponseInquiryResponse1;
import org.openapitools.client.model.InquiryResponseInquiryResponseV01;
import org.openapitools.client.model.InquiryResponseV01List;
import org.openapitools.client.model.InquiryResponseV01Status;
import org.openapitools.client.model.ReversalFinancialAdviceInitiationReversalInitiationV02;
import org.openapitools.client.model.ReversalFinancialAdviceResponseHeader42;
import org.openapitools.client.model.ReversalFinancialAdviceResponseReversalResponse2;
import org.openapitools.client.model.ReversalFinancialAdviceResponseReversalResponseV02;
import org.openapitools.client.model.ReversalInitiationReversalInitiationV02;
import org.openapitools.client.model.ReversalResponseHeader42;
import org.openapitools.client.model.ReversalResponseReversalResponse2;
import org.openapitools.client.model.ReversalResponseReversalResponseV02;
import org.openapitools.client.model.ReversalResponseV02List;
import org.openapitools.client.model.ReversalResponseV02Status;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.mastercard.developer.transactionapi.test.TestConstants.CORRELATION_ID_HEADER;
import static com.mastercard.developer.transactionapi.test.TestConstants.CUSTOMER_CONTEXT_KEY_HEADER;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CORRELATION_ID;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CORRELATION_ID_1;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CORRELATION_ID_2;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CUSTOMER_CONTEXT_KEY;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CUSTOMER_CONTEXT_KEY_1;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CUSTOMER_CONTEXT_KEY_2;

public class  TestRequestResponseGenerator {

    private static final RequestExampleGenerator requestExampleGenerator = new RequestExampleGenerator();

    public static final Map<String, List<String>> responseMap = Map.of(CUSTOMER_CONTEXT_KEY_HEADER, List.of(TEST_CUSTOMER_CONTEXT_KEY),CORRELATION_ID_HEADER, List.of(TEST_CORRELATION_ID));

    public static AuthorisationResponseAuthorisationResponseV02 getResponseAuthResponseItem() {
        return new AuthorisationResponseAuthorisationResponseV02()
                .body(new AuthorisationResponseAuthorisationResponse1())
                .hdr(new AuthorisationResponseHeader42());
    }

    public static AuthorisationResponseAuthorisationResponseV02 getResponseAuthAdviceResponseItem() {
        return new AuthorisationResponseAuthorisationResponseV02()
                .body(new AuthorisationResponseAuthorisationResponse1())
                .hdr(new AuthorisationResponseHeader42());
    }

    public static FinancialResponseFinancialResponseV02 getResponseFinancialAdvResponseItem() {
        return new FinancialResponseFinancialResponseV02()
                .body(new FinancialResponseFinancialResponse1())
                .hdr(new FinancialResponseHeader42());
    }

    public static FinancialRequestResponseFinancialResponseV02 getResponseFinancialReqResponseItem() {
        return new FinancialRequestResponseFinancialResponseV02()
                .body(new FinancialRequestResponseFinancialResponse1())
                .hdr(new FinancialRequestResponseHeader42());
    }

    public static ReversalFinancialAdviceResponseReversalResponseV02 getResponseFinancialRevAdvResponseItem() {
        return new ReversalFinancialAdviceResponseReversalResponseV02()
                .body(new ReversalFinancialAdviceResponseReversalResponse2())
                .hdr(new ReversalFinancialAdviceResponseHeader42());
    }

    public static InquiryResponseInquiryResponseV01 getResponseInquiryResponseItem() {
        return new InquiryResponseInquiryResponseV01()
                .body(new InquiryResponseInquiryResponse1())
                .hdr(new InquiryResponseHeader39());
    }

    public static ReversalResponseReversalResponseV02 getResponseReversalResponseItem() {
        return new ReversalResponseReversalResponseV02()
                .body(new ReversalResponseReversalResponse2())
                .hdr(new ReversalResponseHeader42());
    }

    public static AuthorisationResponseStatus getAuthResponseItem(AuthorisationResponseAuthorisationResponseV02 resp, String corrId, String customerContextKey, int httpStatus) {
        return new AuthorisationResponseStatus(
                new AuthorisationResponseV02Status()
                        .correlationId(corrId)
                        .customerContextKey(customerContextKey)
                        .payload(resp)
                        .httpStatus(httpStatus));
    }

    public static AuthorisationResponseStatus getAuthResponseItem(String corrId, String customerContextKey, int httpStatus) {
        return new AuthorisationResponseStatus(
                new AuthorisationResponseV02Status()
                        .correlationId(corrId)
                        .customerContextKey(customerContextKey)
                        .payload(getResponseAuthResponseItem())
                        .httpStatus(httpStatus));
    }

    public static AuthorisationAdviceResponseStatus getAuthAdviceResponseItem(AuthorisationResponseAuthorisationResponseV02 resp, String corrId, String customerContextKey, int httpStatus) {
        return new AuthorisationAdviceResponseStatus(
                new AuthorisationAdviceResponseV02Status()
                        .correlationId(corrId)
                        .customerContextKey(customerContextKey)
                        .payload(resp)
                        .httpStatus(httpStatus));
    }

    public static AuthorisationAdviceResponseStatus getAuthAdviceResponseItem(String corrId, String customerContextKey, int httpStatus) {
        return new AuthorisationAdviceResponseStatus(
                new AuthorisationAdviceResponseV02Status()
                        .correlationId(corrId)
                        .customerContextKey(customerContextKey)
                        .payload(getResponseAuthResponseItem())
                        .httpStatus(httpStatus));
    }

    public static FinancialAdviceResponseStatus getFinAdvResponseItem(String corrId, String customerContextKey, int httpStatus) {
        return new FinancialAdviceResponseStatus(
                new FinancialAdviceResponseV02Status()
                        .correlationId(corrId)
                        .customerContextKey(customerContextKey)
                        .payload(getResponseFinancialAdvResponseItem())
                        .httpStatus(httpStatus));
    }

    public static FinancialRequestResponseStatus getFinReqResponseItem(String corrId, String customerContextKey, int httpStatus) {
        return new FinancialRequestResponseStatus(
                new FinancialRequestResponseV02Status()
                        .correlationId(corrId)
                        .customerContextKey(customerContextKey)
                        .payload(getResponseFinancialReqResponseItem())
                        .httpStatus(httpStatus));
    }

    public static FinancialReversalAdviceResponseStatus getFinRevAdvResponseItem(String corrId, String customerContextKey, int httpStatus) {
        return new FinancialReversalAdviceResponseStatus(
                new FinancialReversalAdviceResponseV02Status()
                        .correlationId(corrId)
                        .customerContextKey(customerContextKey)
                        .payload(getResponseFinancialRevAdvResponseItem())
                        .httpStatus(httpStatus));
    }

    public static InquiryResponseStatus getInqResponseItem(String corrId, String customerContextKey, int httpStatus) {
        return new InquiryResponseStatus(
                new InquiryResponseV01Status()
                        .correlationId(corrId)
                        .customerContextKey(customerContextKey)
                        .payload(getResponseInquiryResponseItem())
                        .httpStatus(httpStatus));
    }

    public static ReversalResponseStatus getRevResponseItem(String corrId, String customerContextKey, int httpStatus) {
        return new ReversalResponseStatus(
                new ReversalResponseV02Status()
                        .correlationId(corrId)
                        .customerContextKey(customerContextKey)
                        .payload(getResponseReversalResponseItem())
                        .httpStatus(httpStatus));
    }

    private static AuthorisationResponseV02Status getAuthResponseV02(AuthorisationResponseAuthorisationResponseV02 resp, String corrId, String customerContextKey, int httpStatus) {
        return new AuthorisationResponseV02Status()
                .correlationId(corrId)
                .customerContextKey(customerContextKey)
                .payload(resp)
                .httpStatus(httpStatus);
    }

    private static AuthorisationAdviceResponseV02Status getAuthAdviceResponseV02(AuthorisationResponseAuthorisationResponseV02 resp, String corrId, String customerContextKey, int httpStatus) {
        return new AuthorisationAdviceResponseV02Status()
                .correlationId(corrId)
                .customerContextKey(customerContextKey)
                .payload(resp)
                .httpStatus(httpStatus);
    }

    private static FinancialAdviceResponseV02Status getFinancialAdvResponseV02(FinancialResponseFinancialResponseV02 resp, String corrId, String customerContextKey, int httpStatus) {
        return new FinancialAdviceResponseV02Status()
                .correlationId(corrId)
                .customerContextKey(customerContextKey)
                .payload(resp)
                .httpStatus(httpStatus);
    }

    private static FinancialRequestResponseV02Status getFinancialReqResponseV02(FinancialRequestResponseFinancialResponseV02 resp, String corrId, String customerContextKey, int httpStatus) {
        return new FinancialRequestResponseV02Status()
                .correlationId(corrId)
                .customerContextKey(customerContextKey)
                .payload(resp)
                .httpStatus(httpStatus);
    }

    private static FinancialReversalAdviceResponseV02Status getFinancialRevAdvResponseV02(ReversalFinancialAdviceResponseReversalResponseV02 resp, String corrId, String customerContextKey, int httpStatus) {
        return new FinancialReversalAdviceResponseV02Status()
                .correlationId(corrId)
                .customerContextKey(customerContextKey)
                .payload(resp)
                .httpStatus(httpStatus);
    }

    private static InquiryResponseV01Status getInquiryResponseV02(InquiryResponseInquiryResponseV01 resp, String corrId, String customerContextKey, int httpStatus) {
        return new InquiryResponseV01Status()
                .correlationId(corrId)
                .customerContextKey(customerContextKey)
                .payload(resp)
                .httpStatus(httpStatus);
    }

    private static ReversalResponseV02Status getReversalResponseV02(ReversalResponseReversalResponseV02 resp, String corrId, String customerContextKey, int httpStatus) {
        return new ReversalResponseV02Status()
                .correlationId(corrId)
                .customerContextKey(customerContextKey)
                .payload(resp)
                .httpStatus(httpStatus);
    }

    public static List<Error> getListOfErrors() {
        return getListOfErrors(null);
    }

    public static List<Error> getListOfErrors(ErrorPayload payload) {
        Error error = new Error()
                .description("Test Exception")
                .details("Test Failure")
                .reasonCode("FAILED")
                .source("Test")
                .recoverable(false);

        if(payload!=null){
            error.payload(payload);
        }

        return Collections.singletonList(error);
    }

    public static ErrorWrapper getErrorWrapper(ErrorPayload payload) {
        Errors errors = new Errors().error(getListOfErrors(payload));
        return new ErrorWrapper().errors(errors);
    }

    public static ErrorPayload getAuthErrorPayload(){
        return new ErrorPayload().authorisationResponse(getResponseAuthResponseItem());
    }

    public static ErrorPayload getFinAdvErrorPayload(){
        return new ErrorPayload().financialResponse(getResponseFinancialAdvResponseItem());
    }

    public static ErrorPayload getFinReqErrorPayload(){
        return new ErrorPayload().financialRequestResponse(getResponseFinancialReqResponseItem());
    }

    public static ErrorPayload getFinRevAdvErrorPayload(){
        return new ErrorPayload().reversalFinancialAdviceResponse(getResponseFinancialRevAdvResponseItem());
    }

    public static ErrorPayload getInqErrorPayload(){
        return new ErrorPayload().inquiryResponse(getResponseInquiryResponseItem());
    }

    public static ErrorPayload getRevErrorPayload(){
        return new ErrorPayload().reversalResponse(getResponseReversalResponseItem());
    }

    public static AuthorisationResponseStatus getAuthResponseItemWithErrors(String corrId, String customerContextKey, int httpStatus, AuthorisationResponseAuthorisationResponseV02 payload) {
        return new AuthorisationResponseStatus(
                new AuthorisationResponseV02Status()
                        .payload(payload)
                        .correlationId(corrId)
                        .customerContextKey(customerContextKey)
                        .errors(getListOfErrors())
                        .httpStatus(httpStatus));
    }

    public static AuthorisationResponseStatus getAuthResponseItemWithErrors(String corrId, String customerContextKey, int httpStatus) {
        return new AuthorisationResponseStatus(
                new AuthorisationResponseV02Status()
                        .payload(getResponseAuthResponseItem())
                        .correlationId(corrId)
                        .customerContextKey(customerContextKey)
                        .errors(getListOfErrors(new ErrorPayload().authorisationResponse(getResponseAuthResponseItem())))
                        .httpStatus(httpStatus));
    }

    public static AuthorisationAdviceResponseStatus getAuthAdviceResponseItemWithErrors(String corrId, String customerContextKey, int httpStatus) {
        return new AuthorisationAdviceResponseStatus(
                new AuthorisationAdviceResponseV02Status()
                        .payload(getResponseAuthAdviceResponseItem())
                        .correlationId(corrId)
                        .customerContextKey(customerContextKey)
                        .errors(getListOfErrors(new ErrorPayload().authorisationResponse(getResponseAuthAdviceResponseItem())))
                        .httpStatus(httpStatus));
    }

    public static FinancialAdviceResponseStatus getFinAdvResponseItemWithErrors(String corrId, String customerContextKey, int httpStatus) {
        return new FinancialAdviceResponseStatus(
                new FinancialAdviceResponseV02Status()
                        .payload(getResponseFinancialAdvResponseItem())
                        .correlationId(corrId)
                        .customerContextKey(customerContextKey)
                        .errors(getListOfErrors(new ErrorPayload().financialResponse(getResponseFinancialAdvResponseItem())))
                        .httpStatus(httpStatus));
    }

    public static FinancialRequestResponseStatus getFinReqResponseItemWithErrors(String corrId, String customerContextKey, int httpStatus) {
        return new FinancialRequestResponseStatus(
                new FinancialRequestResponseV02Status()
                        .payload(getResponseFinancialReqResponseItem())
                        .correlationId(corrId)
                        .customerContextKey(customerContextKey)
                        .errors(getListOfErrors(new ErrorPayload().financialRequestResponse(getResponseFinancialReqResponseItem())))
                        .httpStatus(httpStatus));
    }

    public static FinancialReversalAdviceResponseStatus getFinRevAdvResponseItemWithErrors(String corrId, String customerContextKey, int httpStatus) {
        return new FinancialReversalAdviceResponseStatus(
                new FinancialReversalAdviceResponseV02Status()
                        .payload(getResponseFinancialRevAdvResponseItem())
                        .correlationId(corrId)
                        .customerContextKey(customerContextKey)
                        .errors(getListOfErrors(new ErrorPayload().reversalFinancialAdviceResponse(getResponseFinancialRevAdvResponseItem())))
                        .httpStatus(httpStatus));
    }

    public static InquiryResponseStatus getInqResponseItemWithErrors(String corrId, String customerContextKey, int httpStatus) {
        return new InquiryResponseStatus(
                new InquiryResponseV01Status()
                        .payload(getResponseInquiryResponseItem())
                        .correlationId(corrId)
                        .customerContextKey(customerContextKey)
                        .errors(getListOfErrors(new ErrorPayload().inquiryResponse(getResponseInquiryResponseItem())))
                        .httpStatus(httpStatus));
    }

    public static ReversalResponseStatus getRevResponseItemWithErrors(String corrId, String customerContextKey, int httpStatus) {
        return new ReversalResponseStatus(
                new ReversalResponseV02Status()
                        .payload(getResponseReversalResponseItem())
                        .correlationId(corrId)
                        .customerContextKey(customerContextKey)
                        .errors(getListOfErrors(new ErrorPayload().reversalResponse(getResponseReversalResponseItem())))
                        .httpStatus(httpStatus));
    }

    private static List<ResponseStatus<AuthorisationResponseAuthorisationResponseV02>> getResponseItemList(AuthorisationResponseStatus respItem) {
        return Collections.singletonList(respItem);
    }

    public static BatchResponse.BatchResponseBuilder<AuthorisationResponseAuthorisationResponseV02> getBatchBuilder(List<ResponseStatus<AuthorisationResponseAuthorisationResponseV02>> respList) {
        BatchResponse.BatchResponseBuilder<AuthorisationResponseAuthorisationResponseV02> batchBuilder = BatchResponse.builder();
        return batchBuilder
                .items(respList)
                .retryAfter(Duration.ZERO);
    }

    public static BatchResponse.BatchResponseBuilder<AuthorisationResponseAuthorisationResponseV02> generateBatchBuilder(String corrId, String customerContextKey, int httpStatus) {
        return getBatchBuilder(
                getResponseItemList(
                        getAuthResponseItem(
                                getResponseAuthResponseItem(), corrId, customerContextKey, httpStatus
                        )));
    }

    public static BatchResponse.BatchResponseBuilder<AuthorisationResponseAuthorisationResponseV02> generateBatchBuilderWithPayloadAndErrors(String corrId, String customerContextKey, int httpStatus) {
        return getBatchBuilder(
                getResponseItemList(
                        getAuthResponseItemWithErrors(
                                corrId, customerContextKey, httpStatus, getResponseAuthResponseItem()
                        )));
    }

    public static BatchResponse.BatchResponseBuilder<AuthorisationResponseAuthorisationResponseV02> generateBatchBuilderWithErrors(String corrId, String customerContextKey, int httpStatus) {
        return getBatchBuilder(
                getResponseItemList(
                        getAuthResponseItemWithErrors(
                                corrId, customerContextKey, httpStatus, null
                        )));
    }

    public static ApiResponse<AuthorisationResponseAuthorisationResponseV02> getAuthorisationResponse(int httpStatus) {
        return new ApiResponse<>(httpStatus, responseMap, getResponseAuthResponseItem());
    }

    public static ApiResponse<FinancialResponseFinancialResponseV02> getFinancialAdvResponse(int httpStatus) {
        return new ApiResponse<>(httpStatus, responseMap, getResponseFinancialAdvResponseItem());
    }

    public static ApiResponse<FinancialRequestResponseFinancialResponseV02> getFinancialReqResponse(int httpStatus) {
        return new ApiResponse<>(httpStatus, responseMap, getResponseFinancialReqResponseItem());
    }

    public static ApiResponse<ReversalFinancialAdviceResponseReversalResponseV02> getFinancialRevAdvResponse(int httpStatus) {
        return new ApiResponse<>(httpStatus, responseMap, getResponseFinancialRevAdvResponseItem());
    }

    public static ApiResponse<InquiryResponseInquiryResponseV01> getInquiryResponse(int httpStatus) {
        return new ApiResponse<>(httpStatus, responseMap, getResponseInquiryResponseItem());
    }

    public static ApiResponse<ReversalResponseReversalResponseV02> getReversalResponse(int httpStatus) {
        return new ApiResponse<>(httpStatus, responseMap, getResponseReversalResponseItem());
    }

    public static ApiResponse<AuthorisationResponseAuthorisationResponseV02> getAuthorisationAdviceResponse(int httpStatus) {
        return new ApiResponse<>(httpStatus, responseMap, getResponseAuthResponseItem());
    }

    private static List<AuthorisationResponseV02Status> buildAuthList(int httpStatus) {
        return Arrays.asList(
                getAuthResponseV02(getResponseAuthResponseItem(), TEST_CORRELATION_ID_1, TEST_CUSTOMER_CONTEXT_KEY_1, httpStatus),
                getAuthResponseV02(getResponseAuthResponseItem(), TEST_CORRELATION_ID_2, TEST_CUSTOMER_CONTEXT_KEY_2, httpStatus));
    }

    public static AuthorisationResponseV02List getAuthorisationResponseList(int httpStatus) {
        return new AuthorisationResponseV02List().items(buildAuthList(httpStatus));
    }

    private static List<FinancialAdviceResponseV02Status> buildFinancialAdvList(int httpStatus) {
        return Arrays.asList(
                getFinancialAdvResponseV02(getResponseFinancialAdvResponseItem(), TEST_CORRELATION_ID_1, TEST_CUSTOMER_CONTEXT_KEY_1, httpStatus),
                getFinancialAdvResponseV02(getResponseFinancialAdvResponseItem(), TEST_CORRELATION_ID_2, TEST_CUSTOMER_CONTEXT_KEY_2, httpStatus));
    }

    public static FinancialAdviceResponseV02List getFinancialAdvResponseList(int httpStatus) {
        return new FinancialAdviceResponseV02List().items(buildFinancialAdvList(httpStatus));
    }

    private static List<FinancialRequestResponseV02Status> buildFinancialReqList(int httpStatus) {
        return Arrays.asList(
                getFinancialReqResponseV02(getResponseFinancialReqResponseItem(), TEST_CORRELATION_ID_1, TEST_CUSTOMER_CONTEXT_KEY_1, httpStatus),
                getFinancialReqResponseV02(getResponseFinancialReqResponseItem(), TEST_CORRELATION_ID_2, TEST_CUSTOMER_CONTEXT_KEY_2, httpStatus));
    }

    public static FinancialRequestResponseV02List getFinancialReqResponseList(int httpStatus) {
        return new FinancialRequestResponseV02List().items(buildFinancialReqList(httpStatus));
    }

    private static List<FinancialReversalAdviceResponseV02Status> buildFinancialRevAdvList(int httpStatus) {
        return Arrays.asList(
                getFinancialRevAdvResponseV02(getResponseFinancialRevAdvResponseItem(), TEST_CORRELATION_ID_1, TEST_CUSTOMER_CONTEXT_KEY_1, httpStatus),
                getFinancialRevAdvResponseV02(getResponseFinancialRevAdvResponseItem(), TEST_CORRELATION_ID_2, TEST_CUSTOMER_CONTEXT_KEY_2, httpStatus));
    }

    public static FinancialReversalAdviceResponseV02List getFinancialRevAdvResponseList(int httpStatus) {
        return new FinancialReversalAdviceResponseV02List().items(buildFinancialRevAdvList(httpStatus));
    }

    private static List<InquiryResponseV01Status> buildInquiryList(int httpStatus) {
        return Arrays.asList(
                getInquiryResponseV02(getResponseInquiryResponseItem(), TEST_CORRELATION_ID_1, TEST_CUSTOMER_CONTEXT_KEY_1, httpStatus),
                getInquiryResponseV02(getResponseInquiryResponseItem(), TEST_CORRELATION_ID_2, TEST_CUSTOMER_CONTEXT_KEY_2, httpStatus));
    }

    public static InquiryResponseV01List getInquiryResponseList(int httpStatus) {
        return new InquiryResponseV01List().items(buildInquiryList(httpStatus));
    }

    private static List<ReversalResponseV02Status> buildReversalList(int httpStatus) {
        return Arrays.asList(
                getReversalResponseV02(getResponseReversalResponseItem(), TEST_CORRELATION_ID_1, TEST_CUSTOMER_CONTEXT_KEY_1, httpStatus),
                getReversalResponseV02(getResponseReversalResponseItem(), TEST_CORRELATION_ID_2, TEST_CUSTOMER_CONTEXT_KEY_2, httpStatus));
    }

    public static ReversalResponseV02List getReversalResponseList(int httpStatus) {
        return new ReversalResponseV02List().items(buildReversalList(httpStatus));
    }

    private static List<AuthorisationAdviceResponseV02Status> buildAuthAdviceList(int httpStatus) {
        return Arrays.asList(
                getAuthAdviceResponseV02(getResponseAuthAdviceResponseItem(), TEST_CORRELATION_ID_1, TEST_CUSTOMER_CONTEXT_KEY_1, httpStatus),
                getAuthAdviceResponseV02(getResponseAuthAdviceResponseItem(), TEST_CORRELATION_ID_2, TEST_CUSTOMER_CONTEXT_KEY_2, httpStatus));
    }

    public static AuthorisationAdviceResponseV02List getAuthorisationAdviceResponseList(int httpStatus) {
        return new AuthorisationAdviceResponseV02List().items(buildAuthAdviceList(httpStatus));
    }

    public static AuthorisationInitiationAuthorisationInitiationV02 getTestAuthorisationInitiationV02() {
        return requestExampleGenerator.buildAuthorisationRequest();
    }

    public static ReversalInitiationReversalInitiationV02 getTestReversalInitiationV02() {
        return requestExampleGenerator.buildReversalRequest();
    }

    public static InquiryInitiationInquiryInitiationV01 getTestInquiryInitiationV01() {
        return requestExampleGenerator.buildInquiryRequest();
    }

    public static FinancialInitiationFinancialInitiationV02 getTestFinancialInitiationV02() {
        return requestExampleGenerator.buildFinancialAdviceRequest();
    }

    public static FinancialRequestInitiationFinancialInitiationV02 getTestFinancialRequestInitiationV02() {
        return requestExampleGenerator.buildFinancialRequest();
    }

    public static ReversalFinancialAdviceInitiationReversalInitiationV02 getTestFinancialReversalAdviceInitiationV02() {
        return requestExampleGenerator.buildFinancialReversalAdvice();
    }

    public static AuthorisationInitiationAuthorisationInitiationV02 getTestAuthorisationAdviceInitiationV02() {
        return requestExampleGenerator.buildAuthorisationAdviceRequest();
    }


}
