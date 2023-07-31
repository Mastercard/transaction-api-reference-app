package com.mastercard.developer.transactionapi.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RunTimeInterruptedExceptionTest {

    @Test
    void runTimeException(){
        RuntimeInterruptedException runtimeInterruptedException = new RuntimeInterruptedException();

        assertThat(runtimeInterruptedException).hasMessage("Thread interrupted");
    }
}
