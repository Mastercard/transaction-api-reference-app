package com.mastercard.developer.transactionapi.test;

import com.mastercard.developer.transactionapi.client.model.AuthorisationResponseItem;
import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import com.mastercard.developer.transactionapi.client.model.ResponseItem;
import com.mastercard.developer.transactionapi.example.RequestExampleGenerator;
import org.openapitools.client.model.AuthorisationResponseV02Item;
import org.openapitools.client.model.AuthorisationResponseV02List;
import org.openapitools.client.model.AuthorisationinitiationAuthorisationInitiationV02;
import org.openapitools.client.model.AuthorisationresponseAuthorisationResponse1;
import org.openapitools.client.model.AuthorisationresponseAuthorisationResponseV02;
import org.openapitools.client.model.AuthorisationresponseHeader42;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.FinancialAdviceResponseV02Item;
import org.openapitools.client.model.FinancialAdviceResponseV02List;
import org.openapitools.client.model.FinancialinitiationFinancialInitiationV02;
import org.openapitools.client.model.FinancialresponseFinancialResponse1;
import org.openapitools.client.model.FinancialresponseFinancialResponseV02;
import org.openapitools.client.model.FinancialresponseHeader42;
import org.openapitools.client.model.InquiryResponseV01Item;
import org.openapitools.client.model.InquiryResponseV01List;
import org.openapitools.client.model.InquiryinitiationInquiryInitiationV01;
import org.openapitools.client.model.InquiryresponseHeader39;
import org.openapitools.client.model.InquiryresponseInquiryResponse1;
import org.openapitools.client.model.InquiryresponseInquiryResponseV01;
import org.openapitools.client.model.ReversalResponseV02Item;
import org.openapitools.client.model.ReversalResponseV02List;
import org.openapitools.client.model.ReversalinitiationReversalInitiationV02;
import org.openapitools.client.model.ReversalresponseHeader42;
import org.openapitools.client.model.ReversalresponseReversalResponse2;
import org.openapitools.client.model.ReversalresponseReversalResponseV02;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CORRELATION_ID_1;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CORRELATION_ID_2;

public class  TestRequestResponseGenerator {

    private static final RequestExampleGenerator requestExampleGenerator = new RequestExampleGenerator();

    public static AuthorisationresponseAuthorisationResponseV02 getResponseAuthResponseItem() {
        return new AuthorisationresponseAuthorisationResponseV02()
                .body(new AuthorisationresponseAuthorisationResponse1())
                .hdr(new AuthorisationresponseHeader42());
    }

    public static FinancialresponseFinancialResponseV02 getResponseFinancialAdvResponseItem() {
        return new FinancialresponseFinancialResponseV02()
                .body(new FinancialresponseFinancialResponse1())
                .hdr(new FinancialresponseHeader42());
    }

    public static InquiryresponseInquiryResponseV01 getResponseInquiryResponseItem() {
        return new InquiryresponseInquiryResponseV01()
                .body(new InquiryresponseInquiryResponse1())
                .hdr(new InquiryresponseHeader39());
    }

    public static ReversalresponseReversalResponseV02 getResponseReversalResponseItem() {
        return new ReversalresponseReversalResponseV02()
                .body(new ReversalresponseReversalResponse2())
                .hdr(new ReversalresponseHeader42());
    }

    private static AuthorisationResponseItem getAuthResponseItem(AuthorisationresponseAuthorisationResponseV02 resp, String corrId, int httpStatus) {
        return new AuthorisationResponseItem(
                new AuthorisationResponseV02Item()
                        .correlationId(corrId)
                        .payload(resp)
                        .httpStatus(httpStatus));
    }

    private static AuthorisationResponseV02Item getAuthResponseV02(AuthorisationresponseAuthorisationResponseV02 resp, String corrId, int httpStatus) {
        return new AuthorisationResponseV02Item()
                .correlationId(corrId)
                .payload(resp)
                .httpStatus(httpStatus);
    }

    private static FinancialAdviceResponseV02Item getFinancialAdvResponseV02(FinancialresponseFinancialResponseV02 resp, String corrId, int httpStatus) {
        return new FinancialAdviceResponseV02Item()
                .correlationId(corrId)
                .payload(resp)
                .httpStatus(httpStatus);
    }

    private static InquiryResponseV01Item getInquiryResponseV02(InquiryresponseInquiryResponseV01 resp, String corrId, int httpStatus) {
        return new InquiryResponseV01Item()
                .correlationId(corrId)
                .payload(resp)
                .httpStatus(httpStatus);
    }

