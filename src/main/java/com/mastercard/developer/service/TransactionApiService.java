package com.mastercard.developer.service;

import com.mastercard.developer.exception.ServiceException;
import org.openapitools.client.model.AuthorisationInitiationV02;
import org.openapitools.client.model.AuthorisationResponseV02;

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
    AuthorisationResponseV02 initiateAuthorisation(AuthorisationInitiationV02 authorisationRequest)
            throws ServiceException;
}
