package com.mastercard.developer.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ApiClientConfigurationTest {

  private String basePath = "https://sandbox.api.mastercard.com/direct-service-api";
  // Insert MC developer project consumer key into string below
  private String consumerKey = "";
  private String keyStoreAlias = "keyalias";
  private String keyStorePassword = "keystorepassword";
  // Insert path to MC developer project .p12 file into string below
  private String keyFile = "keyFilePath";

  @Test
  public void apiClientTest() {
    MastercardProperties mastercardProperties = new MastercardProperties();
    mastercardProperties.setKeyFile(keyFile);
    mastercardProperties.setBasePath(basePath);
    mastercardProperties.setConsumerKey(consumerKey);
    mastercardProperties.setKeystoreAlias(keyStoreAlias);
    mastercardProperties.setKeystorePassword(keyStorePassword);

    ApiClientConfiguration apiClientConfiguration = new ApiClientConfiguration();
    Exception exception = Assertions.assertThrows(Exception.class, () -> apiClientConfiguration.apiClient(mastercardProperties));
    assertNotNull(exception.getMessage());
  }

}
