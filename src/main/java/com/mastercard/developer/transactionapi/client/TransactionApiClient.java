package com.mastercard.developer.transactionapi.client;

import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import com.mastercard.developer.transactionapi.exception.TransactionApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.model.AuthorisationInitiationAuthorisationInitiationV02;
import org.openapitools.client.model.FinancialInitiationFinancialInitiationV02;
import org.openapitools.client.model.FinancialRequestInitiationFinancialInitiationV02;
import org.openapitools.client.model.FinancialRequestResponseFinancialResponseV02;
import org.openapitools.client.model.InquiryInitiationInquiryInitiationV01;
import org.openapitools.client.model.ReversalFinancialAdviceInitiationReversalInitiationV02;
import org.openapitools.client.model.ReversalFinancialAdviceResponseReversalResponseV02;
import org.openapitools.client.model.ReversalInitiationReversalInitiationV02;
import org.openapitools.client.model.AuthorisationResponseAuthorisationResponseV02;
import org.openapitools.client.model.FinancialResponseFinancialResponseV02;
import org.openapitools.client.model.InquiryResponseInquiryResponseV01;
import org.openapitools.client.model.ReversalResponseReversalResponseV02;

/**
 * REST client for the Transaction API.
 */
public interface TransactionApiClient {

    /**
     * Submits the authorisation request for processing.
     * URL: /cain-authorisation-requests
     * Method: POST
     *
     * @param customerContextKey   transaction identifier
     * @param authorisationRequest authorisation request
     * @return ApiResponse with response payload for request
     */
    ApiResponse<AuthorisationResponseAuthorisationResponseV02> submitAuthorisationRequest(String customerContextKey, AuthorisationInitiationAuthorisationInitiationV02 authorisationRequest) throws TransactionApiException;

    /**
     * Submits the reversal request for processing.
     * URL: /cain-reversal-requests
     * Method: POST
     *
     * @param customerContextKey transaction identifier
     * @param reversalRequest    reversal request
     * @return ApiResponse with response payload for request
     */
    ApiResponse<ReversalResponseReversalResponseV02> submitReversalRequest(String customerContextKey, ReversalInitiationReversalInitiationV02 reversalRequest) throws TransactionApiException;

    /**
     * Submits the inquiry request for processing.
     * URL: /cain-inquiry-requests
     * Method: POST
     *
     * @param customerContextKey transaction identifier
     * @param inquiryRequest     inquiry request
     * @return ApiResponse with response payload for request
     */
    ApiResponse<InquiryResponseInquiryResponseV01> submitInquiryRequest(String customerContextKey, InquiryInitiationInquiryInitiationV01 inquiryRequest) throws TransactionApiException;

    /**
     * Submits the financial advice for processing.
     * URL: /cain-financial-advices
     * Method: POST
     *
     * @param customerContextKey     transaction identifier
     * @param financialAdviceRequest financial advice
     * @return ApiResponse with response payload for request
     */
    ApiResponse<FinancialResponseFinancialResponseV02> submitFinancialAdviceRequest(String customerContextKey, FinancialInitiationFinancialInitiationV02 financialAdviceRequest) throws TransactionApiException;

    /**
     * Submits the financial request for processing.
     * URL: /cain-financial-requests
     * Method: POST
     *
     * @param customerContextKey transaction identifier
     * @param financialRequest   financial request
     * @return ApiResponse with response payload for request
     */
    ApiResponse<FinancialRequestResponseFinancialResponseV02> submitFinancialRequest(String customerContextKey, FinancialRequestInitiationFinancialInitiationV02 financialRequest) throws TransactionApiException;

    /**
     * Submits the financial reversal advice for processing.
     * URL: /cain-financial-reversal-advices
     * Method: POST
     *
     * @param customerContextKey             transaction identifier
     * @param financialReversalAdviceRequest financial reversal advice
     * @return ApiResponse with response payload for request
     */
    ApiResponse<ReversalFinancialAdviceResponseReversalResponseV02> submitFinancialReversalAdvice(String customerContextKey, ReversalFinancialAdviceInitiationReversalInitiationV02 financialReversalAdviceRequest) throws TransactionApiException;

    /**
     * Submits the authorisation advice request for processing.
     * URL: /cain-authorisation-advices
     * Method: POST
     *
     * @param customerContextKey   transaction identifier
     * @param authorisationAdviceRequest authorisation request
     * @return ApiResponse with response payload for request
     */
    ApiResponse<AuthorisationResponseAuthorisationResponseV02> submitAuthorisationAdviceRequest(String customerContextKey, AuthorisationInitiationAuthorisationInitiationV02 authorisationAdviceRequest) throws TransactionApiException;

    /**
     * Polls for available authorisation responses
     * URL: /cain-authorisation-requests
     * Method: GET
     *
     * @return response batch
     */
    BatchResponse<AuthorisationResponseAuthorisationResponseV02> getAuthorisationResponses() throws TransactionApiException;

    /**
     * Polls for available reversal responses
     * URL: /cain-reversal-requests
     * Method: GET
     *
     * @return response batch
     */
    BatchResponse<ReversalResponseReversalResponseV02> getReversalResponses() throws TransactionApiException;

    /**
     * Polls for available inquiry responses
     * URL: /cain-inquiry-requests
     * Method: GET
     *
     * @return response batch
     */
    BatchResponse<InquiryResponseInquiryResponseV01> getInquiryResponses() throws TransactionApiException;

    /**
     * Polls for available financial advice responses
     * URL: /cain-financial-advices
     * Method: GET
     *
     * @return response batch
     */
    BatchResponse<FinancialResponseFinancialResponseV02> getFinancialAdviceResponses() throws TransactionApiException;

    /**
     * Polls for available financial responses
     * URL: /cain-financial-requests
     * Method: GET
     *
     * @return response batch
     */
    BatchResponse<FinancialRequestResponseFinancialResponseV02> getFinancialRequestResponses() throws TransactionApiException;

    /**
     * Polls for available financial reversal advice
     * URL: /cain-financial-reversal-advices
     * Method: GET
     *
     * @return response batch
     */
    BatchResponse<ReversalFinancialAdviceResponseReversalResponseV02> getFinancialReversalAdviceResponses() throws TransactionApiException;

    /**
     * Polls for available authorisation advice responses
     * URL: /cain-authorisation-advices
     * Method: GET
     *
     * @return response batch
     */
    BatchResponse<AuthorisationResponseAuthorisationResponseV02> getAuthorisationAdviceResponses() throws TransactionApiException;
}