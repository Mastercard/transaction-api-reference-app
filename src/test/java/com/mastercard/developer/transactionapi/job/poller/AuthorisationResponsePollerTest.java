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
class AuthorisationResponsePollerTest {

    @Mock
    private TransactionApiClient mockTransactionApiClient;

    @InjectMocks
    private AuthorisationResponsePoller authorisationResponsePoller;

    @Mock
    private BatchResponse<AuthorisationResponseAuthorisationResponseV02> testBatchResponse;

    @Test
    void whenGetResponses_verifyGetAuthorisationResponses() {
        // setup
        when(mockTransactionApiClient.getAuthorisationResponses()).thenReturn(testBatchResponse);

        // call
        BatchResponse<AuthorisationResponseAuthorisationResponseV02> actual = authorisationResponsePoller.getResponses();

        // verify
        assertThat(actual).isSameAs(testBatchResponse);
    }
}