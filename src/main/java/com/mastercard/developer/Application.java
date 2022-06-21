package com.mastercard.developer;

import com.mastercard.developer.executor.ServicesExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@EnableScheduling
@EnableAsync
@SpringBootApplication
public class Application implements CommandLineRunner {

    private ServicesExecutor servicesExecutor;

    @Autowired
    public Application(ServicesExecutor servicesExecutor) {
        this.servicesExecutor = servicesExecutor;
    }

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        execute();
    }

    @Async
    @Scheduled(cron = "${scheduler-cron}")
    public void scheduleExecution() {
        execute();
    }

    private void execute() {
        try {
            servicesExecutor.execute();
        } catch (Exception ex) {
            log.error("<-- APPLICATION ENDED WITH SOME ERROR --> {}", ex.getMessage());
        }
    }

}
