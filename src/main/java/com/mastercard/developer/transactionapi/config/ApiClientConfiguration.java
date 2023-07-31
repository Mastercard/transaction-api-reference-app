package com.mastercard.developer.transactionapi.config;

import com.mastercard.developer.transactionapi.utils.SslConfigurer;
import okhttp3.OkHttpClient;
import org.openapitools.client.ApiClient;
import org.openapitools.client.api.TransactionApiApi;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * Configures the ApiClient for the Transaction API.
 */
@Configuration
@EnableConfigurationProperties(TransactionApiProperties.class)
public class ApiClientConfiguration {

    @Bean
    public TransactionApiApi transactionApiApi(ApiClient apiClient) {
        return new TransactionApiApi(apiClient);
    }

    @Bean
    public ApiClient apiClient(TransactionApiProperties tapiProperties, SslConfigurer sslConfigurer) throws GeneralSecurityException, IOException {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(tapiProperties.getBasePath());

        OkHttpClient httpClient = apiClient.getHttpClient();
        httpClient = sslConfigurer.enableSsl(httpClient, tapiProperties.getSsl());
        apiClient.setHttpClient(httpClient);

        return apiClient;
    }

    @Bean
    public SslConfigurer sslInitializer() {
        return new SslConfigurer();
    }

}
