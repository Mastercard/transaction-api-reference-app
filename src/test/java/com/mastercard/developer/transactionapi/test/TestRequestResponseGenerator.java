package com.mastercard.developer.transactionapi.test;

import com.mastercard.developer.transactionapi.client.model.AuthorisationResponseStatus;
import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import com.mastercard.developer.transactionapi.client.model.ResponseStatus;
import com.mastercard.developer.transactionapi.example.RequestExampleGenerator;
import org.openapitools.client.model.AuthorisationResponseV02Status;
import org.openapitools.client.model.AuthorisationResponseV02List;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.FinancialAdviceResponseV02Status;
import org.openapitools.client.model.FinancialAdviceResponseV02List;
import org.openapitools.client.model.AuthorisationInitiationAuthorisationInitiationV02;
import org.openapitools.client.model.FinancialInitiationFinancialInitiationV02;
import org.openapitools.client.model.InquiryInitiationInquiryInitiationV01;
import org.openapitools.client.model.ReversalInitiationReversalInitiationV02;
import org.openapitools.client.model.InquiryResponseV01Status;
import org.openapitools.client.model.InquiryResponseV01List;
import org.openapitools.client.model.InquiryResponseHeader39;
import org.openapitools.client.model.AuthorisationResponseHeader42;
import org.openapitools.client.model.FinancialResponseHeader42;
import org.openapitools.client.model.ReversalResponseHeader42;
import org.openapitools.client.model.AuthorisationResponseAuthorisationResponse1;
import org.openapitools.client.model.AuthorisationResponseAuthorisationResponseV02;
import org.openapitools.client.model.FinancialResponseFinancialResponse1;
import org.openapitools.client.model.FinancialResponseFinancialResponseV02;
import org.openapitools.client.model.InquiryResponseInquiryResponse1;
import org.openapitools.client.model.InquiryResponseInquiryResponseV01;
import org.openapitools.client.model.ReversalResponseReversalResponse2;
import org.openapitools.client.model.ReversalResponseReversalResponseV02;
import org.openapitools.client.model.ReversalResponseV02Status;
import org.openapitools.client.model.ReversalResponseV02List;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CORRELATION_ID_1;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CORRELATION_ID_2;

public class  TestRequestResponseGenerator {

    private static final RequestExampleGenerator requestExampleGenerator = new RequestExampleGenerator();

    public static AuthorisationResponseAuthorisationResponseV02 getResponseAuthResponseItem() {
        return new AuthorisationResponseAuthorisationResponseV02()
                .body(new AuthorisationResponseAuthorisationResponse1())
                .hdr(new AuthorisationResponseHeader42());
    }

