package com.mastercard.developer.transactionapi.config;

import com.mastercard.developer.transactionapi.utils.SslConfigurer;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.ApiClient;
import org.openapitools.client.api.TransactionApiApi;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_BASE_PATH;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_KEY_FILE;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_KEY_STORE_PASSWORD;
import static com.mastercard.developer.transactionapi.test.TestConstants.TEST_KEY_STORE_TYPE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApiClientConfigurationTest {

    @Mock
    private ApiClient mockApiClient;
    @Mock
    private SslConfigurer mockSslConfigurer;
    @Mock
    private OkHttpClient mockHttpClientWithSslEnabled;

    private final ApiClientConfiguration apiClientConfiguration = new ApiClientConfiguration();

    @Test
    void givenApiClient_whenTransactionApiApi_thenReturnTransactionApiApi() {
        // call
        TransactionApiApi actual = apiClientConfiguration.transactionApiApi(mockApiClient);

        // verify
        assertThat(actual).isInstanceOf(TransactionApiApi.class);
        assertThat(actual.getApiClient()).isSameAs(mockApiClient);
    }

    @Test
    void whenApiClient_thenReturnApiClient() throws GeneralSecurityException, IOException {
        // Setup
        TransactionApiProperties properties = new TransactionApiProperties();
        TransactionApiProperties.SslProperties sslProperties = new TransactionApiProperties.SslProperties();

        properties.setBasePath(TEST_BASE_PATH);
        sslProperties.setKeyStore(TEST_KEY_STORE_PASSWORD);
        sslProperties.setEnabled(true);
        sslProperties.setKeyStoreType(TEST_KEY_STORE_TYPE);
        sslProperties.setKeyStore(TEST_KEY_FILE);

        properties.setSsl(sslProperties);


        when(mockSslConfigurer.enableSsl(any(), eq(sslProperties))).thenReturn(mockHttpClientWithSslEnabled);

        // call
        ApiClient actual = apiClientConfiguration.apiClient(properties, mockSslConfigurer);

        // verify
        assertThat(actual).isNotNull();
        assertThat(actual.getBasePath()).isEqualTo(TEST_BASE_PATH);
        assertThat(actual.getHttpClient()).isSameAs(mockHttpClientWithSslEnabled);
    }

    @Test
    void sslInitializer() {
        // call
        SslConfigurer actual = apiClientConfiguration.sslInitializer();

        // verify
        assertThat(actual).isInstanceOf(SslConfigurer.class);
    }

}
