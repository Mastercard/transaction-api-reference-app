package com.mastercard.developer.transactionapi.job.poller;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.enums.FlowType;
import com.mastercard.developer.transactionapi.job.processor.ResponseProcessor;
import org.openapitools.client.model.AuthorisationResponseAuthorisationResponseV02;
import org.springframework.stereotype.Service;

/**
 * Polls for authorisation advice responses from the Transaction API.
 */
@Service
public class AuthorisationAdviceResponsePoller extends ResponsePoller<AuthorisationResponseAuthorisationResponseV02> {

    public AuthorisationAdviceResponsePoller(RequestContextManager requestContextManager,
                                             TransactionApiClient transactionApiClient,
                                             ResponseProcessor responseProcessor) {
        super(FlowType.AUTHORISATION_ADVICE, requestContextManager, transactionApiClient, responseProcessor);
    }

    @Override
    protected BatchResponse<AuthorisationResponseAuthorisationResponseV02> getResponses() {
        return getTransactionApiClient().getAuthorisationAdviceResponses();
    }
}
