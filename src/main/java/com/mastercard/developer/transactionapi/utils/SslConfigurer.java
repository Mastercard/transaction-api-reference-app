package com.mastercard.developer.transactionapi.utils;

import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import okhttp3.OkHttpClient;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;

public class SslConfigurer {

    /**
     * Configures SSL for an HTTP client.
     */
    public OkHttpClient enableSsl(OkHttpClient httpClient, TransactionApiProperties.SslProperties sslProperties) throws IOException, GeneralSecurityException {
        if (!sslProperties.isEnabled()) {
            return httpClient;
        }
        KeyStore keyStore = KeyStore.getInstance(sslProperties.getKeyStoreType());
        try (InputStream keyStoreInputStream = getFileInputStream(sslProperties.getKeyStore())) {
            keyStore.load(keyStoreInputStream, sslProperties.getKeyStorePassword().toCharArray());
        }

        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, sslProperties.getKeyStorePassword().toCharArray());

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());

        return httpClient.newBuilder()
                .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustManagerFactory.getTrustManagers()[0])
                .build();
    }

    protected InputStream getFileInputStream(String fileName) throws IOException {
        return new FileInputStream(fileName);
    }
}
