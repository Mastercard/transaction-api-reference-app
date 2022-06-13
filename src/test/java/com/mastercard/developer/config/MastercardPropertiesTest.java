package com.mastercard.developer.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.mastercard.developer.exception.ServiceException;
import org.junit.jupiter.api.Test;

public class MastercardPropertiesTest {

  private String basePath = "https://sandbox.api.mastercard.com/direct-service-api";
  // Insert MC developer project consumer key into string below
  private String consumerKey = "";
  private String keyStoreAlias = "keyalias";
  private String keyStorePassword = "keystorepassword";
  // Insert path to MC developer project .p12 file into string below
  private String keyFile = "";

  @Test
  public void propertiesTest() {
    MastercardProperties properties = new MastercardProperties();
    properties.setBasePath(basePath);
    properties.setConsumerKey(consumerKey);
    properties.setKeystoreAlias(keyStoreAlias);
    properties.setKeystorePassword(keyStorePassword);
    properties.setKeyFile(keyFile);

    assertNotNull(properties);
    assertEquals(basePath, properties.getBasePath());
    assertEquals(consumerKey, properties.getConsumerKey());
    assertEquals(keyStoreAlias, properties.getKeystoreAlias());
    assertEquals(keyStorePassword, properties.getKeystorePassword());
    assertEquals(keyFile, properties.getKeyFile());
  }

  @Test
  public void initializeTest() {
    MastercardProperties properties = new MastercardProperties();
    Exception exception = assertThrows(ServiceException.class, properties::initialize);
    assertEquals(".p12 file or consumerKey does not exist, please add details in application.properties", exception.getMessage());
  }

}
