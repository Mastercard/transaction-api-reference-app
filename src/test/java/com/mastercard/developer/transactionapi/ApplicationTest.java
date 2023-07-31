package com.mastercard.developer.transactionapi;

import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.job.RequestSubmitter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ApplicationTest {

    @Mock
    private RequestSubmitter mockRequestSubmitter;
    @Mock
    private RequestContextManager mockRequestContextManager;

    @Test
    void main() {
        // setup
        List<RequestSubmitter> requestSubmitters = Collections.singletonList(mockRequestSubmitter);

        // call
        Application application = new Application(requestSubmitters, mockRequestContextManager);
        application.run();

        // verify
        verify(mockRequestSubmitter).submitRequests(5);
        verify(mockRequestContextManager).waitUntilAllCompleted();
    }
}
