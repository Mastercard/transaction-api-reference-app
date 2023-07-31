package com.mastercard.developer.transactionapi.test;

import com.mastercard.developer.transactionapi.client.model.AuthorisationResponseItem;
import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import com.mastercard.developer.transactionapi.client.model.ResponseItem;
import com.mastercard.developer.transactionapi.example.RequestExampleGenerator;
import org.openapitools.client.model.AuthorisationResponseV02Item;
import org.openapitools.client.model.AuthorisationResponseV02List;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.FinancialAdviceResponseV02Item;
import org.openapitools.client.model.FinancialAdviceResponseV02List;
import org.openapitools.client.model.InitiationAuthorisationInitiationV02;
import org.openapitools.client.model.InitiationFinancialInitiationV02;
import org.openapitools.client.model.InitiationInquiryInitiationV01;
import org.openapitools.client.model.InitiationReversalInitiationV02;
import org.openapitools.client.model.InquiryResponseV01Item;
import org.openapitools.client.model.InquiryResponseV01List;
import org.openapitools.client.model.InquiryresponseHeader39;
import org.openapitools.client.model.MsgauthorisationresponseAdditionalData1;
import org.openapitools.client.model.MsgauthorisationresponseCardData4;
import org.openapitools.client.model.MsgauthorisationresponseContext2;
import org.openapitools.client.model.MsgauthorisationresponseEnvironment2;
import org.openapitools.client.model.MsgauthorisationresponseGenericIdentification172;
import org.openapitools.client.model.MsgauthorisationresponseHeader42;
import org.openapitools.client.model.MsgauthorisationresponsePartyIdentification197;
import org.openapitools.client.model.MsgauthorisationresponseProcessingResult2;
import org.openapitools.client.model.MsgauthorisationresponseRiskAssessment1;
import org.openapitools.client.model.MsgauthorisationresponseRiskContext1;
import org.openapitools.client.model.MsgauthorisationresponseToken1;
import org.openapitools.client.model.MsgauthorisationresponseTransaction78;
import org.openapitools.client.model.MsgauthorisationresponseTransactionAmounts1;
import org.openapitools.client.model.MsgauthorisationresponseTransactionAttribute1Code;
import org.openapitools.client.model.MsgauthorisationresponseTransactionContext3;
import org.openapitools.client.model.MsgauthorisationresponseTransactionIdentification8;
import org.openapitools.client.model.MsgauthorisationresponseVerification2;
import org.openapitools.client.model.MsgfinancialresponseHeader42;
import org.openapitools.client.model.MsgreversalresponseHeader42;
import org.openapitools.client.model.ResponseAuthorisationResponse1;
import org.openapitools.client.model.ResponseAuthorisationResponseV02;
import org.openapitools.client.model.ResponseFinancialResponse1;
import org.openapitools.client.model.ResponseFinancialResponseV02;
import org.openapitools.client.model.ResponseInquiryResponse1;
import org.openapitools.client.model.ResponseInquiryResponseV01;
import org.openapitools.client.model.ResponseReversalResponse2;
import org.openapitools.client.model.ResponseReversalResponseV02;
import org.openapitools.client.model.ReversalResponseV02Item;
import org.openapitools.client.model.ReversalResponseV02List;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CORRELATION_ID_1;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CORRELATION_ID_2;

public class  TestRequestResponseGenerator {

    private static final RequestExampleGenerator requestExampleGenerator = new RequestExampleGenerator();

    public static ResponseAuthorisationResponseV02 getResponseAuthResponseItem() {
        return new ResponseAuthorisationResponseV02()
                .body(new ResponseAuthorisationResponse1())
                .hdr(new MsgauthorisationresponseHeader42());
    }

    public static ResponseFinancialResponseV02 getResponseFinancialAdvResponseItem() {
        return new ResponseFinancialResponseV02()
                .body(new ResponseFinancialResponse1())
                .hdr(new MsgfinancialresponseHeader42());
    }

    public static ResponseInquiryResponseV01 getResponseInquiryResponseItem() {
        return new ResponseInquiryResponseV01()
                .body(new ResponseInquiryResponse1())
                .hdr(new InquiryresponseHeader39());
    }

    public static ResponseReversalResponseV02 getResponseReversalResponseItem() {
        return new ResponseReversalResponseV02()
                .body(new ResponseReversalResponse2())
                .hdr(new MsgreversalresponseHeader42());
    }

    private static AuthorisationResponseItem getAuthResponseItem(ResponseAuthorisationResponseV02 resp, String corrId, int httpStatus) {
        return new AuthorisationResponseItem(
                new AuthorisationResponseV02Item()
                        .correlationId(corrId)
                        .payload(resp)
                        .httpStatus(httpStatus));
    }

    private static AuthorisationResponseV02Item getAuthResponseV02(ResponseAuthorisationResponseV02 resp, String corrId, int httpStatus) {
        return new AuthorisationResponseV02Item()
                .correlationId(corrId)
                .payload(resp)
                .httpStatus(httpStatus);
    }

    private static FinancialAdviceResponseV02Item getFinancialAdvResponseV02(ResponseFinancialResponseV02 resp, String corrId, int httpStatus) {
        return new FinancialAdviceResponseV02Item()
                .correlationId(corrId)
                .payload(resp)
                .httpStatus(httpStatus);
    }

    private static InquiryResponseV01Item getInquiryResponseV02(ResponseInquiryResponseV01 resp, String corrId, int httpStatus) {
        return new InquiryResponseV01Item()
                .correlationId(corrId)
                .payload(resp)
                .httpStatus(httpStatus);
    }

    private static ReversalResponseV02Item getReversalResponseV02(ResponseReversalResponseV02 resp, String corrId, int httpStatus) {
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

    private static List<ResponseItem<ResponseAuthorisationResponseV02>> getResponseItemList(AuthorisationResponseItem respItem) {
        return Collections.singletonList(respItem);
    }

    public static BatchResponse.BatchResponseBuilder<ResponseAuthorisationResponseV02> getBatchBuilder(List<ResponseItem<ResponseAuthorisationResponseV02>> respList) {
        BatchResponse.BatchResponseBuilder<ResponseAuthorisationResponseV02> batchBuilder = BatchResponse.builder();
        return batchBuilder
                .items(respList)
                .retryAfter(Duration.ZERO);
    }

    public static BatchResponse.BatchResponseBuilder<ResponseAuthorisationResponseV02> generateBatchBuilder(String corrId, int httpStatus) {
        return getBatchBuilder(
                getResponseItemList(
                        getAuthResponseItem(
                                getResponseAuthResponseItem(), corrId, httpStatus
                        )));
    }

    public static BatchResponse.BatchResponseBuilder<ResponseAuthorisationResponseV02> generateBatchBuilderWithErrors(String corrId, int httpStatus) {
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

    public static InitiationAuthorisationInitiationV02 getTestAuthorisationInitiationV02() {
        return requestExampleGenerator.buildAuthorisationRequest();
    }

    public static InitiationReversalInitiationV02 getTestReversalInitiationV02() {
        return requestExampleGenerator.buildReversalRequest();
    }

    public static InitiationInquiryInitiationV01 getTestInquiryInitiationV01() {
        return requestExampleGenerator.buildInquiryRequest();
    }

    public static InitiationFinancialInitiationV02 getTestFinancialInitiationV02() {
        return requestExampleGenerator.buildFinancialAdviceRequest();
    }

}
