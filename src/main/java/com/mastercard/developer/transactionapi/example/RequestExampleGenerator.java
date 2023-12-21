package com.mastercard.developer.transactionapi.example;

import com.google.gson.Gson;
import org.openapitools.client.model.AuthorisationinitiationAuthorisationInitiationV02;
import org.openapitools.client.model.FinancialinitiationFinancialInitiationV02;
import org.openapitools.client.model.InquiryinitiationInquiryInitiationV01;
import org.openapitools.client.model.ReversalinitiationReversalInitiationV02;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    private final Clock clock = Clock.systemUTC();
    private final Random random = new Random();     // NOSONAR used for test data only

    /**
     * Creates a sample AuthorisationInitiationV02 request.
     *
     * @return An instance of AuthorisationInitiationV02
     * @implNote The required field values used in this tutorial are dummy values and for demo purposes only, please change to valid values before running this application.
     */
    public AuthorisationinitiationAuthorisationInitiationV02 buildAuthorisationRequest() {
        AuthorisationinitiationAuthorisationInitiationV02 request = loadInitiationRequest(AUTHORISATION_JSON, AuthorisationinitiationAuthorisationInitiationV02.class);
        LocalDateTime date = LocalDateTime.now();
        String now = date.atOffset(ZoneOffset.UTC).format(dtf);
        // the below fields must be uinque in each request
        request.getBody().getTx().getTxId().setLclDtTm(now);
        request.getBody().getTx().getTxId().setTrnsmssnDtTm(now);
        request.getBody().getTx().getTxId().setSysTracAudtNb(nextSysTracAudtNb());
        return request;
    }

    /**
     * Creates a sample InitiationReversalInitiationV02 request.
     *
     * @return An instance of InitiationReversalInitiationV02
     * @implNote The required field values used in this tutorial are dummy values and for demo purposes only, please change to valid values before running this application.
     */
    public ReversalinitiationReversalInitiationV02 buildReversalRequest() {
        ReversalinitiationReversalInitiationV02 request = loadInitiationRequest(REVERSAL_JSON, ReversalinitiationReversalInitiationV02.class);
        LocalDateTime date = LocalDateTime.now();
        String now = date.atOffset(ZoneOffset.UTC).format(dtf);
        // the below fields must be uinque in each request
        request.getBody().getTx().getTxId().setLclDtTm(now);
        request.getBody().getTx().getTxId().setTrnsmssnDtTm(now);
        request.getBody().getTx().getTxId().setSysTracAudtNb(nextSysTracAudtNb());
        return request;
    }

    /**
     * Creates a sample InitiationInquiryInitiationV01 request.
     *
     * @return An instance of InitiationInquiryInitiationV01
     * @implNote The required field values used in this tutorial are dummy values and for demo purposes only, please change to valid values before running this application.
     */
    public InquiryinitiationInquiryInitiationV01 buildInquiryRequest() {
        InquiryinitiationInquiryInitiationV01 request = loadInitiationRequest(INQUIRY_JSON, InquiryinitiationInquiryInitiationV01.class);
        LocalDateTime date = LocalDateTime.now();
        String now = date.atOffset(ZoneOffset.UTC).format(dtf);
        // the below fields must be uinque in each request
        request.getBody().getTx().getTxId().setLclDtTm(now);
        request.getBody().getTx().getTxId().setTrnsmssnDtTm(now);
        request.getBody().getTx().getTxId().setSysTracAudtNb(nextSysTracAudtNb());
        return request;
    }

    /**
     * Creates a sample InitiationFinancialInitiationV02 request.
     *
     * @return An instance of InitiationFinancialInitiationV02
     * @implNote The required field values used in this tutorial are dummy values and for demo purposes only, please change to valid values before running this application.
     */
    public FinancialinitiationFinancialInitiationV02 buildFinancialAdviceRequest() {
        FinancialinitiationFinancialInitiationV02 request = loadInitiationRequest(FINANCIAL_ADVICE_JSON, FinancialinitiationFinancialInitiationV02.class);
        LocalDateTime date = LocalDateTime.now();
        String now = date.atOffset(ZoneOffset.UTC).format(dtf);
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
