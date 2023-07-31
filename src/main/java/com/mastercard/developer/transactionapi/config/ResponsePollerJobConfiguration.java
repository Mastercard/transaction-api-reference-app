package com.mastercard.developer.transactionapi.config;

import com.mastercard.developer.transactionapi.enums.FlowType;
import com.mastercard.developer.transactionapi.job.ResponsePoller;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.List;

/**
 * Configures Response Poller jobs.
 */
@Configuration
public class ResponsePollerJobConfiguration {

    /**
     * Task executor for Response Poller jobs.
     */
    @Bean
    public ThreadPoolTaskExecutor pollerJobsExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(FlowType.values().length);
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(5);
        return taskExecutor;
    }

    /**
     * Starts Response Poller jobs when application starts up.
     */
    @Bean
    public InitializingBean jobStarter(List<ResponsePoller<?>> pollers, ThreadPoolTaskExecutor pollerJobsExecutor) {
        return () -> {
            for (ResponsePoller<?> poller : pollers) {
                pollerJobsExecutor.submit(poller::run);
            }
        };
    }

    /**
     * Stops Response Poller jobs when application shuts down.
     */
    @Bean
    public DisposableBean jobTerminator(List<ResponsePoller<?>> pollers, ThreadPoolTaskExecutor pollerJobsExecutor) { // NOSONAR pollerJobsExecutor is needed here
        // NOTE that the pollerJobsExecutor argument is needed here to express spring dependency,
        //      so that this bean gets terminated (and stops pollers) before the pollerJobsExecutor thread pool
        return () -> {
            for (ResponsePoller<?> poller : pollers) {
                poller.stop();
            }
        };
    }
}
