package com.mastercard.developer.transactionapi.job;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.enums.FlowType;
import org.openapitools.client.model.AuthorisationResponseAuthorisationResponseV02;
import org.springframework.stereotype.Service;

/**
 * Polls for authorisation responses from the Transaction API.
 */
@Service
public class AuthorisationResponsePoller extends ResponsePoller<AuthorisationResponseAuthorisationResponseV02> {

    public AuthorisationResponsePoller(RequestContextManager requestContextManager, TransactionApiClient transactionApiClient, TransactionApiProperties transactionApiProperties) {
        super(FlowType.AUTHORISATION, requestContextManager, transactionApiClient, transactionApiProperties);
    }

    @Override
    protected BatchResponse<AuthorisationResponseAuthorisationResponseV02> getResponses() {
        return getTransactionApiClient().getAuthorisationResponses();
    }

    @Override
    protected String toJson(AuthorisationResponseAuthorisationResponseV02 payload) {
        return payload.toJson();
    }
}
