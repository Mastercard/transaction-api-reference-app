package com.mastercard.developer.transactionapi.context;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.Scheduler;
import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.enums.FlowType;
import com.mastercard.developer.transactionapi.utils.ClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.time.Duration;
import java.util.EnumMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Stream;

/**
 * Keeps track of ongoing requests.
 * In a real application, this would be implemented using a database or a shared cache.
 */
@Slf4j
@Service
public class RequestContextManager {

    private final ScheduledExecutorService executorService;
    private final EnumMap<FlowType, Cache<String,Object>> cacheMap = new EnumMap<>(FlowType.class);

    private final Duration requestTimeout;

    public RequestContextManager(TransactionApiProperties transactionApiProperties) {
        this.requestTimeout = transactionApiProperties.getRequestTimeout();
        this.executorService = Executors.newSingleThreadScheduledExecutor();
        Stream.of(FlowType.values()).forEach(flowType ->
                cacheMap.put(flowType, buildCache()));
    }

    @PreDestroy
    public void shutdown() {
        executorService.shutdownNow();
    }

    /**
     * Checks if there are ongoing requests (response not yet received) of the given type.
     * @param flowType  type of the request
     * @return  true or false
     */
    public boolean haveOngoingRequests(FlowType flowType) {
        return !cacheMap.get(flowType).asMap().isEmpty();
    }

    /**
     * Checks if there are ongoing requests (response not yet received) of any type.
     * @return  true or false
     */
    public boolean haveAnyOngoingRequests() {
        return cacheMap.keySet().stream()
                .anyMatch(this::haveOngoingRequests);
    }

    /**
     * Called when a new request is sent.
     */
    public <R> void onRequestSent(FlowType flowType, String correlationId, R request) {
        cacheMap.get(flowType).put(correlationId, request);
    }

    /**
     * Called when a new response is received.
     */
    public void onResponseReceived(FlowType flowType, String correlationId) {
        cacheMap.get(flowType).invalidate(correlationId);
    }

    /**
     * Called when a new response is received.
     */
    public void waitUntilAllCompleted() {
        while (haveAnyOngoingRequests()) {
            ClientUtils.sleep(Duration.ofMillis(250));
        }
    }

    /**
     * Called when a request expires.
     */
    static void onRequestExpired(String correlationId, Object request) {
        log.error("Request {} with correlationId={} has expired", request.getClass().getSimpleName(), correlationId);

        // NOTE that in this exceptional case that a response was not received before the request has expired,
        //      a recovery process needs to be initiated here (e.g. to submit a reversal if this is an authorisation request).
    }

    private Cache<String,Object> buildCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(requestTimeout)
                .scheduler(
                        Scheduler.forScheduledExecutorService(executorService))
                .removalListener(
                        (key, value, cause) -> {
                            if (cause.equals(RemovalCause.EXPIRED)) {
                                onRequestExpired((String)key, value);
                            }
                        })
                .build();
    }
}
