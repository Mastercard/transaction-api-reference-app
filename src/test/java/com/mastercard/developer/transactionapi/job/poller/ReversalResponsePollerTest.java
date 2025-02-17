package com.mastercard.developer.transactionapi.job.poller;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.model.ReversalResponseReversalResponseV02;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReversalResponsePollerTest {

    @Mock
    private TransactionApiClient mockTransactionApiClient;

    @InjectMocks
    private ReversalResponsePoller reversalResponsePoller;

    @Mock
    private BatchResponse<ReversalResponseReversalResponseV02> testBatchResponse;

    @Test
    void whenGetResponses_VerifyGetReversalResponses() {
        // setup
        when(mockTransactionApiClient.getReversalResponses()).thenReturn(testBatchResponse);

        // call
        BatchResponse<ReversalResponseReversalResponseV02> actual = reversalResponsePoller.getResponses();

        // verify
        assertThat(actual).isSameAs(testBatchResponse);
    }

}