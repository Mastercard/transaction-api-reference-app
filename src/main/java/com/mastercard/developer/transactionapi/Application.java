package com.mastercard.developer.transactionapi;

import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.job.RequestSubmitter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {

    private static final int REQUEST_COUNT = 5;

    private final List<RequestSubmitter> requestSubmitters;
    private final RequestContextManager requestContextManager;

    public static void main(String... args) { // NOSONAR not a threat
        ConfigurableApplicationContext appContext = SpringApplication.run(Application.class, args);
        appContext.close();
    }

    @Override
    public void run(String... args) {
        // For the purposes of this demo, submit several requests of each type
        for (RequestSubmitter requestSubmitter : requestSubmitters) {
            requestSubmitter.submitRequests(REQUEST_COUNT);
        }
        // and wait till all responses are received
        // Note that responses are received using Response Pollers jobs, see ResponsePollerJobConfiguration
        requestContextManager.waitUntilAllCompleted();
    }
}