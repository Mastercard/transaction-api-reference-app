package com.mastercard.developer.transactionapi.client.impl;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.AuthorisationResponseItem;
import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import com.mastercard.developer.transactionapi.client.model.FinancialAdviceResponseItem;
import com.mastercard.developer.transactionapi.client.model.InquiryResponseItem;
import com.mastercard.developer.transactionapi.client.model.ReversalResponseItem;
import com.mastercard.developer.transactionapi.exception.MissingHeaderException;
import com.mastercard.developer.transactionapi.exception.TransactionApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.api.TransactionApiApi;
import org.openapitools.client.model.AuthorisationResponseV02List;
import org.openapitools.client.model.AuthorisationinitiationAuthorisationInitiationV02;
import org.openapitools.client.model.AuthorisationresponseAuthorisationResponseV02;
import org.openapitools.client.model.FinancialAdviceResponseV02List;
import org.openapitools.client.model.FinancialinitiationFinancialInitiationV02;
import org.openapitools.client.model.FinancialresponseFinancialResponseV02;
import org.openapitools.client.model.InquiryResponseV01List;
import org.openapitools.client.model.InquiryinitiationInquiryInitiationV01;
import org.openapitools.client.model.InquiryresponseInquiryResponseV01;
import org.openapitools.client.model.ReversalResponseV02List;
import org.openapitools.client.model.ReversalinitiationReversalInitiationV02;
import org.openapitools.client.model.ReversalresponseReversalResponseV02;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * REST client for the Transaction API.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionApiClientImpl implements TransactionApiClient {

    private static final String CORRELATION_ID_HEADER = "Correlation-Id";
    private static final String RETRY_AFTER_MS_HEADER = "Mc-Retry-After-Ms";
    private static final int HTTP_STATUS_TOO_EARLY = 425;
    private static final int RESPONSE_BATCH_LIMIT = 20;

    interface ResponseSupplier<P> {
        ApiResponse<P> get() throws ApiException;
    }

    private final TransactionApiApi transactionApiApi;

    /**
     * Submits the authorisation request for processing.
     * URL: /cain-authorisation-requests
     * Method: POST
     *
     * @param authorisationRequest  authorisation request
     * @return Correlation ID of the accepted request
     */
    @Override
    public String submitAuthorisationRequest(AuthorisationinitiationAuthorisationInitiationV02 authorisationRequest) throws TransactionApiException {
        try {
            log.info("Calling Transaction API processAuthorisationRequest");
            ApiResponse<Void> response = transactionApiApi.transactionApiProcessAuthorisationRequestWithHttpInfo(authorisationRequest);
            log.info("Completed Transaction API processAuthorisationRequest");
            return getCorrelationId(response);
        } catch (Exception e) {
            log.error("Transaction API processAuthorisationRequest failed: {}", e.getMessage(), e);
            throw new TransactionApiException("Failed to call processAuthorisationRequest", e);
        }
    }

    /**
     * Submits the reversal request for processing.
     * URL: /cain-reversal-requests
     * Method: POST
     *
     * @param reversalRequest  reversal request
     * @return Correlation ID of the accepted request
     */
    @Override
    public String submitReversalRequest(ReversalinitiationReversalInitiationV02 reversalRequest) throws TransactionApiException {
        try {
            log.info("Calling Transaction API processReversalRequest");
            ApiResponse<Void> response = transactionApiApi.transactionApiProcessReversalRequestWithHttpInfo(reversalRequest);
            log.info("Completed Transaction API processReversalRequest");
            return getCorrelationId(response);
        } catch (Exception e) {
            log.error("Transaction API processReversalRequest failed: {}", e.getMessage(), e);
            throw new TransactionApiException("Failed to call processReversalRequest", e);
        }
    }

    /**
     * Submits the inquiry request for processing.
     * URL: /cain-inquiry-requests
     * Method: POST
     *
     * @param inquiryRequest inquiry request
     * @return Correlation ID of the accepted request
     */
    @Override
    public String submitInquiryRequest(InquiryinitiationInquiryInitiationV01 inquiryRequest) throws TransactionApiException {
        try {
            log.info("Calling Transaction API processInquiryRequest");
            ApiResponse<Void> response = transactionApiApi.transactionApiProcessInquiryRequestWithHttpInfo(inquiryRequest);
            log.info("Completed Transaction API processInquiryRequest");
            return getCorrelationId(response);
        } catch (Exception e) {
            log.error("Transaction API processInquiryRequest failed: {}", e.getMessage(), e);
            throw new TransactionApiException("Failed to call processInquiryRequest", e);
        }
    }

    /**
     * Submits the financial advice for processing.
     * URL: /cain-financial-advices
     * Method: POST
     *
     * @param financialAdviceRequest  financial advice
     * @return Correlation ID of the accepted request
     */
    @Override
    public String submitFinancialAdviceRequest(FinancialinitiationFinancialInitiationV02 financialAdviceRequest) throws TransactionApiException {
        try {
            log.info("Calling Transaction API processFinancialAdviceRequest");
            ApiResponse<Void> response = transactionApiApi.transactionApiProcessFinancialAdviceRequestWithHttpInfo(financialAdviceRequest);
            log.info("Completed Transaction API processFinancialAdviceRequest");
            return getCorrelationId(response);
        } catch (Exception e) {
            log.error("Transaction API processFinancialAdviceRequest failed: {}", e.getMessage(), e);
            throw new TransactionApiException("Failed to call processFinancialAdviceRequest", e);
        }
    }


    /**
     * Polls for available authorisation responses
     * URL: /cain-authorisation-requests
     * Method: GET
     *
     * @return response batch
     */
    @Override
    public BatchResponse<AuthorisationresponseAuthorisationResponseV02> getAuthorisationResponses() throws TransactionApiException {
        try {
            log.info("Calling Transaction API getAuthorisationResponses");
            ApiResponse<AuthorisationResponseV02List> response = handleTooEarly(() ->
                    transactionApiApi.transactionApiGetAuthorisationResponsesWithHttpInfo(RESPONSE_BATCH_LIMIT));

            BatchResponse<AuthorisationresponseAuthorisationResponseV02> batchResponse = BatchResponse.<AuthorisationresponseAuthorisationResponseV02>builder()
                    .items(wrapItems(response, AuthorisationResponseV02List::getItems, AuthorisationResponseItem::new))
                    .hasMore(response.getStatusCode() == HttpStatus.SC_PARTIAL_CONTENT)
                    .retryAfter(getRetryAfter(response))
                    .build();
            log.info("Completed Transaction API getAuthorisationResponses, httpStatus={}, itemsCount={}", response.getStatusCode(), batchResponse.getItems().size());
            return batchResponse;
        } catch (Exception e) {
            log.error("Transaction API getAuthorisationResponses failed: {}", e.getMessage(), e);
            throw new TransactionApiException("Failed to call getAuthorisationResponses", e);
        }
    }

    /**
     * Polls for available reversal responses
     * URL: /cain-reversal-requests
     * Method: GET
     *
     * @return response batch
     */
    @Override
    public BatchResponse<ReversalresponseReversalResponseV02> getReversalResponses() throws TransactionApiException {
        try {
            log.info("Calling Transaction API getReversalResponses");
            ApiResponse<ReversalResponseV02List> response = handleTooEarly(() ->
                    transactionApiApi.transactionApiGetReversalResponsesWithHttpInfo(RESPONSE_BATCH_LIMIT));

            BatchResponse<ReversalresponseReversalResponseV02> batchResponse = BatchResponse.<ReversalresponseReversalResponseV02>builder()
                    .items(wrapItems(response, ReversalResponseV02List::getItems, ReversalResponseItem::new))
                    .hasMore(response.getStatusCode() == HttpStatus.SC_PARTIAL_CONTENT)
                    .retryAfter(getRetryAfter(response))
                    .build();
            log.info("Completed Transaction API getReversalResponses, httpStatus={}, itemsCount={}", response.getStatusCode(), batchResponse.getItems().size());
            return batchResponse;
        } catch (Exception e) {
            log.error("Transaction API getReversalResponses failed: {}", e.getMessage(), e);
            throw new TransactionApiException("Failed to call getReversalResponses", e);
        }
    }

    /**
     * Polls for available inquiry responses
     * URL: /cain-inquiry-requests
     * Method: GET
     *
     * @return response batch
     */
    @Override
    public BatchResponse<InquiryresponseInquiryResponseV01> getInquiryResponses() throws TransactionApiException {
        try {
            log.info("Calling Transaction API getInquiryResponses");
            ApiResponse<InquiryResponseV01List> response = handleTooEarly(() ->
                    transactionApiApi.transactionApiGetInquiryResponsesWithHttpInfo(RESPONSE_BATCH_LIMIT));

            BatchResponse<InquiryresponseInquiryResponseV01> batchResponse = BatchResponse.<InquiryresponseInquiryResponseV01>builder()
                    .items(wrapItems(response, InquiryResponseV01List::getItems, InquiryResponseItem::new))
                    .hasMore(response.getStatusCode() == HttpStatus.SC_PARTIAL_CONTENT)
                    .retryAfter(getRetryAfter(response))
                    .build();
            log.info("Completed Transaction API getInquiryResponses, httpStatus={}, itemsCount={}", response.getStatusCode(), batchResponse.getItems().size());
            return batchResponse;
        } catch (Exception e) {
            log.error("Transaction API getInquiryResponses failed: {}", e.getMessage(), e);
            throw new TransactionApiException("Failed to call getInquiryResponses", e);
        }
    }

    /**
     * Polls for available financial advice responses
     * URL: /cain-financial-advices
     * Method: GET
     *
     * @return response batch
     */
    @Override
    public BatchResponse<FinancialresponseFinancialResponseV02> getFinancialAdviceResponses() throws TransactionApiException {
        try {
            log.info("Calling Transaction API getFinancialAdviceResponses");
            ApiResponse<FinancialAdviceResponseV02List> response = handleTooEarly(() ->
                    transactionApiApi.transactionApiGetFinancialAdviceResponsesWithHttpInfo(RESPONSE_BATCH_LIMIT));

            BatchResponse<FinancialresponseFinancialResponseV02> batchResponse = BatchResponse.<FinancialresponseFinancialResponseV02>builder()
                    .items(wrapItems(response, FinancialAdviceResponseV02List::getItems, FinancialAdviceResponseItem::new))
                    .hasMore(response.getStatusCode() == HttpStatus.SC_PARTIAL_CONTENT)
                    .retryAfter(getRetryAfter(response))
                    .build();
            log.info("Completed Transaction API getFinancialAdviceResponses, httpStatus={}, itemsCount={}", response.getStatusCode(), batchResponse.getItems().size());
            return batchResponse;
        } catch (Exception e) {
            log.error("Transaction API getFinancialAdviceResponses failed: {}", e.getMessage(), e);
            throw new TransactionApiException("Failed to call getFinancialAdviceResponses", e);
        }
    }

    /**
     * Extracts the Correlation ID from the response header.
     */
    private String getCorrelationId(ApiResponse<?> response) {
        List<String> headerValues = response.getHeaders().get(CORRELATION_ID_HEADER);
        if (CollectionUtils.isEmpty(headerValues)) {
            throw new MissingHeaderException("Missing required " + CORRELATION_ID_HEADER + " in the response");
        }
        return headerValues.get(0);
    }

    /**
     * Extracts the Retry After from the response header.
     */
    private Duration getRetryAfter(ApiResponse<?> response) {
        List<String> headerValues = response.getHeaders().get(RETRY_AFTER_MS_HEADER);
        if (CollectionUtils.isEmpty(headerValues)) {
            return Duration.ZERO;
        } else {
            return Duration.ofMillis(Long.parseLong(headerValues.get(0)));
        }
    }

    /**
     * The generated ApiClient throws an ApiException if the Transaction API returns 425 Too Early.
     * This method converts it into an empty response with the Retry-After-Ms header,
     * so that the application can continue polling after the delay.
     */
    private <P> ApiResponse<P> handleTooEarly(ResponseSupplier<P> caller) throws ApiException {
        try {
            return caller.get();
        } catch (ApiException apiException) {
            if (apiException.getCode() == HTTP_STATUS_TOO_EARLY) {
                log.warn("Called Transaction API too early after the previous attempt");
                return new ApiResponse<>(apiException.getCode(), apiException.getResponseHeaders(), null);
            } else {
                throw apiException;
            }
        }
    }

    /**
     * Wraps each item of the response list with an Adapter, so that they can be accessed using a common interface.
     */
    private <L, I, A> List<A> wrapItems(ApiResponse<L> response, Function<L, List<I>> getItems, Function<I, A> adapterCreator) {
        List<I> items = Optional.ofNullable(response.getData())
                .map(getItems)
                .orElse(Collections.emptyList());
        return items.stream()
                .map(adapterCreator)
                .collect(Collectors.toList());
    }

}