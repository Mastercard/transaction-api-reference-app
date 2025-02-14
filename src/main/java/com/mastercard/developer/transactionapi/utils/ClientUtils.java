package com.mastercard.developer.transactionapi.utils;

import com.mastercard.developer.transactionapi.exception.RuntimeInterruptedException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientUtils {

    public static void sleep(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException ie) {     // NOSONAR no need to log
            log.info("Thread interrupted");
            Thread.currentThread().interrupt();
            throw new RuntimeInterruptedException();
        }
    }

    public static String convertPayloadForLogging(String json, boolean loggingEnabled) {
        return loggingEnabled ? json : "omitted payload";
    }

    public static String getValueFromHeaders(String key, Map<String, List<String>> headers){
        return Optional.ofNullable(headers)
                .map(h -> h.get(key))
                .filter(l -> !l.isEmpty())
                .map(l -> l.get(0))
                .orElse(null);
    }
}
