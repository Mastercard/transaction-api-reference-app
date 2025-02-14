package com.mastercard.developer.transactionapi.job.poller;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.BatchResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.model.InquiryResponseInquiryResponseV01;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InquiryResponsePollerTest {
    @Mock
    private TransactionApiClient mockTransactionApiClient;

    @InjectMocks
    private InquiryResponsePoller inquiryResponsePoller;

    @Mock
    private BatchResponse<InquiryResponseInquiryResponseV01> testBatchResponse;

    @Test
    void whenGetResponses_verifyGetInquiryResponses() {
        // setup
        when(mockTransactionApiClient.getInquiryResponses()).thenReturn(testBatchResponse);

        // call
        BatchResponse<InquiryResponseInquiryResponseV01> actual = inquiryResponsePoller.getResponses();

        // verify
        assertThat(actual).isSameAs(testBatchResponse);
    }
}