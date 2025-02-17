package com.mastercard.developer.transactionapi.job.processor;

import com.mastercard.developer.transactionapi.client.model.ResponseStatus;
import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.enums.FlowType;
import com.mastercard.developer.transactionapi.utils.ClientUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResponseProcessor{

    private final TransactionApiProperties transactionApiProperties;

    public <P> void processResponseStatus(ResponseStatus<P> item, FlowType flowType) {

        if (item.getPayload() != null) {
            String payloadString = ClientUtils.convertPayloadForLogging(item.getPayloadAsJson(), transactionApiProperties.isPayloadLoggingEnabled());

            if (item.getHttpStatus() == HttpStatus.SC_OK) {
                log.info("Received successful {} response with correlationId={}, customerContextKey={}, httpStatus={}: {}",
                        flowType, item.getCorrelationId(), item.getCustomerContextKey(), item.getHttpStatus(), payloadString);
            } else {
                log.error("Received unsuccessful {} response with correlationId={}, customerContextKey={}, httpStatus={}, errors={}: {}",
                        flowType, item.getCorrelationId(), item.getCustomerContextKey(), item.getHttpStatus(), item.getErrors(), payloadString);
            }
        } else {
            log.error("Received failed {} response with correlationId={}, customerContextKey={}, httpStatus={}, errors={}",
                    flowType, item.getCorrelationId(), item.getCustomerContextKey(), item.getHttpStatus(), item.getErrors());
        }
    }
}
