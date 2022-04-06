package com.mastercard.developer.example;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mastercard.developer.exception.ServiceException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openapitools.client.model.AuthorisationInitiation1;
import org.openapitools.client.model.AuthorisationInitiationV02;
import org.openapitools.client.model.GenericIdentification172;
import org.openapitools.client.model.Header42;

import java.io.IOException;
import java.io.InputStream;
import java.time.OffsetDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionApiExample {

    /**
     * Creates an instance of AuthorisationInitiationV02 for a combined request with multiple use cases and sets all required and (available) optional information of request
     *
     * @return An instance of AuthorisationInitiationV02
     * @implNote The required field values used in this tutorial are dummy values and for demo purposes only, please change to valid values before running this application.
     */
    public static AuthorisationInitiationV02 buildAuthorisationRequest() {
        InputStream requestStream = TransactionApiExample.class.getClassLoader()
                .getResourceAsStream("data/authorisation.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        try {
            AuthorisationInitiationV02 authorisationInitiationV02 = objectMapper.readValue(requestStream, AuthorisationInitiationV02.class);
            return authorisationInitiationV02;
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }

}
