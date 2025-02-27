package com.mastercard.developer.transactionapi.client.impl;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.AuthorisationAdviceResponseStatus;
import com.mastercard.developer.transactionapi.client.model.AuthorisationResponseStatus;
import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import com.mastercard.developer.transactionapi.client.model.FinancialAdviceResponseStatus;
import com.mastercard.developer.transactionapi.client.model.FinancialRequestResponseStatus;
import com.mastercard.developer.transactionapi.client.model.FinancialReversalAdviceResponseStatus;
import com.mastercard.developer.transactionapi.client.model.InquiryResponseStatus;
import com.mastercard.developer.transactionapi.client.model.ReversalResponseStatus;
import com.mastercard.developer.transactionapi.exception.TransactionApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.api.TransactionApiApi;
import org.openapitools.client.model.AuthorisationAdviceResponseV02List;
import org.openapitools.client.model.AuthorisationInitiationAuthorisationInitiationV02;
import org.openapitools.client.model.AuthorisationResponseAuthorisationResponseV02;
import org.openapitools.client.model.AuthorisationResponseV02List;
import org.openapitools.client.model.FinancialAdviceResponseV02List;
import org.openapitools.client.model.FinancialInitiationFinancialInitiationV02;
import org.openapitools.client.model.FinancialRequestInitiationFinancialInitiationV02;
import org.openapitools.client.model.FinancialRequestResponseFinancialResponseV02;
import org.openapitools.client.model.FinancialRequestResponseV02List;
import org.openapitools.client.model.FinancialResponseFinancialResponseV02;
import org.openapitools.client.model.FinancialReversalAdviceResponseV02List;
import org.openapitools.client.model.InquiryInitiationInquiryInitiationV01;
import org.openapitools.client.model.InquiryResponseInquiryResponseV01;
import org.openapitools.client.model.InquiryResponseV01List;
import org.openapitools.client.model.ReversalFinancialAdviceInitiationReversalInitiationV02;
import org.openapitools.client.model.ReversalFinancialAdviceResponseReversalResponseV02;
import org.openapitools.client.model.ReversalInitiationReversalInitiationV02;
import org.openapitools.client.model.ReversalResponseReversalResponseV02;
import org.openapitools.client.model.ReversalResponseV02List;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.mastercard.developer.transactionapi.client.TransactionApiConstants.HTTP_STATUS_TOO_EARLY;
import static com.mastercard.developer.transactionapi.client.TransactionApiConstants.RETRY_AFTER_MS_HEADER;

