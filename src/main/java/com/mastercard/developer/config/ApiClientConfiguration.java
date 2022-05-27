package com.mastercard.developer.config;

import com.mastercard.developer.interceptors.OkHttpOAuth1Interceptor;
import com.mastercard.developer.utils.AuthenticationUtils;

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
    client.setBasePath(mcProperties.getBasePath());
    client.setDebugging(true);
    client.setReadTimeout(40000);

    KeyStore pkcs12KeyStore = KeyStore.getInstance("PKCS12");
    pkcs12KeyStore.load(new FileInputStream(mcProperties.getKeyFile()), mcProperties.getKeystorePassword().toCharArray());

    KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
    keyManagerFactory.init(pkcs12KeyStore, mcProperties.getKeystorePassword().toCharArray());

    TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
    trustManagerFactory.init(pkcs12KeyStore);

    SSLContext sslContext = SSLContext.getInstance("TLS");
    sslContext.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());

    client.setHttpClient(
            client.getHttpClient().newBuilder()
                    .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustManagerFactory.getTrustManagers()[0])
                    .build()
    );

    return client;
  }

}
