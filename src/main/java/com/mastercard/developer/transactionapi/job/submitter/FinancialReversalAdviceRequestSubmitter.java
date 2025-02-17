package com.mastercard.developer.transactionapi.job.submitter;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.FinancialReversalAdviceResponseStatus;
import com.mastercard.developer.transactionapi.client.model.ResponseStatus;
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
import org.openapitools.client.model.FinancialReversalAdviceResponseV02Status;
import org.openapitools.client.model.ReversalFinancialAdviceInitiationReversalInitiationV02;
import org.openapitools.client.model.ReversalFinancialAdviceResponseReversalResponseV02;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mastercard.developer.transactionapi.client.TransactionApiConstants.CORRELATION_ID_HEADER;
import static com.mastercard.developer.transactionapi.utils.ClientUtils.getValueFromHeaders;
import static com.mastercard.developer.transactionapi.utils.PayloadUtils.getErrorPayload;
import static com.mastercard.developer.transactionapi.utils.PayloadUtils.jsonToErrorList;

/**
 * Emulates a stream of financial reversal advice for the Transaction API.
 */
@Slf4j
@Service
public class FinancialReversalAdviceRequestSubmitter extends RequestSubmitter<ReversalFinancialAdviceInitiationReversalInitiationV02,ReversalFinancialAdviceResponseReversalResponseV02> {

    public FinancialReversalAdviceRequestSubmitter(TransactionApiClient transactionApiClient,
                                                   RequestContextManager requestContextManager,
                                                   RequestExampleGenerator requestExampleGenerator,
                                                   TransactionApiProperties transactionApiProperties,
                                                   ResponseProcessor responseProcessor) {
        super(FlowType.FINANCIAL_REVERSAL_ADVICE, transactionApiClient, requestContextManager, requestExampleGenerator, transactionApiProperties, responseProcessor);
    }

    /**
     * Submits a financial reversal advice.
     */
    protected ApiResponse<ReversalFinancialAdviceResponseReversalResponseV02> submitRequest(String customerContextKey, ReversalFinancialAdviceInitiationReversalInitiationV02 request) throws TransactionApiException {
        return getTransactionApiClient().submitFinancialReversalAdvice(customerContextKey, request);
    }

    /**
     * Converts OpenAPI ApiResponse to ResponseStatus
     */
    protected ResponseStatus<ReversalFinancialAdviceResponseReversalResponseV02> toResponseStatus(String customerContextKey, ApiResponse<ReversalFinancialAdviceResponseReversalResponseV02> response){
        return new FinancialReversalAdviceResponseStatus(new FinancialReversalAdviceResponseV02Status()
                .httpStatus(response.getStatusCode())
                .payload(response.getData())
                .customerContextKey(customerContextKey)
                .correlationId(getValueFromHeaders(CORRELATION_ID_HEADER,response.getHeaders())));
    }

    /**
     * Converts OpenAPI ApiException to ResponseStatus
     */
    protected ResponseStatus<ReversalFinancialAdviceResponseReversalResponseV02> toResponseStatus(String customerContextKey, ApiException apiException){
        List<Error> errorList = jsonToErrorList(apiException.getResponseBody());

        ReversalFinancialAdviceResponseReversalResponseV02 errorPayload = getErrorPayload(errorList, ErrorPayload::getReversalFinancialAdviceResponse);

        return new FinancialReversalAdviceResponseStatus(new FinancialReversalAdviceResponseV02Status()
                .httpStatus(apiException.getCode())
                .errors(errorList)
                .payload(errorPayload)
                .customerContextKey(customerContextKey)
                .correlationId(getValueFromHeaders(CORRELATION_ID_HEADER,apiException.getResponseHeaders())));
    }

    /**
     * Generate request payload for transaction
     */
    protected ReversalFinancialAdviceInitiationReversalInitiationV02 generateRequest(){
        return getRequestExampleGenerator().buildFinancialReversalAdvice();
    }


    /**
     * Returns JSON String for request payload
     */
    protected String requestToJson(ReversalFinancialAdviceInitiationReversalInitiationV02 request){
        return request.toJson();
    }
}
