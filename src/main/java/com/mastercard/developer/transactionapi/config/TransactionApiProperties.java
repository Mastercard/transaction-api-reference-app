package com.mastercard.developer.transactionapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.Duration;

/**
 * Properties for the Transaction API client.
 */
@Data
@Validated
@ConfigurationProperties(prefix = "transaction-api")
public class TransactionApiProperties {

    @Data
    public static class SslProperties {
        /**
         * Whether SSL is enabled.
         */
        private boolean enabled;

        /**
         * Type of the key store, eg "PKCS12" or "JKS"
         */
        @NotEmpty
        private String keyStoreType;

        /**
         * Key store file name.
         */
        @NotEmpty
        private String keyStore;

        /**
         * Key store password.
         */
        @NotEmpty
        private String keyStorePassword;
    }

    /**
     * Base path for the Transaction API endpoints.
     */
    @NotEmpty
    private String basePath;

    /**
     * SSL properties
     */
    @Valid
    private SslProperties ssl;

    /**
     * Period of time after which a request is considered expired if no response has been received.
     */
    private Duration requestTimeout;

    /**
     * Property to enable payload logging
     */
    private boolean payloadLoggingEnabled;

}
