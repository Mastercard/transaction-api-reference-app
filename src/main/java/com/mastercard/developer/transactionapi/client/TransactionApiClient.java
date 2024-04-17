package com.mastercard.developer.transactionapi.client;

import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import com.mastercard.developer.transactionapi.exception.TransactionApiException;
import org.openapitools.client.model.AuthorisationInitiationAuthorisationInitiationV02;
import org.openapitools.client.model.FinancialInitiationFinancialInitiationV02;
import org.openapitools.client.model.InquiryInitiationInquiryInitiationV01;
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
     * @param authorisationRequest  authorisation request
     * @return Correlation ID of the accepted request
     */
    String submitAuthorisationRequest(AuthorisationInitiationAuthorisationInitiationV02 authorisationRequest) throws TransactionApiException;

    /**
     * Submits the reversal request for processing.
     * URL: /cain-reversal-requests
     * Method: POST
     *
     * @param reversalRequest  reversal request
     * @return Correlation ID of the accepted request
     */
    String submitReversalRequest(ReversalInitiationReversalInitiationV02 reversalRequest) throws TransactionApiException;

    /**
     * Submits the inquiry request for processing.
     * URL: /cain-inquiry-requests
     * Method: POST
     *
     * @param inquiryRequest inquiry request
     * @return Correlation ID of the accepted request
     */
    String submitInquiryRequest(InquiryInitiationInquiryInitiationV01 inquiryRequest) throws TransactionApiException;

    /**
     * Submits the financial advice for processing.
     * URL: /cain-financial-advices
     * Method: POST
     *
     * @param financialAdviceRequest  financial advice
     * @return Correlation ID of the accepted request
     */
    String submitFinancialAdviceRequest(FinancialInitiationFinancialInitiationV02 financialAdviceRequest) throws TransactionApiException;

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

}