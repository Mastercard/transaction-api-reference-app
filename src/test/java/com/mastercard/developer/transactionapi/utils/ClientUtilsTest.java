package com.mastercard.developer.transactionapi.utils;

import com.mastercard.developer.transactionapi.exception.RuntimeInterruptedException;
import com.mastercard.developer.transactionapi.test.TestRequestResponseGenerator;
import org.junit.jupiter.api.Test;
import org.openapitools.client.model.InitiationAuthorisationInitiationV02;

import java.time.Duration;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClientUtilsTest {

    private static final Duration TEST_DELAY = Duration.ofMillis(250);

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
        InitiationAuthorisationInitiationV02 item = TestRequestResponseGenerator.getTestAuthorisationInitiationV02();

        // call
        String result = ClientUtils.convertPayloadForLogging(item.toJson(), true);

        // verify
        assertThat(result).isEqualTo(item.toJson());
    }

    @Test
    void givenLoggingDisabled_whenConvertPayloadForLogging_verifyString(){
        // setup
        InitiationAuthorisationInitiationV02 item = TestRequestResponseGenerator.getTestAuthorisationInitiationV02();

        // call
        String result = ClientUtils.convertPayloadForLogging(item.toJson(), false);

        // verify
        assertThat(result).isEqualTo("omitted payload");
    }
}