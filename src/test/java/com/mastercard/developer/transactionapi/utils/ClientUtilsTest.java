package com.mastercard.developer.transactionapi.utils;

import com.mastercard.developer.transactionapi.exception.RuntimeInterruptedException;
import com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.client.JSON;
import org.openapitools.client.model.AuthorisationInitiationAuthorisationInitiationV02;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.ErrorPayload;
import org.openapitools.client.model.ReversalResponseReversalResponseV02;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mastercard.developer.transactionapi.test.TestConstants.CUSTOMER_CONTEXT_KEY_HEADER;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_CUSTOMER_CONTEXT_KEY;
import static com.mastercard.developer.transactionapi.utils.ClientUtils.getValueFromHeaders;
import static com.mastercard.developer.transactionapi.utils.PayloadUtils.getErrorPayload;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClientUtilsTest {

    private static final Duration TEST_DELAY = Duration.ofMillis(250);

    @BeforeEach
    void setup() {
        new JSON();     // required to initialise Json.getGson() method
    }

    @Test
    void givenHappyPath_whenSleep_thenSleep() {
        // setup
        Instant start = Instant.now();

        // call
        ClientUtils.sleep(TEST_DELAY);

        // verify
        assertThat(Duration.between(start, Instant.now())).isGreaterThanOrEqualTo(TEST_DELAY);
    }

    @Test
    void givenInterrupted_whenSleep_thenThrow() {
        // setup
        Thread.currentThread().interrupt();

        // call
        assertThrows(RuntimeInterruptedException.class, () ->
                ClientUtils.sleep(TEST_DELAY));

        // verify
        assertThat(Thread.interrupted()).isTrue();
    }

    @Test
    void givenLoggingEnabled_whenConvertPayloadForLogging_verifyString(){
        // setup
        AuthorisationInitiationAuthorisationInitiationV02 item = TestRequestResponseGenerator.getTestAuthorisationInitiationV02();

        // call
        String result = ClientUtils.convertPayloadForLogging(item.toJson(), true);

        // verify
        assertThat(result).isEqualTo(item.toJson());
    }

    @Test
    void givenLoggingDisabled_whenConvertPayloadForLogging_verifyString(){
        // setup
        AuthorisationInitiationAuthorisationInitiationV02 item = TestRequestResponseGenerator.getTestAuthorisationInitiationV02();

        // call
        String result = ClientUtils.convertPayloadForLogging(item.toJson(), false);

        // verify
        assertThat(result).isEqualTo("omitted payload");
    }

    @Test
    void givenCustomerContextKeyOnHeader_whenGetValueFromHeaders_thenReturnCustomerContextKeyValue() {
        // setup
        Map<String, List<String>> map = Map.of(CUSTOMER_CONTEXT_KEY_HEADER,List.of(TEST_CUSTOMER_CONTEXT_KEY));

        // call
        String actual = getValueFromHeaders(CUSTOMER_CONTEXT_KEY_HEADER, map);

        // verify
        assertThat(actual).isEqualTo(TEST_CUSTOMER_CONTEXT_KEY);
    }

    @Test
    void givenNullMap_whenGetValueFromHeaders_thenReturnNull() {

        // call
        String actual = getValueFromHeaders(CUSTOMER_CONTEXT_KEY_HEADER, null);

        // verify
        assertThat(actual).isNull();
    }

    @Test
    void givenEmptyMap_whenGetValueFromHeaders_thenReturnNull() {
        // call
        String actual = getValueFromHeaders(CUSTOMER_CONTEXT_KEY_HEADER, Map.of());

        // verify
        assertThat(actual).isNull();
    }

    @Test
    void givenEmptyMapNullList_whenGetValueFromHeaders_thenReturnNull() {
        // setup
        Map<String, List<String>> map = new HashMap<>();
        map.put(CUSTOMER_CONTEXT_KEY_HEADER,null);

        // call
        String actual = getValueFromHeaders(CUSTOMER_CONTEXT_KEY_HEADER, map);

        // verify
        assertThat(actual).isNull();
    }

    @Test
    void givenEmptyMapEmptyList_whenGetValueFromHeaders_thenReturnNull() {
        // setup
        Map<String, List<String>> map = Map.of(CUSTOMER_CONTEXT_KEY_HEADER,List.of());

        // call
        String actual = getValueFromHeaders(CUSTOMER_CONTEXT_KEY_HEADER, map);

        // verify
        assertThat(actual).isNull();
    }
}