package com.mastercard.developer.example;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mastercard.developer.exception.ServiceException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.client.model.InitiationAuthorisationInitiationV02;
import org.openapitools.client.model.InitiationInquiryInitiation1;
import org.openapitools.client.model.InitiationInquiryInitiationV01;
import org.openapitools.client.model.InitiationReversalInitiationV02;

import java.io.IOException;
import java.io.InputStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionApiExample {

    public static final String PATH = "data/";
    public static final String REVERSAL_JSON = "reversal.json";
    public static final String AUTHORISATION_JSON = "authorisation.json";
    public static final String INQUIRY_JSON = "inquiry.json";

    /**
     * Creates an instance of AuthorisationInitiationV02 for a combined request with multiple use cases and sets all required and (available) optional information of request
     *
     * @return An instance of AuthorisationInitiationV02
     * @implNote The required field values used in this tutorial are dummy values and for demo purposes only, please change to valid values before running this application.
     */
    public static InitiationAuthorisationInitiationV02 buildAuthorisationRequest() {
        return buildInitiationRequest(AUTHORISATION_JSON, InitiationAuthorisationInitiationV02.class);
    }

    /**
     * Creates an instance of InitiationReversalInitiationV02 for a combined request with multiple use cases and sets all required and (available) optional information of request
     *
     * @return An instance of InitiationReversalInitiationV02
     * @implNote The required field values used in this tutorial are dummy values and for demo purposes only, please change to valid values before running this application.
     */
    public static InitiationReversalInitiationV02 buildReversalRequest() {
        return buildInitiationRequest(REVERSAL_JSON, InitiationReversalInitiationV02.class);
    }

    /**
     * Creates an instance of InitiationInquiryInitiationV01 for a combined request with multiple use cases and sets all required and (available) optional information of request
     *
     * @return An instance of InitiationInquiryInitiationV01
     * @implNote The required field values used in this tutorial are dummy values and for demo purposes only, please change to valid values before running this application.
     */
    public static InitiationInquiryInitiationV01 buildInquiryRequest() {
        return buildInitiationRequest(INQUIRY_JSON, InitiationInquiryInitiationV01.class);
    }

    private static <T> T buildInitiationRequest(String fileName, Class<T> type) {
        InputStream requestStream = TransactionApiExample.class.getClassLoader().getResourceAsStream(PATH + fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        try {
            return objectMapper.readValue(requestStream, type);
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }

}
