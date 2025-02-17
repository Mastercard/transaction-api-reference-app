package com.mastercard.developer.transactionapi.job.poller;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.model.AuthorisationResponseAuthorisationResponseV02;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorisationAdviceResponsePollerTest {

    @Mock
    private TransactionApiClient mockTransactionApiClient;

    @InjectMocks
    private AuthorisationAdviceResponsePoller authorisationAdviceResponsePoller;

    @Mock
    private BatchResponse<AuthorisationResponseAuthorisationResponseV02> testBatchResponse;

    @Test
    void whenGetResponses_verifyGetAuthorisationAdviceResponses() {
        // setup
        when(mockTransactionApiClient.getAuthorisationAdviceResponses()).thenReturn(testBatchResponse);

        // call
        BatchResponse<AuthorisationResponseAuthorisationResponseV02> actual = authorisationAdviceResponsePoller.getResponses();

        // verify
        assertThat(actual).isSameAs(testBatchResponse);
    }
}