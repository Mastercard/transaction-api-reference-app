package com.mastercard.developer.transactionapi.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openapitools.client.model.AuthorisationResponseAuthorisationResponseV02;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.ErrorPayload;
import org.openapitools.client.model.ErrorWrapper;
import org.openapitools.client.model.FinancialRequestResponseFinancialResponseV02;
import org.openapitools.client.model.FinancialResponseFinancialResponseV02;
import org.openapitools.client.model.InquiryResponseInquiryResponseV01;
import org.openapitools.client.model.ReversalFinancialAdviceResponseReversalResponseV02;
import org.openapitools.client.model.ReversalResponseReversalResponseV02;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.Collections;
import java.util.List;

import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getAuthErrorPayload;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getErrorWrapper;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getFinAdvErrorPayload;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getFinReqErrorPayload;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getFinRevAdvErrorPayload;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getInqErrorPayload;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getListOfErrors;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getRevErrorPayload;
import static com.mastercard.developer.transactionapi.utils.PayloadUtils.getErrorPayload;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(OutputCaptureExtension.class)
class PayloadUtilsTest {

    private ErrorPayload authorisationErrorPayload = getAuthErrorPayload();
    private ErrorPayload financialAdviceErrorPayload = getFinAdvErrorPayload();
    private ErrorPayload financialRequestErrorPayload = getFinReqErrorPayload();
    private ErrorPayload financialReversalAdviceErrorPayload = getFinRevAdvErrorPayload();
    private ErrorPayload inquiryErrorPayload = getInqErrorPayload();
    private ErrorPayload reversalErrorPayload = getRevErrorPayload();

    @Test
    void givenErrorResponseBody_whenJsonToErrorList_thenReturnErrorList() {
        // setup
        ErrorWrapper errorWrapper = getErrorWrapper(getAuthErrorPayload());
        List<Error> expected = errorWrapper.getErrors().getError();

        String errorJson = "{\n" +
                "    \"Errors\": {\n" +
                "        \"Error\": [\n" +
                "            {\n" +
                "                \"Source\": \"Test\",\n" +
                "                \"ReasonCode\": \"FAILED\",\n" +
                "                \"Description\": \"Test Exception\",\n" +
                "                \"Recoverable\": false,\n" +
                "                \"Details\": \"Test Failure\",\n" +
                "                \"Payload\": {\n" +
                "                    \"authorisationResponse\": {\n" +
                "                        \"hdr\": {\n" +
                "                            \"msgFctn\": \"UNSPECIFIED\"\n" +
                "                        },\n" +
                "                        \"body\": {}\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";

        // call
        List<Error> actual = PayloadUtils.jsonToErrorList(errorJson);

        // verify
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenMalformedErrorResponseBody_whenJsonToErrorList_thenReturnEmptyErrorList(CapturedOutput output) {
        // setup
        List<Error> expected = Collections.emptyList();

        String errorJson = "{";

        // call
        List<Error> actual = PayloadUtils.jsonToErrorList(errorJson);

        // verify
        assertThat(actual).isEqualTo(expected);
        assertThat(output.getAll()).contains("Unexpected error body: {");
    }

    @Test
    void givenAuthorisationErrorPayload_whenGetErrorPayload_thenReturnAuthorisationResponse() {
        // setup
        List<Error> errorList = getListOfErrors(authorisationErrorPayload);
        AuthorisationResponseAuthorisationResponseV02 expected = authorisationErrorPayload.getAuthorisationResponse();

        // call
        AuthorisationResponseAuthorisationResponseV02 actual = getErrorPayload(errorList, ErrorPayload::getAuthorisationResponse);

        // verify
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenFinancialAdviceErrorPayload_whenGetErrorPayload_thenReturnFinancialAdviceResponse() {
        // setup
        List<Error> errorList = getListOfErrors(financialAdviceErrorPayload);
        FinancialResponseFinancialResponseV02 expected = financialAdviceErrorPayload.getFinancialResponse();

        // call
        FinancialResponseFinancialResponseV02 actual = getErrorPayload(errorList, ErrorPayload::getFinancialResponse);

        // verify
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenFinancialRequestErrorPayload_whenGetErrorPayload_thenReturnFinancialRequestResponse() {
        // setup
        List<Error> errorList = getListOfErrors(financialRequestErrorPayload);
        FinancialRequestResponseFinancialResponseV02 expected = financialRequestErrorPayload.getFinancialRequestResponse();

        // call
        FinancialRequestResponseFinancialResponseV02 actual = getErrorPayload(errorList, ErrorPayload::getFinancialRequestResponse);

        // verify
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenFinancialReversalAdviceErrorPayload_whenGetErrorPayload_thenReturnFinancialReversalAdviceResponse() {
        // setup
        List<Error> errorList = getListOfErrors(financialReversalAdviceErrorPayload);
        ReversalFinancialAdviceResponseReversalResponseV02 expected = financialReversalAdviceErrorPayload.getReversalFinancialAdviceResponse();

        // call
        ReversalFinancialAdviceResponseReversalResponseV02 actual = getErrorPayload(errorList, ErrorPayload::getReversalFinancialAdviceResponse);

        // verify
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenInquiryErrorPayload_whenGetErrorPayload_thenReturnInquiryResponse() {
        // setup
        List<Error> errorList = getListOfErrors(inquiryErrorPayload);
        InquiryResponseInquiryResponseV01 expected = inquiryErrorPayload.getInquiryResponse();

        // call
        InquiryResponseInquiryResponseV01 actual = getErrorPayload(errorList, ErrorPayload::getInquiryResponse);

        // verify
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenReversalErrorPayload_whenGetErrorPayload_thenReturnReversalResponse() {
        // setup
        List<Error> errorList = getListOfErrors(reversalErrorPayload);
        ReversalResponseReversalResponseV02 expected = reversalErrorPayload.getReversalResponse();

        // call
        ReversalResponseReversalResponseV02 actual =getErrorPayload(errorList, ErrorPayload::getReversalResponse);

        // verify
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void givenNullErrorList_whenGetErrorPayload_thenReturnNull() {
        // call
        ReversalResponseReversalResponseV02 actual = getErrorPayload(null, ErrorPayload::getReversalResponse);

        // verify
        assertThat(actual).isNull();
    }

    @Test
    void givenEmptyErrorList_whenGetErrorPayload_thenReturnNull() {
        // setup
        List<Error> errorList = Collections.emptyList();

        // call
        ReversalResponseReversalResponseV02 actual = getErrorPayload(errorList, ErrorPayload::getReversalResponse);

        // verify
        assertThat(actual).isNull();
    }

    @Test
    void givenEmptyErrorPayload_whenGetErrorPayload_thenReturnNull() {
        // setup
        List<Error> errorList = getListOfErrors();

        // call
        ReversalResponseReversalResponseV02 actual = getErrorPayload(errorList, ErrorPayload::getReversalResponse);

        // verify
        assertThat(actual).isNull();
    }

    @Test
    void givenEmptyReversalErrorPayload_whenGetErrorPayload_thenReturnNull() {
        // setup
        List<Error> errorList = getListOfErrors();
        errorList.get(0).payload(new ErrorPayload());

        // call
        ReversalResponseReversalResponseV02 actual = getErrorPayload(errorList, ErrorPayload::getReversalResponse);

        // verify
        assertThat(actual).isNull();
    }
}