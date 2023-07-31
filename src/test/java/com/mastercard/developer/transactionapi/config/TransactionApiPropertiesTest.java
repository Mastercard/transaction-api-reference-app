package com.mastercard.developer.transactionapi.config;

import org.junit.jupiter.api.Test;

import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_BASE_PATH;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_KEY_FILE;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_KEY_STORE_PASSWORD;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_KEY_STORE_TYPE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TransactionApiPropertiesTest {

    @Test
    void propertiesTest() {
        TransactionApiProperties properties = new TransactionApiProperties();
        TransactionApiProperties.SslProperties sslProperties = new TransactionApiProperties.SslProperties();
        properties.setBasePath(TEST_BASE_PATH);
        properties.setPayloadLoggingEnabled(true);
        sslProperties.setEnabled(true);
        sslProperties.setKeyStoreType(TEST_KEY_STORE_TYPE);
        sslProperties.setKeyStore(TEST_KEY_FILE);
        sslProperties.setKeyStorePassword(TEST_KEY_STORE_PASSWORD);
        properties.setSsl(sslProperties);

        assertThat(properties.getBasePath()).isEqualTo(TEST_BASE_PATH);
        assertThat(properties.isPayloadLoggingEnabled()).isTrue();
        assertThat(properties.getSsl().isEnabled()).isTrue();
        assertThat(properties.getSsl().getKeyStoreType()).isEqualTo(TEST_KEY_STORE_TYPE);
        assertThat(properties.getSsl().getKeyStore()).isEqualTo(TEST_KEY_FILE);
        assertThat(properties.getSsl().getKeyStorePassword()).isEqualTo(TEST_KEY_STORE_PASSWORD);
    }

}
