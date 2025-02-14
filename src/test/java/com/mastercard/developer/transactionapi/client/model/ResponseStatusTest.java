package com.mastercard.developer.transactionapi.client.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.JSON;
import org.openapitools.client.model.AuthorisationAdviceResponseV02Status;
import org.openapitools.client.model.AuthorisationResponseV02Status;
import org.openapitools.client.model.FinancialAdviceResponseV02Status;
import org.openapitools.client.model.FinancialRequestResponseV02Status;
import org.openapitools.client.model.FinancialReversalAdviceResponseV02Status;
import org.openapitools.client.model.InquiryResponseV01Status;
import org.openapitools.client.model.ReversalResponseV02Status;

import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getResponseAuthAdviceResponseItem;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getResponseAuthResponseItem;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getResponseFinancialAdvResponseItem;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getResponseFinancialReqResponseItem;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getResponseFinancialRevAdvResponseItem;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getResponseInquiryResponseItem;
import static com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator.getResponseReversalResponseItem;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ResponseStatusTest {

    @InjectMocks
    private AuthorisationResponseStatus authorisationResponseStatus;
    private AuthorisationAdviceResponseStatus authorisationAdviceResponseStatus;
    private FinancialAdviceResponseStatus financialAdviceResponseStatus;
    private FinancialRequestResponseStatus financialRequestResponseStatus;
    private FinancialReversalAdviceResponseStatus financialReversalAdviceResponseStatus;
    private InquiryResponseStatus inquiryResponseStatus;
    private ReversalResponseStatus reversalResponseStatus;

    @BeforeEach
    void setup() {
        new JSON();     // required to initialise Json.getGson() method
    }

    @Test
    void givenAuthorisationStatusPayload_whenGetPayloadAsJson_thenReturnJSONPayload() {
        // setup
        authorisationResponseStatus = new AuthorisationResponseStatus(new AuthorisationResponseV02Status()
                .payload(getResponseAuthResponseItem()));

        // call
        String actual = authorisationResponseStatus.getPayloadAsJson();

        // verify
        assertThat(actual).contains("{\"hdr\":{\"msgFctn\":\"UNSPECIFIED\"},\"body\":{}}");
    }

    @Test
    void givenNullAuthorisationStatusPayload_whenGetPayloadAsJson_thenReturnNull() {
        // setup
        authorisationResponseStatus = new AuthorisationResponseStatus(new AuthorisationResponseV02Status()
                .payload(null));

        // call
        String actual = authorisationResponseStatus.getPayloadAsJson();

        // verify
        assertThat(actual).isNull();
    }

    @Test
    void givenAuthorisationAdviceStatusPayload_whenGetPayloadAsJson_thenReturnJSONPayload() {
        // setup
        authorisationAdviceResponseStatus = new AuthorisationAdviceResponseStatus(new AuthorisationAdviceResponseV02Status()
                .payload(getResponseAuthAdviceResponseItem()));

        // call
        String actual = authorisationAdviceResponseStatus.getPayloadAsJson();

        // verify
        assertThat(actual).contains("{\"hdr\":{\"msgFctn\":\"UNSPECIFIED\"},\"body\":{}}");
    }

    @Test
    void givenNullAuthorisationAdviceStatusPayload_whenGetPayloadAsJson_thenReturnNull() {
        // setup
        authorisationAdviceResponseStatus = new AuthorisationAdviceResponseStatus(new AuthorisationAdviceResponseV02Status()
                .payload(null));

        // call
        String actual = authorisationAdviceResponseStatus.getPayloadAsJson();

        // verify
        assertThat(actual).isNull();
    }

    @Test
    void givenFinancialAdviceStatusPayload_whenGetPayloadAsJson_thenReturnJSONPayload() {
        // setup
        financialAdviceResponseStatus = new FinancialAdviceResponseStatus(new FinancialAdviceResponseV02Status()
                .payload(getResponseFinancialAdvResponseItem()));

        // call
        String actual = financialAdviceResponseStatus.getPayloadAsJson();

        // verify
        assertThat(actual).contains("{\"hdr\":{\"msgFctn\":\"UNSPECIFIED\"},\"body\":{}}");
    }

    @Test
    void givenNullFinancialAdviceStatusPayload_whenGetPayloadAsJson_thenReturnNull() {
        // setup
        financialAdviceResponseStatus = new FinancialAdviceResponseStatus(new FinancialAdviceResponseV02Status()
                .payload(null));

        // call
        String actual = financialAdviceResponseStatus.getPayloadAsJson();

        // verify
        assertThat(actual).isNull();
    }

    @Test
    void givenFinancialRequestStatusPayload_whenGetPayloadAsJson_thenReturnJSONPayload() {
        // setup
        financialRequestResponseStatus = new FinancialRequestResponseStatus(new FinancialRequestResponseV02Status()
                .payload(getResponseFinancialReqResponseItem()));

        // call
        String actual = financialRequestResponseStatus.getPayloadAsJson();

        // verify
        assertThat(actual).contains("{\"hdr\":{\"msgFctn\":\"UNSPECIFIED\"},\"body\":{}}");
    }

    @Test
    void givenNullFinancialRequestStatusPayload_whenGetPayloadAsJson_thenReturnNull() {
        // setup
        financialRequestResponseStatus = new FinancialRequestResponseStatus(new FinancialRequestResponseV02Status()
                .payload(null));

        // call
        String actual = financialRequestResponseStatus.getPayloadAsJson();

        // verify
        assertThat(actual).isNull();
    }

    @Test
    void givenFinancialReversalAdviceStatusPayload_whenGetPayloadAsJson_thenReturnJSONPayload() {
        // setup
        financialReversalAdviceResponseStatus = new FinancialReversalAdviceResponseStatus(new FinancialReversalAdviceResponseV02Status()
                .payload(getResponseFinancialRevAdvResponseItem()));

        // call
        String actual = financialReversalAdviceResponseStatus.getPayloadAsJson();

        // verify
        assertThat(actual).contains("{\"hdr\":{\"msgFctn\":\"UNSPECIFIED\"},\"body\":{}}");
    }

    @Test
    void givenNullFinancialReversalRequestStatusPayload_whenGetPayloadAsJson_thenReturnNull() {
        // setup
        financialReversalAdviceResponseStatus = new FinancialReversalAdviceResponseStatus(new FinancialReversalAdviceResponseV02Status()
                .payload(null));

        // call
        String actual = financialReversalAdviceResponseStatus.getPayloadAsJson();

        // verify
        assertThat(actual).isNull();
    }

    @Test
    void givenInquiryStatusPayload_whenGetPayloadAsJson_thenReturnJSONPayload() {
        // setup
        inquiryResponseStatus = new InquiryResponseStatus(new InquiryResponseV01Status()
                .payload(getResponseInquiryResponseItem()));

        // call
        String actual = inquiryResponseStatus.getPayloadAsJson();

        // verify
        assertThat(actual).contains("{\"hdr\":{\"msgFctn\":\"UNSPECIFIED\"},\"body\":{}}");
    }

    @Test
    void givenNullInquiryStatusPayload_whenGetPayloadAsJson_thenReturnNull() {
        // setup
        inquiryResponseStatus = new InquiryResponseStatus(new InquiryResponseV01Status()
                .payload(null));

        // call
        String actual = inquiryResponseStatus.getPayloadAsJson();

        // verify
        assertThat(actual).isNull();
    }

    @Test
    void givenReversalStatusPayload_whenGetPayloadAsJson_thenReturnJSONPayload() {
        // setup
        reversalResponseStatus = new ReversalResponseStatus(new ReversalResponseV02Status()
                .payload(getResponseReversalResponseItem()));

        // call
        String actual = reversalResponseStatus.getPayloadAsJson();

        // verify
        assertThat(actual).contains("{\"hdr\":{\"msgFctn\":\"UNSPECIFIED\"},\"body\":{}}");
    }

    @Test
    void givenNullReversalStatusPayload_whenGetPayloadAsJson_thenReturnNull() {
        // setup
        reversalResponseStatus = new ReversalResponseStatus(new ReversalResponseV02Status()
                .payload(null));

        // call
        String actual = reversalResponseStatus.getPayloadAsJson();

        // verify
        assertThat(actual).isNull();
    }

}