package com.mastercard.developer.transactionapi.example;

import com.google.gson.Gson;
import org.openapitools.client.model.AuthorisationInitiationAuthorisationInitiationV02;
import org.openapitools.client.model.FinancialInitiationFinancialInitiationV02;
import org.openapitools.client.model.InquiryInitiationInquiryInitiationV01;
import org.openapitools.client.model.ReversalInitiationReversalInitiationV02;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.temporal.ChronoUnit;
import java.util.Random;

/**
 * Generates sample requests.
 */
@Service
public class RequestExampleGenerator {

    public static final String PATH = "/data/";     // NOSONAR resource path, does not need to be configured
    public static final String AUTHORISATION_JSON = "authorisation.json";
    public static final String REVERSAL_JSON = "reversal.json";
    public static final String INQUIRY_JSON = "inquiry.json";
    public static final String FINANCIAL_ADVICE_JSON = "financial_advice.json";

    private final Clock clock = Clock.systemUTC();
    private final Random random = new Random();     // NOSONAR used for test data only

    /**
     * Creates a sample AuthorisationInitiationV02 request.
     *
     * @return An instance of AuthorisationInitiationV02
     * @implNote The required field values used in this tutorial are dummy values and for demo purposes only, please change to valid values before running this application.
     */
    public AuthorisationInitiationAuthorisationInitiationV02 buildAuthorisationRequest() {
        AuthorisationInitiationAuthorisationInitiationV02 request = loadInitiationRequest(AUTHORISATION_JSON, AuthorisationInitiationAuthorisationInitiationV02.class);
        String now = clock.instant().truncatedTo(ChronoUnit.MILLIS).toString();
        // the below fields must be uinque in each request
        request.getBody().getTx().getTxId().setLclDtTm(now);
        request.getBody().getTx().getTxId().setTrnsmssnDtTm(now);
        request.getBody().getTx().getTxId().setSysTracAudtNb(nextSysTracAudtNb());
        return request;
    }

    /**
     * Creates a sample ReversalInitiationReversalInitiationV02 request.
     *
     * @return An instance of ReversalInitiationReversalInitiationV02
     * @implNote The required field values used in this tutorial are dummy values and for demo purposes only, please change to valid values before running this application.
     */
    public ReversalInitiationReversalInitiationV02 buildReversalRequest() {
        ReversalInitiationReversalInitiationV02 request = loadInitiationRequest(REVERSAL_JSON, ReversalInitiationReversalInitiationV02.class);
        String now = clock.instant().truncatedTo(ChronoUnit.MILLIS).toString();
        // the below fields must be uinque in each request
        request.getBody().getTx().getTxId().setLclDtTm(now);
        request.getBody().getTx().getTxId().setTrnsmssnDtTm(now);
        request.getBody().getTx().getTxId().setSysTracAudtNb(nextSysTracAudtNb());
        return request;
    }

    /**
     * Creates a sample InquiryInitiationInquiryInitiationV01 request.
     *
     * @return An instance of InquiryInitiationInquiryInitiationV01
     * @implNote The required field values used in this tutorial are dummy values and for demo purposes only, please change to valid values before running this application.
     */
    public InquiryInitiationInquiryInitiationV01 buildInquiryRequest() {
        InquiryInitiationInquiryInitiationV01 request = loadInitiationRequest(INQUIRY_JSON, InquiryInitiationInquiryInitiationV01.class);
        String now = clock.instant().truncatedTo(ChronoUnit.MILLIS).toString();
        // the below fields must be uinque in each request
        request.getBody().getTx().getTxId().setLclDtTm(now);
        request.getBody().getTx().getTxId().setTrnsmssnDtTm(now);
        request.getBody().getTx().getTxId().setSysTracAudtNb(nextSysTracAudtNb());
        return request;
    }

    /**
     * Creates a sample FinancialInitiationFinancialInitiationV02 request.
     *
     * @return An instance of FinancialInitiationFinancialInitiationV02
     * @implNote The required field values used in this tutorial are dummy values and for demo purposes only, please change to valid values before running this application.
     */
    public FinancialInitiationFinancialInitiationV02 buildFinancialAdviceRequest() {
        FinancialInitiationFinancialInitiationV02 request = loadInitiationRequest(FINANCIAL_ADVICE_JSON, FinancialInitiationFinancialInitiationV02.class);
        String now = clock.instant().truncatedTo(ChronoUnit.MILLIS).toString();
        // the below fields must be uinque in each request
        request.getBody().getTx().getTxId().setLclDtTm(now);
        request.getBody().getTx().getTxId().setSysTracAudtNb(nextSysTracAudtNb());
        return request;

    }

    private <T> T loadInitiationRequest(String fileName, Class<T> type) {
        try (InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream(PATH + fileName), StandardCharsets.UTF_8)) {
            return new Gson().fromJson(reader, type);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to load sample request from " + PATH + fileName, e);
        }
    }

    private String nextSysTracAudtNb() {
        return Integer.toString(100000 + random.nextInt(899999));
    }
}
