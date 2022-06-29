package com.mastercard.developer.service.impl;

import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.service.TransactionApiService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.api.TransactionApiApi;
import org.openapitools.client.model.InitiationAuthorisationInitiationV02;
import org.openapitools.client.model.ResponseAuthorisationResponseV02;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionApiServiceImpl implements TransactionApiService {

    private TransactionApiApi transactionApiApi;

    @Autowired
    public TransactionApiServiceImpl(ApiClient apiClient) {
        log.info("-->> INITIALIZING TRANSACTION API");
        transactionApiApi = new TransactionApiApi(apiClient);
    }

    @Override
    public ResponseAuthorisationResponseV02 initiateAuthorisation(InitiationAuthorisationInitiationV02 authorisationRequest) throws ServiceException {
        try {
            log.info("<-- CALLING TRANSACTION API ENDPOINT -->");
            ResponseAuthorisationResponseV02 transactionApiResponse = transactionApiApi.transactionApiProcessAuthorisationRequest(authorisationRequest);
            Assertions.assertNotNull(transactionApiResponse, "Missing object 'transactionApiResponse' when calling servicesPost(Async)");
            log.info("<-- TRANSACTION API RESPONDED SUCCESSFULLY -->");
            return transactionApiResponse;
        } catch (ApiException e) {
            log.error("<<-- TRANSACTION API FAILED -->>");
            throw new ServiceException(e);
        }
    }

}
