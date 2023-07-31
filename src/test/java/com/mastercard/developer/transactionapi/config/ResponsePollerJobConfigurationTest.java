package com.mastercard.developer.transactionapi.config;

import com.mastercard.developer.transactionapi.enums.FlowType;
import com.mastercard.developer.transactionapi.job.ResponsePoller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.client.model.ResponseAuthorisationResponseV02;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ResponsePollerJobConfigurationTest {

    private final ResponsePollerJobConfiguration responsePollerJobConfiguration = new ResponsePollerJobConfiguration();

    @Mock
    private ResponsePoller<ResponseAuthorisationResponseV02> mockResponsePoller;

    @Mock
    private ThreadPoolTaskExecutor mockThreadPoolTaskExecutor;

    @Captor
    private ArgumentCaptor<Runnable> runnableArgumentCaptor;

    @Test
    void whenPollerJobsExecutor_thenVerifyTaskExecutorProperties() {
        // call
        ThreadPoolTaskExecutor taskExecutor = responsePollerJobConfiguration.pollerJobsExecutor();

        // verify
        assertThat(taskExecutor).isInstanceOf(ThreadPoolTaskExecutor.class);
        assertThat(taskExecutor.getCorePoolSize()).isEqualTo(FlowType.values().length);
    }

    @Test
    void whenJobStarter() throws Exception {
        // setup
        List<ResponsePoller<?>> pollers = Collections.singletonList(mockResponsePoller);

        // call
        InitializingBean initializingBean = responsePollerJobConfiguration.jobStarter(pollers, mockThreadPoolTaskExecutor);
        initializingBean.afterPropertiesSet();

        // verify
        verify(mockThreadPoolTaskExecutor).submit(runnableArgumentCaptor.capture());

        Runnable runnable = runnableArgumentCaptor.getValue();
        runnable.run();

        verify(mockResponsePoller).run();
    }

    @Test
    void whenJobTerminator() throws Exception {
        // setup
        List<ResponsePoller<?>> pollers = Collections.singletonList(mockResponsePoller);

        // call
        DisposableBean disposableBean = responsePollerJobConfiguration.jobTerminator(pollers, mockThreadPoolTaskExecutor);
        disposableBean.destroy();

        // verify
        verify(mockResponsePoller).stop();
    }
}
