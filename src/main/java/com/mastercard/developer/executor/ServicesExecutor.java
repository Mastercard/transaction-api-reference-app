package com.mastercard.developer.executor;

import com.mastercard.developer.example.TransactionApiExample;
import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.service.TransactionApiService;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.model.ResponseAuthorisationResponseV02;
import org.openapitools.client.model.ResponseInquiryResponseV01;
import org.openapitools.client.model.ResponseReversalResponseV02;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ServicesExecutor {

    private final TransactionApiService transactionApiService;

    @Autowired
    public ServicesExecutor(TransactionApiService transactionApiService) {
        this.transactionApiService = transactionApiService;
    }

    public void execute() throws ServiceException {
        log.info("<<<---- TRANSACTION API EXECUTION STARTED ---->>>");

        log.info("<-- SENDING AUTHORISATION REQUEST -->");
        initiateAuthorisation();
        log.info("<-- COMPLETED AUTHORISATION REQUEST -->");

        log.info("<-- SENDING REVERSAL REQUEST -->");
        initiateReversal();
        log.info("<-- COMPLETED REVERSAL REQUEST -->");

        log.info("<-- SENDING INQUIRY REQUEST -->");
        initiateInquiry();
        log.info("<-- COMPLETED INQUIRY REQUEST -->");

        log.info("<<<---- TRANSACTION API EXECUTION COMPLETED ---->>>");
    }

    /**
     * USE CASE 1: AUTHORISATION
     * User performs an API request with a combination of fields from different use cases below to execute multiple use cases simultaneously.
     */
    private ResponseAuthorisationResponseV02 initiateAuthorisation() throws ServiceException {
        ResponseAuthorisationResponseV02 response = transactionApiService.initiateAuthorisation(TransactionApiExample.buildAuthorisationRequest());
        log.info(response.toString());
        return response;
    }

    /**
     * USE CASE 2: REVERSAL
     * User performs an API request with a combination of fields from different use cases below to execute multiple use cases simultaneously.
     */
    private ResponseReversalResponseV02 initiateReversal() throws ServiceException {
        ResponseReversalResponseV02 response = transactionApiService.initiateReversal(TransactionApiExample.buildReversalRequest());
        log.info(response.toString());
        return response;
    }

    /**
     * USE CASE 2: REVERSAL
     * User performs an API request with a combination of fields from different use cases below to execute multiple use cases simultaneously.
     */
    private ResponseInquiryResponseV01 initiateInquiry() throws ServiceException {
        ResponseInquiryResponseV01 response = transactionApiService.initiateInquiry(TransactionApiExample.buildInquiryRequest());
        log.info(response.toString());
        return response;
    }

}