    public static FinancialResponseFinancialResponseV02 getResponseFinancialAdvResponseItem() {
        return new FinancialResponseFinancialResponseV02()
                .body(new FinancialResponseFinancialResponse1())
                .hdr(new FinancialResponseHeader42());
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

    private static AuthorisationResponseStatus getAuthResponseItem(AuthorisationResponseAuthorisationResponseV02 resp, String corrId, int httpStatus) {
        return new AuthorisationResponseStatus(
                new AuthorisationResponseV02Status()
                        .correlationId(corrId)
                        .payload(resp)
                        .httpStatus(httpStatus));
    }

    private static AuthorisationResponseV02Status getAuthResponseV02(AuthorisationResponseAuthorisationResponseV02 resp, String corrId, int httpStatus) {
        return new AuthorisationResponseV02Status()
                .correlationId(corrId)
                .payload(resp)
                .httpStatus(httpStatus);
    }

    private static FinancialAdviceResponseV02Status getFinancialAdvResponseV02(FinancialResponseFinancialResponseV02 resp, String corrId, int httpStatus) {
        return new FinancialAdviceResponseV02Status()
                .correlationId(corrId)
                .payload(resp)
                .httpStatus(httpStatus);
    }

    private static InquiryResponseV01Status getInquiryResponseV02(InquiryResponseInquiryResponseV01 resp, String corrId, int httpStatus) {
        return new InquiryResponseV01Status()
                .correlationId(corrId)
                .payload(resp)
                .httpStatus(httpStatus);
    }

    private static ReversalResponseV02Status getReversalResponseV02(ReversalResponseReversalResponseV02 resp, String corrId, int httpStatus) {
        return new ReversalResponseV02Status()
                .correlationId(corrId)
                .payload(resp)
                .httpStatus(httpStatus);
    }

    private static List<Error> getListOfErrors() {
        return Collections.singletonList(new Error()
                .description("Test Exception")
                .details("Test Failure")
                .reasonCode("FAILED")
                .source("Test")
                .recoverable(false));
    }

    private static AuthorisationResponseStatus getAuthResponseItemWithErrors(String corrId, int httpStatus) {
        return new AuthorisationResponseStatus(
                new AuthorisationResponseV02Status()
                        .correlationId(corrId)
                        .errors(getListOfErrors())
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

    public static BatchResponse.BatchResponseBuilder<AuthorisationResponseAuthorisationResponseV02> generateBatchBuilder(String corrId, int httpStatus) {
        return getBatchBuilder(
                getResponseItemList(
                        getAuthResponseItem(
                                getResponseAuthResponseItem(), corrId, httpStatus
                        )));
    }

    public static BatchResponse.BatchResponseBuilder<AuthorisationResponseAuthorisationResponseV02> generateBatchBuilderWithErrors(String corrId, int httpStatus) {
        return getBatchBuilder(
                getResponseItemList(
                        getAuthResponseItemWithErrors(
                                corrId, httpStatus
                        )));
    }

    private static List<AuthorisationResponseV02Status> buildAuthList(int httpStatus) {
        return Arrays.asList(
                getAuthResponseV02(getResponseAuthResponseItem(), TEST_CORRELATION_ID_1, httpStatus),
                getAuthResponseV02(getResponseAuthResponseItem(), TEST_CORRELATION_ID_2, httpStatus));
    }

    public static AuthorisationResponseV02List getAuthorisationResponseList(int httpStatus) {
        return new AuthorisationResponseV02List().items(buildAuthList(httpStatus));
    }

    private static List<FinancialAdviceResponseV02Status> buildFinancialAdvList(int httpStatus) {
        return Arrays.asList(
                getFinancialAdvResponseV02(getResponseFinancialAdvResponseItem(), TEST_CORRELATION_ID_1, httpStatus),
                getFinancialAdvResponseV02(getResponseFinancialAdvResponseItem(), TEST_CORRELATION_ID_2, httpStatus));
    }

    public static FinancialAdviceResponseV02List getFinancialAdvResponseList(int httpStatus) {
        return new FinancialAdviceResponseV02List().items(buildFinancialAdvList(httpStatus));
    }

    private static List<InquiryResponseV01Status> buildInquiryList(int httpStatus) {
        return Arrays.asList(
                getInquiryResponseV02(getResponseInquiryResponseItem(), TEST_CORRELATION_ID_1, httpStatus),
                getInquiryResponseV02(getResponseInquiryResponseItem(), TEST_CORRELATION_ID_2, httpStatus));
    }

    public static InquiryResponseV01List getInquiryResponseList(int httpStatus) {
        return new InquiryResponseV01List().items(buildInquiryList(httpStatus));
    }

    private static List<ReversalResponseV02Status> buildReversalList(int httpStatus) {
        return Arrays.asList(
                getReversalResponseV02(getResponseReversalResponseItem(), TEST_CORRELATION_ID_1, httpStatus),
                getReversalResponseV02(getResponseReversalResponseItem(), TEST_CORRELATION_ID_2, httpStatus));
    }

    public static ReversalResponseV02List getReversalResponseList(int httpStatus) {
        return new ReversalResponseV02List().items(buildReversalList(httpStatus));
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

}
