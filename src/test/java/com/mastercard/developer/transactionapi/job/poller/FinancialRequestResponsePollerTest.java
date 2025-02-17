package com.mastercard.developer.transactionapi.job.poller;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.model.FinancialRequestResponseFinancialResponseV02;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FinancialRequestResponsePollerTest {

    @Mock
    private TransactionApiClient mockTransactionApiClient;

    @InjectMocks
    private FinancialRequestResponsePoller financialRequestResponsePoller;

    @Mock
    private BatchResponse<FinancialRequestResponseFinancialResponseV02> testBatchResponse;

    @Test
    void whenGetResponses_verifyGetFinancialAdviceResponses() {
        // setup
        when(mockTransactionApiClient.getFinancialRequestResponses()).thenReturn(testBatchResponse);

        // call
        BatchResponse<FinancialRequestResponseFinancialResponseV02> actual = financialRequestResponsePoller.getResponses();

        // verify
        assertThat(actual).isSameAs(testBatchResponse);
    }

}