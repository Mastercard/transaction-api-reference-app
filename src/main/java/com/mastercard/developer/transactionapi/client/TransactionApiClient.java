package com.mastercard.developer.transactionapi.client;

import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import com.mastercard.developer.transactionapi.exception.TransactionApiException;
import org.openapitools.client.model.InitiationAuthorisationInitiationV02;
import org.openapitools.client.model.InitiationFinancialInitiationV02;
import org.openapitools.client.model.InitiationInquiryInitiationV01;
import org.openapitools.client.model.InitiationReversalInitiationV02;
import org.openapitools.client.model.ResponseAuthorisationResponseV02;
import org.openapitools.client.model.ResponseFinancialResponseV02;
import org.openapitools.client.model.ResponseInquiryResponseV01;
import org.openapitools.client.model.ResponseReversalResponseV02;

/**
 * REST client for the Transaction API.
 */
public interface TransactionApiClient {

    /**
     * Submits the authorisation request for processing.
     * URL: /cain-authorisation-requests
     * Method: POST
     *
     * @param authorisationRequest  authorisation request
     * @return Correlation ID of the accepted request
     */
    String submitAuthorisationRequest(InitiationAuthorisationInitiationV02 authorisationRequest) throws TransactionApiException;

    /**
     * Submits the reversal request for processing.
     * URL: /cain-reversal-requests
     * Method: POST
     *
     * @param reversalRequest  reversal request
     * @return Correlation ID of the accepted request
     */
    String submitReversalRequest(InitiationReversalInitiationV02 reversalRequest) throws TransactionApiException;

    /**
     * Submits the inquiry request for processing.
     * URL: /cain-inquiry-requests
     * Method: POST
     *
     * @param inquiryRequest inquiry request
     * @return Correlation ID of the accepted request
     */
    String submitInquiryRequest(InitiationInquiryInitiationV01 inquiryRequest) throws TransactionApiException;

    /**
     * Submits the financial advice for processing.
     * URL: /cain-financial-advices
     * Method: POST
     *
     * @param financialAdviceRequest  financial advice
     * @return Correlation ID of the accepted request
     */
    String submitFinancialAdviceRequest(InitiationFinancialInitiationV02 financialAdviceRequest) throws TransactionApiException;

    /**
     * Polls for available authorisation responses
     * URL: /cain-authorisation-requests
     * Method: GET
     *
     * @return response batch
     */
    BatchResponse<ResponseAuthorisationResponseV02> getAuthorisationResponses() throws TransactionApiException;

    /**
     * Polls for available reversal responses
     * URL: /cain-reversal-requests
     * Method: GET
     *
     * @return response batch
     */
    BatchResponse<ResponseReversalResponseV02> getReversalResponses() throws TransactionApiException;

    /**
     * Polls for available inquiry responses
     * URL: /cain-inquiry-requests
     * Method: GET
     *
     * @return response batch
     */
    BatchResponse<ResponseInquiryResponseV01> getInquiryResponses() throws TransactionApiException;

    /**
     * Polls for available financial advice responses
     * URL: /cain-financial-advices
     * Method: GET
     *
     * @return response batch
     */
    BatchResponse<ResponseFinancialResponseV02> getFinancialAdviceResponses() throws TransactionApiException;

}