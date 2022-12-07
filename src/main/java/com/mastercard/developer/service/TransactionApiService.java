package com.mastercard.developer.service;

import com.mastercard.developer.exception.ServiceException;
import org.openapitools.client.model.*;

public interface TransactionApiService {
    /**
     * initiateAuthorisation processes the authorisation request and returns the authorisation response
     * URL: /cain-authorisation-requests
     * Method: POST
     * Success Response: 200
     * Error Response: 4XX or 5XX
     *
     * @param authorisationRequest AuthorisationInitiationV02
     * @return An instance of AuthorisationResponseV02
     */
    ResponseAuthorisationResponseV02 initiateAuthorisation(InitiationAuthorisationInitiationV02 authorisationRequest) throws ServiceException;

    /**
     * initiateReversal processes the reversal request and returns the reversal response
     * URL: /cain-reversal-requests
     * Method: POST
     * Success Response: 200
     * Error Response: 4XX or 5XX
     *
     * @param reversalRequest ReversalInitiationV02
     * @return An instance of ReversalResponseV02
     */
    ResponseReversalResponseV02 initiateReversal(InitiationReversalInitiationV02 reversalRequest) throws ServiceException;

    /**
     * initiateInquiry processes the inquiry request and returns the inquiry response
     * URL: /cain-inquiry-requests
     * Method: POST
     * Success Response: 200
     * Error Response: 4XX or 5XX
     *
     * @param inquiryRequest InitiationInquiryInitiationV01
     * @return An instance of ResponseInquiryResponseV01
     */
    ResponseInquiryResponseV01 initiateInquiry(InitiationInquiryInitiationV01 inquiryRequest);
}