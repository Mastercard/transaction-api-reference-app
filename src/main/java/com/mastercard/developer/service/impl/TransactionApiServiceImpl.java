package com.mastercard.developer.service.impl;

import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.service.TransactionApiService;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.TransactionApiApi;
import org.openapitools.client.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionApiServiceImpl implements TransactionApiService {

    private static final String MISSING_OBJECT_MESSAGE = "Missing object 'transactionApiResponse' when calling servicesPost(Async)";
    private final TransactionApiApi transactionApiApi;

    @Autowired
    public TransactionApiServiceImpl(ApiClient apiClient) {
        log.info("-->> INITIALIZING TRANSACTION API");
        transactionApiApi = new TransactionApiApi(apiClient);
    }

    @Override
    public ResponseAuthorisationResponseV02 initiateAuthorisation(InitiationAuthorisationInitiationV02 authorisationRequest) throws ServiceException {
        try {
            log.info("<-- CALLING TRANSACTION API AUTHORISATION ENDPOINT -->");
            ResponseAuthorisationResponseV02 transactionApiResponse = transactionApiApi.transactionApiProcessAuthorisationRequest(authorisationRequest);
            if (transactionApiResponse == null) {
                log.error(MISSING_OBJECT_MESSAGE);
            }
            log.info("<-- TRANSACTION API RESPONDED SUCCESSFULLY FOR AUTHORISATION REQUEST-->");
            return transactionApiResponse;
        } catch (ApiException e) {
            log.error("<<-- TRANSACTION API FAILED FOR AUTHORISATION REQUEST-->>");
            log.error(e.getResponseBody());
            throw new ServiceException(e);
        }
    }

    @Override
    public ResponseReversalResponseV02 initiateReversal(InitiationReversalInitiationV02 reversalRequest) throws ServiceException {
        try {
            log.info("CALLING TRANSACTION API REVERSAL ENDPOINT");
            ResponseReversalResponseV02 transactionApiResponse = transactionApiApi.transactionApiProcessReversalRequest(reversalRequest);
            if (transactionApiResponse == null) {
                log.error(MISSING_OBJECT_MESSAGE);
            }
            log.info("TRANSACTION API RESPONDED SUCCESSFULLY FOR REVERSAL REQUEST");
            return transactionApiResponse;
        } catch (ApiException e) {
            log.error("TRANSACTION API FAILED FOR REVERSAL REQUEST");
            log.error(e.getResponseBody());
            throw new ServiceException(e);
        }
    }

    @Override
    public ResponseInquiryResponseV01 initiateInquiry(InitiationInquiryInitiationV01 inquiryRequest) {
        try {
            log.info("CALLING TRANSACTION API INQUIRY ENDPOINT");
            ResponseInquiryResponseV01 transactionApiResponse = transactionApiApi.transactionApiProcessInquriyRequest(inquiryRequest);
            if (transactionApiResponse == null) {
                log.error(MISSING_OBJECT_MESSAGE);
            }
            log.info("TRANSACTION API RESPONDED SUCCESSFULLY FOR INQUIRY REQUEST");
            return transactionApiResponse;
        } catch (ApiException e) {
            log.error("TRANSACTION API FAILED FOR INQUIRY REQUEST");
            log.error(e.getResponseBody());
            throw new ServiceException(e);
        }
    }

}