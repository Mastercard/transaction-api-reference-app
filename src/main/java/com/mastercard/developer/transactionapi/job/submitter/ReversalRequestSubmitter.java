package com.mastercard.developer.transactionapi.job.submitter;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.ResponseStatus;
import com.mastercard.developer.transactionapi.client.model.ReversalResponseStatus;
import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.enums.FlowType;
import com.mastercard.developer.transactionapi.example.RequestExampleGenerator;
import com.mastercard.developer.transactionapi.exception.TransactionApiException;
import com.mastercard.developer.transactionapi.job.processor.ResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.ErrorPayload;
import org.openapitools.client.model.ReversalInitiationReversalInitiationV02;
import org.openapitools.client.model.ReversalResponseReversalResponseV02;
import org.openapitools.client.model.ReversalResponseV02Status;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mastercard.developer.transactionapi.client.TransactionApiConstants.CORRELATION_ID_HEADER;
import static com.mastercard.developer.transactionapi.utils.ClientUtils.getValueFromHeaders;
import static com.mastercard.developer.transactionapi.utils.PayloadUtils.getErrorPayload;
import static com.mastercard.developer.transactionapi.utils.PayloadUtils.jsonToErrorList;

/**
 * Emulates a stream of reversal requests for the Transaction API.
 */
@Slf4j
@Service
public class ReversalRequestSubmitter extends RequestSubmitter<ReversalInitiationReversalInitiationV02, ReversalResponseReversalResponseV02> {

    public ReversalRequestSubmitter(TransactionApiClient transactionApiClient,
                                    RequestContextManager requestContextManager,
                                    RequestExampleGenerator requestExampleGenerator,
                                    TransactionApiProperties transactionApiProperties,
                                    ResponseProcessor responseProcessor) {
        super(FlowType.REVERSAL, transactionApiClient, requestContextManager, requestExampleGenerator, transactionApiProperties, responseProcessor);
    }

    /**
     * Submits a reversal request.
     */
    protected ApiResponse<ReversalResponseReversalResponseV02> submitRequest(String customerContextKey, ReversalInitiationReversalInitiationV02 request) throws TransactionApiException {
        return getTransactionApiClient().submitReversalRequest(customerContextKey, request);
    }

    /**
     * Converts OpenAPI ApiResponse to ResponseStatus
     */
    protected ResponseStatus<ReversalResponseReversalResponseV02> toResponseStatus(String customerContextKey, ApiResponse<ReversalResponseReversalResponseV02> response){
        return new ReversalResponseStatus(new ReversalResponseV02Status()
                .httpStatus(response.getStatusCode())
                .payload(response.getData())
                .customerContextKey(customerContextKey)
                .correlationId(getValueFromHeaders(CORRELATION_ID_HEADER,response.getHeaders())));
    }

    /**
     * Converts OpenAPI ApiException to ResponseStatus
     */
    protected ResponseStatus<ReversalResponseReversalResponseV02> toResponseStatus(String customerContextKey, ApiException apiException){
        List<Error> errorList = jsonToErrorList(apiException.getResponseBody());

        ReversalResponseReversalResponseV02 errorPayload = getErrorPayload(errorList, ErrorPayload::getReversalResponse);

        return new ReversalResponseStatus(new ReversalResponseV02Status()
                .httpStatus(apiException.getCode())
                .errors(errorList)
                .payload(errorPayload)
                .customerContextKey(customerContextKey)
                .correlationId(getValueFromHeaders(CORRELATION_ID_HEADER,apiException.getResponseHeaders())));
    }

    /**
     * Generate request payload for transaction
     */
    protected ReversalInitiationReversalInitiationV02 generateRequest(){
        return getRequestExampleGenerator().buildReversalRequest();
    }


    /**
     * Returns JSON String for request payload
     */
    protected String requestToJson(ReversalInitiationReversalInitiationV02 request){
        return request.toJson();
    }
}
