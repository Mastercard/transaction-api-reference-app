package com.mastercard.developer.executor;

import com.mastercard.developer.example.TransactionApiExample;
import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.service.TransactionApiService;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.model.ResponseAuthorisationResponseV02;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class ServicesExecutor {

    private final TransactionApiService transactioApiService;

    @Autowired
    public ServicesExecutor(TransactionApiService transactioApiService) {
        this.transactioApiService = transactioApiService;
    }

    public void execute() throws ServiceException {
        log.info("<<<---- TRANSACTION API EXECUTION STARTED ---->>>");

        String correlationId = UUID.randomUUID().toString();
        log.info(correlationId+": <-- SENDING AUTHORISATION REQUEST -->");
        initiateAuthorisation(correlationId);
        log.info(correlationId+": <-- COMPLETED AUTHORISATION REQUEST -->");

        log.info("<<<---- TRANSACTION API EXECUTION COMPLETED ---->>>");
    }

    /**
     * USE CASE 1: AUTHORISATION
     * User performs an API request with a combination of fields from different use cases below to execute multiple use cases simultaneously.
     *
     * @param correlationId
     */
    private ResponseAuthorisationResponseV02 initiateAuthorisation(String correlationId) throws ServiceException {
        ResponseAuthorisationResponseV02 response = transactioApiService.initiateAuthorisation(TransactionApiExample.buildAuthorisationRequest(), correlationId);
        return response;
    }

}
