package com.mastercard.developer.config;

import com.mastercard.developer.exception.ServiceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MastercardPropertiesTest {

    private final String basePath = "https://sandbox.api.mastercard.com/direct-service-api";
    private final String keyStorePassword = "keystorepassword";
    private final String keyFile = "";

    @Test
    void propertiesTest() {
        MastercardProperties properties = new MastercardProperties();
        properties.setBasePath(basePath);
        properties.setKeystorePassword(keyStorePassword);
        properties.setKeyFile(keyFile);

        assertNotNull(properties);
        assertEquals(basePath, properties.getBasePath());
        assertEquals(keyStorePassword, properties.getKeystorePassword());
        assertEquals(keyFile, properties.getKeyFile());
    }

    @Test
    void initializeTest() {
        MastercardProperties properties = new MastercardProperties();
        Exception exception = assertThrows(ServiceException.class, properties::initialize);
        assertEquals(MastercardProperties.MISSING_KEY_ERROR, exception.getMessage());
    }

}
