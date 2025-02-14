package com.mastercard.developer.transactionapi.job.poller;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.model.FinancialResponseFinancialResponseV02;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FinancialAdviceResponsePollerTest {
    @Mock
    private TransactionApiClient mockTransactionApiClient;

    @InjectMocks
    private FinancialAdviceResponsePoller financialAdviceResponsePoller;

    @Mock
    private BatchResponse<FinancialResponseFinancialResponseV02> testBatchResponse;

    @Test
    void whenGetResponses_verifyGetFinancialAdviceResponses() {
        // setup
        when(mockTransactionApiClient.getFinancialAdviceResponses()).thenReturn(testBatchResponse);

        // call
        BatchResponse<FinancialResponseFinancialResponseV02> actual = financialAdviceResponsePoller.getResponses();

        // verify
        assertThat(actual).isSameAs(testBatchResponse);
    }

}