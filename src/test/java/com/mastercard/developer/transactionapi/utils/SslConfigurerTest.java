package com.mastercard.developer.transactionapi.utils;

import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import java.io.ByteArrayInputStream;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class SslConfigurerTest {

    private static final String TEST_P12_B64 = "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";
    private static final String TEST_JKS_B64 = "/u3+7QAAAAIAAAABAAAAAQAGY2xpZW50AAABiOQCNXYAAAK6MIICtjAOBgorBgEEASoCEQEBBQAEggKiC7tJRl6uHnbdQJR+yoM+O6I0WY+YpHiRa5UK5WslfY+cTB3oaI7BXrK8+I96JbSUNOXhDhKcBCVT6gQLDxYIrNm8mABXKUpIZmF37s9olhGwsYhbuoTz0KjSniFjC2hlVXs2LGHFU6OX2yc76URHr5PqnTvzW9FBRRGjrUnDETCZekPWmVmSycVXyz6zFCYUnDdd7/h+edmo6Ko4gcmVObraMviiKw4TREqk7SOMh/jQEsQOKuWLOSjSdoXKUkTfQmizaIwV2S5qfMXaC92rc3cZgqBUUQ0+il/mlaT6k9YrDaT2N1bqtOHTelMndFjOuJGvFzH1ycNFnrp7UEAMKu7PuzgdlJk6r+2VpAoDIelHji7NyslRNs1R2Sh1OevWb/jCkaOUXylVMyC8hKgTrO6/fk7V0l5Gc5KFKpOXYjXNqPHeC287afus7OnrcR/cSjV9mR/mak+ix776vWl0/SzNayQhg1YH1Yqztm6qqB+98AnxB5m9iNTPndgrP6yB1cgtnqM+bU2g0MEe1bvJ2lTLb+nDJkcbssTYg2rHhvkGiAFgoUZ9XT+BLd9t3oHgORN4TVtqV0L9XrhRD0SHBbH55wDudectRXXbW144SX3r8UMIOp/VOfZzcBzLXw+1Dn1TR0KDIMeTa+Vf2uv9TbAiY/CU1TFVd6JyTqM3bw78S1W4pSy/5X7WAKhKHLk7MdEWROyv2wqyZ42n3FSZeuiu2n1ropcIThwE4F1avcpEwfoMSOtF2cHMjB8Dk26DLNQe5FBNRvF4w3R6cvk4jCm3FFapdsC9VaDEkHPGahdmOGTx2PP+QJfO1azHCtRHTOAgcnRTAD8SkRLsjfVU3fZWFyNpbjwdUIll2dngupA61eDvccjo2JPk//Ab3vLmt3MAAAABAAVYLjUwOQAAAlAwggJMMIIBtaADAgECAgQYuJruMA0GCSqGSIb3DQEBCwUAMFgxCzAJBgNVBAYTAkVVMQ0wCwYDVQQIEwRUZXN0MQ0wCwYDVQQHEwRUZXN0MQ0wCwYDVQQKEwRUZXN0MQ0wCwYDVQQLEwRUZXN0MQ0wCwYDVQQDEwRUZXN0MCAXDTIzMDYyMjE2NDgyN1oYDzIwNTAxMTA2MTY0ODI3WjBYMQswCQYDVQQGEwJFVTENMAsGA1UECBMEVGVzdDENMAsGA1UEBxMEVGVzdDENMAsGA1UEChMEVGVzdDENMAsGA1UECxMEVGVzdDENMAsGA1UEAxMEVGVzdDCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEAm+FNhu/+g9uITbSlxIE3eqeE2WL5R+ILeS/WGMJ7PcQE2jNC8Bsq6dSDdAQijVohzeB75wWhn+45BBR6NXbENc5QxzALBXTKOSBjk3aGVWW9S64McBQNIsbNxenIX9kRSJdAEpH9mr0pF7uHew4eJrGPUNGMP5lPtGz/Yt/WGBMCAwEAAaMhMB8wHQYDVR0OBBYEFPJXayo9i6qGXKaxURm9byFa1JHRMA0GCSqGSIb3DQEBCwUAA4GBAGiBPFErqlXDYLPp3aPJOmbJiBleQ8MTltDb7y24KXwb6VEdpzER+0l2Z0TM1v/q4xRmnSnqbaiINhBhVbK351NFBTYgNNtUI3B/zZ61HsJNZTSvmayh0CO9hFR71CkNHowHTDE+XExrsqjSeZRalFxIcK/UpjUWxaSRycFWDp4Pkl6ue7vTMlBCo29viFkIJ8Y339A=";

    private static final String TEST_KEYSTORE_P12 = "testkey.p12";
    private static final String TEST_KEYSTORE_JKS = "testkey.jks";
    private static final String TEST_KEYSTORE_PASSWORD = "keystorepassword";

    @Mock
    private OkHttpClient mockHttpClient;
    @Mock
    private OkHttpClient.Builder mockHttpClientBuilder;
    @Mock
    private OkHttpClient builtHttpClient;

    @Captor
    private ArgumentCaptor<SSLSocketFactory> sslSocketFactoryCaptor;
    @Captor
    private ArgumentCaptor<X509TrustManager> trustManagerCaptor;

    @Spy
    private SslConfigurer sslConfigurer;

    private TransactionApiProperties.SslProperties sslProperties = new TransactionApiProperties.SslProperties();

    @BeforeEach
    void setUp() {
        lenient().when(mockHttpClient.newBuilder()).thenReturn(mockHttpClientBuilder);
        lenient().when(mockHttpClientBuilder.sslSocketFactory(sslSocketFactoryCaptor.capture(), trustManagerCaptor.capture())).thenReturn(mockHttpClientBuilder);
        lenient().when(mockHttpClientBuilder.build()).thenReturn(builtHttpClient);
    }

    @Test
    void enableSsl_whenDisabled() throws Exception {
        // setup
        sslProperties.setEnabled(false);

        // call
        OkHttpClient result = sslConfigurer.enableSsl(mockHttpClient, sslProperties);

        // verify
        assertThat(result).isSameAs(mockHttpClient);
        verifyNoInteractions(mockHttpClient);
    }

    @Test
    void enableSsl_whenP12() throws Exception {
        // setup
        sslProperties.setEnabled(true);
        sslProperties.setKeyStoreType("PKCS12");
        sslProperties.setKeyStore(TEST_KEYSTORE_P12);
        sslProperties.setKeyStorePassword(TEST_KEYSTORE_PASSWORD);

        doReturn(new ByteArrayInputStream(Base64.getDecoder().decode(TEST_P12_B64)))
                .when(sslConfigurer).getFileInputStream(TEST_KEYSTORE_P12);

        // call
        OkHttpClient result = sslConfigurer.enableSsl(mockHttpClient, sslProperties);

        // verify
        assertThat(result).isSameAs(builtHttpClient);
        assertThat(sslSocketFactoryCaptor.getValue()).isNotNull();
        assertThat(trustManagerCaptor.getValue()).isNotNull();
    }

    @Test
    void enableSsl_whenJKS() throws Exception {
        // setup
        sslProperties.setEnabled(true);
        sslProperties.setKeyStoreType("JKS");
        sslProperties.setKeyStore(TEST_KEYSTORE_JKS);
        sslProperties.setKeyStorePassword(TEST_KEYSTORE_PASSWORD);

        doReturn(new ByteArrayInputStream(Base64.getDecoder().decode(TEST_JKS_B64)))
                .when(sslConfigurer).getFileInputStream(TEST_KEYSTORE_JKS);

        // call
        OkHttpClient result = sslConfigurer.enableSsl(mockHttpClient, sslProperties);

        // verify
        assertThat(result).isSameAs(builtHttpClient);
        assertThat(sslSocketFactoryCaptor.getValue()).isNotNull();
        assertThat(trustManagerCaptor.getValue()).isNotNull();
    }
}