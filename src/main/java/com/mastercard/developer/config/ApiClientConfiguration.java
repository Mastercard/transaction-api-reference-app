package com.mastercard.developer.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

import okhttp3.OkHttpClient;
import org.openapitools.client.ApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

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
          throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyStoreException, KeyManagementException {
    ApiClient client = new ApiClient();
    OkHttpClient.Builder httpClientBuilder = client.getHttpClient().newBuilder();
    // Configure the Mastercard service URL
    client.setBasePath(mcProperties.getBasePath());
    client.setDebugging(true);
    client.setReadTimeout(40000);
    client.addDefaultHeader("x-openapi-clientid", "ref-app");
    client.addDefaultHeader("x-openapi-keyid", "ref-app-key");

    // Load your client certificate
    KeyStore pkcs12KeyStore = KeyStore.getInstance(mcProperties.getFormat());
    pkcs12KeyStore.load(new FileInputStream(mcProperties.getKeyFile()), mcProperties.getKeystorePassword().toCharArray());

    // Configure a secure socket
    KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
    keyManagerFactory.init(pkcs12KeyStore, mcProperties.getKeystorePassword().toCharArray());

    TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
    trustManagerFactory.init(pkcs12KeyStore);

    SSLContext sslContext = SSLContext.getInstance("TLS");
    sslContext.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());

    client.setHttpClient(
      httpClientBuilder
        .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustManagerFactory.getTrustManagers()[0])
        .build()
    );
    return client;
  }

}
