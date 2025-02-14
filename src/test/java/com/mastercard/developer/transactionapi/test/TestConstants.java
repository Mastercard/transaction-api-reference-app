package com.mastercard.developer.transactionapi.test;

import com.mastercard.developer.transactionapi.exception.TransactionApiException;
import org.openapitools.client.ApiException;

import java.time.Duration;

public class TestConstants {

    public static final String TEST_CORRELATION_ID = "12345";
    public static final String TEST_CUSTOMER_CONTEXT_KEY = "12345";

    public static final String TEST_CORRELATION_ID_1 = "1116784356";
    public static final String TEST_CORRELATION_ID_2 = "2224254543";

    public static final String TEST_CUSTOMER_CONTEXT_KEY_1 = "1116784356";
    public static final String TEST_CUSTOMER_CONTEXT_KEY_2 = "2224254543";

    public static final int TEST_BATCH_LIMIT = 20;

    public static final long TEST_RETRY_AFTER_MS = 250;
    public static final Duration TEST_RETRY_AFTER = Duration.ofMillis(TEST_RETRY_AFTER_MS);

    public static final String CORRELATION_ID_HEADER = "Correlation-Id";
    public static final String CUSTOMER_CONTEXT_KEY_HEADER = "Customer-Context-Key";
    public static final String RETRY_AFTER_MS_HEADER = "Mc-Retry-After-Ms";

    public static final int HTTP_STATUS_TOO_EARLY = 425;

    public static final Duration WAIT_FOR_REQUESTS_DURATION = Duration.ofMillis(250);
    public static final Duration FAILURE_RECOVERY_DURATION = Duration.ofMillis(1000);

    public static final String TEST_API_EXCEPTION_MESSAGE_AUTH = "Failed to call processAuthorisationRequest";
    public static final String TEST_API_EXCEPTION_MESSAGE_AUTHADVC = "Failed to call processAuthorisationAdviceRequest";
    public static final String TEST_API_EXCEPTION_MESSAGE_INQ = "Failed to call processInquiryRequest";
    public static final String TEST_API_EXCEPTION_MESSAGE_FIN = "Failed to call processFinancialAdviceRequest";
    public static final String TEST_API_EXCEPTION_MESSAGE_REV = "Failed to call processReversalRequest";
    public static final String TEST_API_EXCEPTION_MESSAGE_FINREQ = "Failed to call processFinancialRequest";
    public static final String TEST_API_EXCEPTION_MESSAGE_FINREV = "Failed to call processFinancialReversalAdviceRequest";

    public static final Throwable TEST_TRANSACTION_API_EXCEPTION_INQ_CAUSE = new TransactionApiException(TEST_API_EXCEPTION_MESSAGE_AUTH, new ApiException());

    public static final String TEST_BASE_PATH = "https://sandbox.api.mastercard.com/direct-service-api";
    public static final String TEST_KEY_STORE_PASSWORD = "keystorepassword";
    public static final String TEST_KEY_FILE = "";
    public static final String TEST_KEY_STORE_TYPE = "testkeystoretype";

}
