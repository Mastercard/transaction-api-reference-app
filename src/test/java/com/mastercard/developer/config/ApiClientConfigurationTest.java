package com.mastercard.developer.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ApiClientConfigurationTest {

    public static final String PKCS_12 = "PKCS12";
    private final String basePath = "https://sandbox.api.mastercard.com/direct-service-api";
    private final String keyStorePassword = "keystorepassword";
    private final String keyFile = "keyFilePath";

    @Test
    public void apiClientTest() {
        MastercardProperties mastercardProperties = new MastercardProperties();
        mastercardProperties.setFormat(PKCS_12);
        mastercardProperties.setKeyFile(keyFile);
        mastercardProperties.setBasePath(basePath);
        mastercardProperties.setKeystorePassword(keyStorePassword);

        ApiClientConfiguration apiClientConfiguration = new ApiClientConfiguration();
        Exception exception = Assertions.assertThrows(Exception.class, () -> apiClientConfiguration.apiClient(mastercardProperties));
        assertNotNull(exception.getMessage());
    }

}