/**
 * REST client for the Transaction API.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionApiClientImpl implements TransactionApiClient {
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
     * @param customerContextKey   transaction identifier
     * @param authorisationRequest authorisation request
     * @return ApiResponse with response payload for request
     */
    @Override
    public ApiResponse<AuthorisationResponseAuthorisationResponseV02> submitAuthorisationRequest(String customerContextKey, AuthorisationInitiationAuthorisationInitiationV02 authorisationRequest) throws TransactionApiException {
        try {
            log.info("Calling Transaction API processAuthorisationRequest");
            ApiResponse<AuthorisationResponseAuthorisationResponseV02> response = transactionApiApi.processAuthorisationRequestWithHttpInfo(customerContextKey,authorisationRequest);
            log.info("Completed Transaction API processAuthorisationRequest");
            return response;
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
     * @param customerContextKey transaction identifier
     * @param reversalRequest    reversal request
     * @return ApiResponse with response payload for request
     */
    @Override
    public ApiResponse<ReversalResponseReversalResponseV02> submitReversalRequest(String customerContextKey, ReversalInitiationReversalInitiationV02 reversalRequest) throws TransactionApiException {
        try {
            log.info("Calling Transaction API processReversalRequest");
            ApiResponse<ReversalResponseReversalResponseV02> response = transactionApiApi.processReversalRequestWithHttpInfo(customerContextKey,reversalRequest);
            log.info("Completed Transaction API processReversalRequest");
            return response;
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
     * @param customerContextKey transaction identifier
     * @param inquiryRequest     inquiry request
     * @return ApiResponse with response payload for request
     */
    @Override
    public ApiResponse<InquiryResponseInquiryResponseV01> submitInquiryRequest(String customerContextKey, InquiryInitiationInquiryInitiationV01 inquiryRequest) throws TransactionApiException {
        try {
            log.info("Calling Transaction API processInquiryRequest");
            ApiResponse<InquiryResponseInquiryResponseV01> response = transactionApiApi.processInquiryRequestWithHttpInfo(customerContextKey,inquiryRequest);
            log.info("Completed Transaction API processInquiryRequest");
            return response;
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
     * @param customerContextKey     transaction identifier
     * @param financialAdviceRequest financial advice
     * @return ApiResponse with response payload for request
     */
    @Override
    public ApiResponse<FinancialResponseFinancialResponseV02> submitFinancialAdviceRequest(String customerContextKey, FinancialInitiationFinancialInitiationV02 financialAdviceRequest) throws TransactionApiException {
        try {
            log.info("Calling Transaction API processFinancialAdviceRequest");
            ApiResponse<FinancialResponseFinancialResponseV02> response = transactionApiApi.processFinancialAdviceRequestWithHttpInfo(customerContextKey,financialAdviceRequest);
            log.info("Completed Transaction API processFinancialAdviceRequest");
            return response;
        } catch (Exception e) {
            log.error("Transaction API processFinancialAdviceRequest failed: {}", e.getMessage(), e);
            throw new TransactionApiException("Failed to call processFinancialAdviceRequest", e);
        }
    }

    /**
     * Submits the financial request for processing.
     * URL: /cain-financial-requests
     * Method: POST
     *
     * @param customerContextKey transaction identifier
     * @param financialRequest   financial request
     * @return ApiResponse with response payload for request
     */
    @Override
    public ApiResponse<FinancialRequestResponseFinancialResponseV02> submitFinancialRequest(String customerContextKey, FinancialRequestInitiationFinancialInitiationV02 financialRequest) throws TransactionApiException {
        try {
            log.info("Calling Transaction API processFinancialRequest");
            ApiResponse<FinancialRequestResponseFinancialResponseV02> response = transactionApiApi.processFinancialRequestWithHttpInfo(customerContextKey,financialRequest);
            log.info("Completed Transaction API processFinancialRequest");
            return response;
        } catch (Exception e) {
            log.error("Transaction API processFinancialRequest failed: {}", e.getMessage(), e);
            throw new TransactionApiException("Failed to call processFinancialRequest", e);
        }
    }

    /**
     * Submits the financial reversal advice for processing.
     * URL: /cain-financial-reversal-advices
     * Method: POST
     *
     * @param customerContextKey             transaction identifier
     * @param financialReversalAdviceRequest financial reversal advice
     * @return ApiResponse with response payload for request
     */
    @Override
    public ApiResponse<ReversalFinancialAdviceResponseReversalResponseV02> submitFinancialReversalAdvice(String customerContextKey, ReversalFinancialAdviceInitiationReversalInitiationV02 financialReversalAdviceRequest) throws TransactionApiException {
        try {
            log.info("Calling Transaction API processFinancialReversalAdviceRequest");
            ApiResponse<ReversalFinancialAdviceResponseReversalResponseV02> response = transactionApiApi.processFinancialReversalAdviceWithHttpInfo(customerContextKey,financialReversalAdviceRequest);
            log.info("Completed Transaction API processFinancialReversalAdvice");
            return response;
        } catch (Exception e) {
            log.error("Transaction API processFinancialReversalAdvice failed: {}", e.getMessage(), e);
            throw new TransactionApiException("Failed to call processFinancialReversalAdviceRequest", e);
        }
    }

    /**
     * Submits the authorisation advice request for processing.
     * URL: /cain-authorisation-advices
     * Method: POST
     *
     * @param customerContextKey   transaction identifier
     * @param authorisationAdviceRequest authorisation advice request
     * @return ApiResponse with response payload for request
     */
    @Override
    public ApiResponse<AuthorisationResponseAuthorisationResponseV02> submitAuthorisationAdviceRequest(String customerContextKey, AuthorisationInitiationAuthorisationInitiationV02 authorisationAdviceRequest) throws TransactionApiException {
        try {
            log.info("Calling Transaction API processAuthorisationAdviceRequest");
            ApiResponse<AuthorisationResponseAuthorisationResponseV02> response = transactionApiApi.processAuthorisationAdviceRequestWithHttpInfo(customerContextKey,authorisationAdviceRequest);
            log.info("Completed Transaction API processAuthorisationAdviceRequest");
            return response;
        } catch (Exception e) {
            log.error("Transaction API processAuthorisationAdviceRequest failed: {}", e.getMessage(), e);
            throw new TransactionApiException("Failed to call processAuthorisationAdviceRequest", e);
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
    public BatchResponse<AuthorisationResponseAuthorisationResponseV02> getAuthorisationResponses() throws TransactionApiException {
        try {
            log.info("Calling Transaction API getAuthorisationResponses");
            ApiResponse<AuthorisationResponseV02List> response = handleTooEarly(() ->
                    transactionApiApi.getAuthorisationResponsesWithHttpInfo(RESPONSE_BATCH_LIMIT, null, null));

            BatchResponse<AuthorisationResponseAuthorisationResponseV02> batchResponse = BatchResponse.<AuthorisationResponseAuthorisationResponseV02>builder()
                    .items(wrapItems(response, AuthorisationResponseV02List::getItems, AuthorisationResponseStatus::new))
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
    public BatchResponse<ReversalResponseReversalResponseV02> getReversalResponses() throws TransactionApiException {
        try {
            log.info("Calling Transaction API getReversalResponses");
            ApiResponse<ReversalResponseV02List> response = handleTooEarly(() ->
                    transactionApiApi.getReversalResponsesWithHttpInfo(RESPONSE_BATCH_LIMIT, null, null));

            BatchResponse<ReversalResponseReversalResponseV02> batchResponse = BatchResponse.<ReversalResponseReversalResponseV02>builder()
                    .items(wrapItems(response, ReversalResponseV02List::getItems, ReversalResponseStatus::new))
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
    public BatchResponse<InquiryResponseInquiryResponseV01> getInquiryResponses() throws TransactionApiException {
        try {
            log.info("Calling Transaction API getInquiryResponses");
            ApiResponse<InquiryResponseV01List> response = handleTooEarly(() ->
                    transactionApiApi.getInquiryResponsesWithHttpInfo(RESPONSE_BATCH_LIMIT, null, null));

            BatchResponse<InquiryResponseInquiryResponseV01> batchResponse = BatchResponse.<InquiryResponseInquiryResponseV01>builder()
                    .items(wrapItems(response, InquiryResponseV01List::getItems, InquiryResponseStatus::new))
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
    public BatchResponse<FinancialResponseFinancialResponseV02> getFinancialAdviceResponses() throws TransactionApiException {
        try {
            log.info("Calling Transaction API getFinancialAdviceResponses");
            ApiResponse<FinancialAdviceResponseV02List> response = handleTooEarly(() ->
                    transactionApiApi.getFinancialAdviceResponsesWithHttpInfo(RESPONSE_BATCH_LIMIT, null, null));

            BatchResponse<FinancialResponseFinancialResponseV02> batchResponse = BatchResponse.<FinancialResponseFinancialResponseV02>builder()
                    .items(wrapItems(response, FinancialAdviceResponseV02List::getItems, FinancialAdviceResponseStatus::new))
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
     * Polls for available financial responses
     * URL: /cain-financial-requests
     * Method: GET
     *
     * @return response batch
     */
    @Override
    public BatchResponse<FinancialRequestResponseFinancialResponseV02> getFinancialRequestResponses() throws TransactionApiException {
        try {
            log.info("Calling Transaction API getFinancialRequestResponses");
            ApiResponse<FinancialRequestResponseV02List> response = handleTooEarly(() ->
                    transactionApiApi.getFinancialRequestResponsesWithHttpInfo(RESPONSE_BATCH_LIMIT, null, null));

            BatchResponse<FinancialRequestResponseFinancialResponseV02> batchResponse = BatchResponse.<FinancialRequestResponseFinancialResponseV02>builder()
                    .items(wrapItems(response, FinancialRequestResponseV02List::getItems, FinancialRequestResponseStatus::new))
                    .hasMore(response.getStatusCode() == HttpStatus.SC_PARTIAL_CONTENT)
                    .retryAfter(getRetryAfter(response))
                    .build();
            log.info("Completed Transaction API getFinancialRequestResponses, httpStatus={}, itemsCount={}", response.getStatusCode(), batchResponse.getItems().size());
            return batchResponse;
        } catch (Exception e) {
            log.error("Transaction API getFinancialRequestResponses failed: {}", e.getMessage(), e);
            throw new TransactionApiException("Failed to call getFinancialRequestResponses", e);
        }
    }

    /**
     * Polls for available financial reversal advice
     * URL: /cain-financial-reversal-advices
     * Method: GET
     *
     * @return response batch
     */
    @Override
    public BatchResponse<ReversalFinancialAdviceResponseReversalResponseV02> getFinancialReversalAdviceResponses() throws TransactionApiException {
        try {
            log.info("Calling Transaction API getFinancialReversalAdviceResponses");
            ApiResponse<FinancialReversalAdviceResponseV02List> response = handleTooEarly(() ->
                    transactionApiApi.getFinancialReversalAdviceResponsesWithHttpInfo(RESPONSE_BATCH_LIMIT, null, null));

            BatchResponse<ReversalFinancialAdviceResponseReversalResponseV02> batchResponse = BatchResponse.<ReversalFinancialAdviceResponseReversalResponseV02>builder()
                    .items(wrapItems(response, FinancialReversalAdviceResponseV02List::getItems, FinancialReversalAdviceResponseStatus::new))
                    .hasMore(response.getStatusCode() == HttpStatus.SC_PARTIAL_CONTENT)
                    .retryAfter(getRetryAfter(response))
                    .build();
            log.info("Completed Transaction API getFinancialReversalAdviceResponses, httpStatus={}, itemsCount={}", response.getStatusCode(), batchResponse.getItems().size());
            return batchResponse;
        } catch (Exception e) {
            log.error("Transaction API getFinancialReversalAdviceResponses failed: {}", e.getMessage(), e);
            throw new TransactionApiException("Failed to call getFinancialReversalAdviceResponses", e);
        }
    }

    /**
     * Polls for available authorisation advice responses
     * URL: /cain-authorisation-advice
     * Method: GET
     *
     * @return response batch
     */
    @Override
    public BatchResponse<AuthorisationResponseAuthorisationResponseV02> getAuthorisationAdviceResponses() throws TransactionApiException {
        try {
            log.info("Calling Transaction API getAuthorisationAdviceResponses");
            ApiResponse<AuthorisationAdviceResponseV02List> response = handleTooEarly(() ->
                    transactionApiApi.getAuthorisationAdviceResponsesWithHttpInfo(RESPONSE_BATCH_LIMIT, null, null));

            BatchResponse<AuthorisationResponseAuthorisationResponseV02> batchResponse = BatchResponse.<AuthorisationResponseAuthorisationResponseV02>builder()
                    .items(wrapItems(response, AuthorisationAdviceResponseV02List::getItems, AuthorisationAdviceResponseStatus::new))
                    .hasMore(response.getStatusCode() == HttpStatus.SC_PARTIAL_CONTENT)
                    .retryAfter(getRetryAfter(response))
                    .build();
            log.info("Completed Transaction API getAuthorisationAdviceResponses, httpStatus={}, itemsCount={}", response.getStatusCode(), batchResponse.getItems().size());
            return batchResponse;
        } catch (Exception e) {
            log.error("Transaction API getAuthorisationAdviceResponses failed: {}", e.getMessage(), e);
            throw new TransactionApiException("Failed to call getAuthorisationAdviceResponses", e);
        }
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