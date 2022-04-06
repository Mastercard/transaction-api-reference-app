package com.mastercard.developer.config;

import com.mastercard.developer.interceptors.OkHttpOAuth1Interceptor;
import com.mastercard.developer.utils.AuthenticationUtils;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import org.openapitools.client.ApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.security.PrivateKey;

@Slf4j
@Configuration
@EnableConfigurationProperties(MastercardProperties.class)
public class ApiClientConfiguration {


  /**
   * This method generates creates and configures an API client for the Mastercard Transaction API.
   * It loads the .p12 key, correctly sets the base path, and loads other properties
   * for making requests.
   *
   * @param mcProperties - MC Developer properties set in the application.properties file
   * @return ApiClient
   */
  @Bean
  public ApiClient apiClient(@Autowired MastercardProperties mcProperties)
      throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyStoreException, NoSuchProviderException {
    ApiClient client = new ApiClient();
    client.setBasePath(mcProperties.getBasePath());
    client.setDebugging(true);
    client.setReadTimeout(40000);

    return client.setHttpClient(client.getHttpClient().newBuilder()
        .addInterceptor(new OkHttpOAuth1Interceptor(mcProperties.getConsumerKey(), getPrivateKey(mcProperties)))
        .build()
    );
  }

  /**
   * Gets Private Key
   * @param mcProperties MastercardProperties
   * @return instance of PrivateKey
   * @throws CertificateException
   * @throws UnrecoverableKeyException
   * @throws NoSuchAlgorithmException
   * @throws IOException
   * @throws KeyStoreException
   * @throws NoSuchProviderException
   */
  public PrivateKey getPrivateKey(MastercardProperties mcProperties)
      throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyStoreException, NoSuchProviderException {
    return AuthenticationUtils
        .loadSigningKey(mcProperties.getKeyFile(), mcProperties.getKeystoreAlias(), mcProperties.getKeystorePassword());
  }
}
