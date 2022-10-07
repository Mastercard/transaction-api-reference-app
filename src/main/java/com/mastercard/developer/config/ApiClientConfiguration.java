package com.mastercard.developer.config;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.openapitools.client.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

@Slf4j
@Configuration
@EnableConfigurationProperties(MastercardProperties.class)
public class ApiClientConfiguration {


    /**
     * This method generates creates and configures an API client for the Mastercard Transaction API.
     * It loads the .p12 or .jks key, correctly sets the base path, and loads other properties
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
        // Load your client certificate
        KeyStore keyStore = KeyStore.getInstance(mcProperties.getFormat());
        keyStore.load(new FileInputStream(mcProperties.getKeyFile()), mcProperties.getKeystorePassword().toCharArray());

        // Configure a secure socket
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, mcProperties.getKeystorePassword().toCharArray());

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);

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
