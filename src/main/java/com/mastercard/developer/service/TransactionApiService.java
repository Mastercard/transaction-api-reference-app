package com.mastercard.developer.service;

import com.mastercard.developer.exception.ServiceException;
import org.openapitools.client.model.InitiationAuthorisationInitiationV02;
import org.openapitools.client.model.ResponseAuthorisationResponseV02;

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
    ResponseAuthorisationResponseV02 initiateAuthorisation(InitiationAuthorisationInitiationV02 authorisationRequest)
            throws ServiceException;
}
