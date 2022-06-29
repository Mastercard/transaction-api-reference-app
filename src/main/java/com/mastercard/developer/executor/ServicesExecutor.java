package com.mastercard.developer.executor;

import com.mastercard.developer.config.MastercardProperties;
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
    private final MastercardProperties mcProperties;

    @Autowired
    public ServicesExecutor(TransactionApiService transactioApiService, MastercardProperties mcProperties) {
        this.transactioApiService = transactioApiService;
        this.mcProperties = mcProperties;
    }

    public void execute() throws ServiceException {

        if(mcProperties.isHealthEnable()){
            String healthCorrelationId = UUID.randomUUID().toString();
            log.info(healthCorrelationId + ": <<<---- TRANSACTION API HEALTH CHECK ---->>>");
            transactioApiService.health(healthCorrelationId);
            log.info(healthCorrelationId + ": <<<---- TRANSACTION API EXECUTION STARTED ---->>>");
        }

        String correlationId = UUID.randomUUID().toString();
        log.info(correlationId + ": <-- SENDING AUTHORISATION REQUEST -->");
        initiateAuthorisation(correlationId);
        log.info(correlationId + ": <-- COMPLETED AUTHORISATION REQUEST -->");

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