    private static ReversalResponseV02Item getReversalResponseV02(ReversalresponseReversalResponseV02 resp, String corrId, int httpStatus) {
        return new ReversalResponseV02Item()
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

    private static AuthorisationResponseItem getAuthResponseItemWithErrors(String corrId, int httpStatus) {
        return new AuthorisationResponseItem(
                new AuthorisationResponseV02Item()
                        .correlationId(corrId)
                        .errors(getListOfErrors())
                        .httpStatus(httpStatus));
    }

    private static List<ResponseItem<AuthorisationresponseAuthorisationResponseV02>> getResponseItemList(AuthorisationResponseItem respItem) {
        return Collections.singletonList(respItem);
    }

    public static BatchResponse.BatchResponseBuilder<AuthorisationresponseAuthorisationResponseV02> getBatchBuilder(List<ResponseItem<AuthorisationresponseAuthorisationResponseV02>> respList) {
        BatchResponse.BatchResponseBuilder<AuthorisationresponseAuthorisationResponseV02> batchBuilder = BatchResponse.builder();
        return batchBuilder
                .items(respList)
                .retryAfter(Duration.ZERO);
    }

    public static BatchResponse.BatchResponseBuilder<AuthorisationresponseAuthorisationResponseV02> generateBatchBuilder(String corrId, int httpStatus) {
        return getBatchBuilder(
                getResponseItemList(
                        getAuthResponseItem(
                                getResponseAuthResponseItem(), corrId, httpStatus
                        )));
    }

    public static BatchResponse.BatchResponseBuilder<AuthorisationresponseAuthorisationResponseV02> generateBatchBuilderWithErrors(String corrId, int httpStatus) {
        return getBatchBuilder(
                getResponseItemList(
                        getAuthResponseItemWithErrors(
                                corrId, httpStatus
                        )));
    }

    private static List<AuthorisationResponseV02Item> buildAuthList(int httpStatus) {
        return Arrays.asList(
                getAuthResponseV02(getResponseAuthResponseItem(), TEST_CORRELATION_ID_1, httpStatus),
                getAuthResponseV02(getResponseAuthResponseItem(), TEST_CORRELATION_ID_2, httpStatus));
    }

    public static AuthorisationResponseV02List getAuthorisationResponseList(int httpStatus) {
        return new AuthorisationResponseV02List().items(buildAuthList(httpStatus));
    }

    private static List<FinancialAdviceResponseV02Item> buildFinancialAdvList(int httpStatus) {
        return Arrays.asList(
                getFinancialAdvResponseV02(getResponseFinancialAdvResponseItem(), TEST_CORRELATION_ID_1, httpStatus),
                getFinancialAdvResponseV02(getResponseFinancialAdvResponseItem(), TEST_CORRELATION_ID_2, httpStatus));
    }

    public static FinancialAdviceResponseV02List getFinancialAdvResponseList(int httpStatus) {
        return new FinancialAdviceResponseV02List().items(buildFinancialAdvList(httpStatus));
    }

    private static List<InquiryResponseV01Item> buildInquiryList(int httpStatus) {
        return Arrays.asList(
                getInquiryResponseV02(getResponseInquiryResponseItem(), TEST_CORRELATION_ID_1, httpStatus),
                getInquiryResponseV02(getResponseInquiryResponseItem(), TEST_CORRELATION_ID_2, httpStatus));
    }

    public static InquiryResponseV01List getInquiryResponseList(int httpStatus) {
        return new InquiryResponseV01List().items(buildInquiryList(httpStatus));
    }

    private static List<ReversalResponseV02Item> buildReversalList(int httpStatus) {
        return Arrays.asList(
                getReversalResponseV02(getResponseReversalResponseItem(), TEST_CORRELATION_ID_1, httpStatus),
                getReversalResponseV02(getResponseReversalResponseItem(), TEST_CORRELATION_ID_2, httpStatus));
    }

    public static ReversalResponseV02List getReversalResponseList(int httpStatus) {
        return new ReversalResponseV02List().items(buildReversalList(httpStatus));
    }

    public static AuthorisationinitiationAuthorisationInitiationV02 getTestAuthorisationInitiationV02() {
        return requestExampleGenerator.buildAuthorisationRequest();
    }

    public static ReversalinitiationReversalInitiationV02 getTestReversalInitiationV02() {
        return requestExampleGenerator.buildReversalRequest();
    }

    public static InquiryinitiationInquiryInitiationV01 getTestInquiryInitiationV01() {
        return requestExampleGenerator.buildInquiryRequest();
    }

    public static FinancialinitiationFinancialInitiationV02 getTestFinancialInitiationV02() {
        return requestExampleGenerator.buildFinancialAdviceRequest();
    }

}
