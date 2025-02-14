package com.mastercard.developer.transactionapi.client;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionApiConstants {

    public static final String CORRELATION_ID_HEADER = "Correlation-Id";

    public static final String RETRY_AFTER_MS_HEADER = "Mc-Retry-After-Ms";
    public static final int HTTP_STATUS_TOO_EARLY = 425;
}
