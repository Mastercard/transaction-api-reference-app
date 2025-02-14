package com.mastercard.developer.transactionapi.job.submitter;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.FinancialAdviceResponseStatus;
import com.mastercard.developer.transactionapi.client.model.ResponseStatus;
import com.mastercard.developer.transactionapi.config.TransactionApiProperties;
import com.mastercard.developer.transactionapi.context.RequestContextManager;
import com.mastercard.developer.transactionapi.enums.FlowType;
import com.mastercard.developer.transactionapi.example.RequestExampleGenerator;
import com.mastercard.developer.transactionapi.job.processor.ResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.ErrorPayload;
import org.openapitools.client.model.FinancialAdviceResponseV02Status;
import org.openapitools.client.model.FinancialInitiationFinancialInitiationV02;
import org.openapitools.client.model.FinancialResponseFinancialResponseV02;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mastercard.developer.transactionapi.client.TransactionApiConstants.CORRELATION_ID_HEADER;
import static com.mastercard.developer.transactionapi.utils.ClientUtils.getValueFromHeaders;
import static com.mastercard.developer.transactionapi.utils.PayloadUtils.getErrorPayload;
import static com.mastercard.developer.transactionapi.utils.PayloadUtils.jsonToErrorList;

/**
 * Emulates a stream of financial advice requests for the Transaction API.
 */
@Slf4j
@Service
public class FinancialAdviceRequestSubmitter extends RequestSubmitter<FinancialInitiationFinancialInitiationV02,FinancialResponseFinancialResponseV02> {

    public FinancialAdviceRequestSubmitter(TransactionApiClient transactionApiClient,
                                           RequestContextManager requestContextManager,
                                           RequestExampleGenerator requestExampleGenerator,
                                           TransactionApiProperties transactionApiProperties,
                                           ResponseProcessor responseProcessor) {
        super(FlowType.FINANCIAL_ADVICE, transactionApiClient, requestContextManager, requestExampleGenerator, transactionApiProperties, responseProcessor);
    }

    /**
     * Submits a financial advice.
     */
    protected ApiResponse<FinancialResponseFinancialResponseV02> submitRequest(String customerContextKey, FinancialInitiationFinancialInitiationV02 request) {
        return getTransactionApiClient().submitFinancialAdviceRequest(customerContextKey, request);
    }

    /**
     * Converts OpenAPI ApiResponse to ResponseStatus
     */
    protected ResponseStatus<FinancialResponseFinancialResponseV02> toResponseStatus(String customerContextKey, ApiResponse<FinancialResponseFinancialResponseV02> response){
        return new FinancialAdviceResponseStatus(new FinancialAdviceResponseV02Status()
                .httpStatus(response.getStatusCode())
                .payload(response.getData())
                .customerContextKey(customerContextKey)
                .correlationId(getValueFromHeaders(CORRELATION_ID_HEADER,response.getHeaders())));
    }

    /**
     * Converts OpenAPI ApiException to ResponseStatus
     */
    protected ResponseStatus<FinancialResponseFinancialResponseV02> toResponseStatus(String customerContextKey, ApiException apiException){
        List<Error> errorList = jsonToErrorList(apiException.getResponseBody());

        FinancialResponseFinancialResponseV02 errorPayload = getErrorPayload(errorList, ErrorPayload::getFinancialResponse);

        return new FinancialAdviceResponseStatus(new FinancialAdviceResponseV02Status()
                .httpStatus(apiException.getCode())
                .errors(errorList)
                .payload(errorPayload)
                .customerContextKey(customerContextKey)
                .correlationId(getValueFromHeaders(CORRELATION_ID_HEADER,apiException.getResponseHeaders())));
    }

    /**
     * Generate request payload for transaction
     */
    protected FinancialInitiationFinancialInitiationV02 generateRequest(){
        return getRequestExampleGenerator().buildFinancialAdviceRequest();
    }


    /**
     * Returns JSON String for request payload
     */
    protected String requestToJson(FinancialInitiationFinancialInitiationV02 request){
        return request.toJson();
    }
}
