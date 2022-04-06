package com.mastercard.developer.executor;

import com.mastercard.developer.example.TransactionApiExample;
import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.service.TransactionApiService;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.model.AuthorisationResponseV02;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ServicesExecutor {

    private TransactionApiService transactioApiService;

    @Autowired
    public ServicesExecutor(TransactionApiService transactioApiService) {
        this.transactioApiService = transactioApiService;
    }

    public void execute() throws ServiceException {
        log.info("<<<---- TRANSACTION API EXECUTION STARTED ---->>>");

        log.info("<-- SENDING AUTHORISATION REQUEST -->");
        initiateAuthorisation();
        log.info("<-- COMPLETED AUTHORISATION REQUEST -->");

        log.info("<<<---- TRANSACTION API EXECUTION COMPLETED ---->>>");
    }

    /**
     * USE CASE 1: AUTHORISATION
     * User performs an API request with a combination of fields from different use cases below to execute multiple use cases simultaneously.
     *
     * @return An instance of AuthorisationResponseV02
     */
    private AuthorisationResponseV02 initiateAuthorisation() throws ServiceException {
        return transactioApiService.initiateAuthorisation(TransactionApiExample.buildAuthorisationRequest());
    }

}
