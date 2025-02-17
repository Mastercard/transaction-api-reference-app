package com.mastercard.developer.transactionapi.job.submitter;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.FinancialRequestResponseStatus;
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
import org.openapitools.client.model.FinancialRequestInitiationFinancialInitiationV02;
import org.openapitools.client.model.FinancialRequestResponseFinancialResponseV02;
import org.openapitools.client.model.FinancialRequestResponseV02Status;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mastercard.developer.transactionapi.client.TransactionApiConstants.CORRELATION_ID_HEADER;
import static com.mastercard.developer.transactionapi.utils.ClientUtils.getValueFromHeaders;
import static com.mastercard.developer.transactionapi.utils.PayloadUtils.getErrorPayload;
import static com.mastercard.developer.transactionapi.utils.PayloadUtils.jsonToErrorList;

/**
 * Emulates a stream of financial requests for the Transaction API.
 */
@Slf4j
@Service
public class FinancialRequestSubmitter extends RequestSubmitter<FinancialRequestInitiationFinancialInitiationV02,FinancialRequestResponseFinancialResponseV02> {

    public FinancialRequestSubmitter(TransactionApiClient transactionApiClient,
                                     RequestContextManager requestContextManager,
                                     RequestExampleGenerator requestExampleGenerator,
                                     TransactionApiProperties transactionApiProperties,
                                     ResponseProcessor responseProcessor) {
        super(FlowType.FINANCIAL_REQUEST, transactionApiClient, requestContextManager, requestExampleGenerator, transactionApiProperties, responseProcessor);
    }

    /**
     * Submits a financial request.
     */
    protected ApiResponse<FinancialRequestResponseFinancialResponseV02> submitRequest(String customerContextKey, FinancialRequestInitiationFinancialInitiationV02 request) throws TransactionApiException {
        return getTransactionApiClient().submitFinancialRequest(customerContextKey, request);
    }

    /**
     * Converts OpenAPI ApiResponse to ResponseStatus
     */
    protected ResponseStatus<FinancialRequestResponseFinancialResponseV02> toResponseStatus(String customerContextKey, ApiResponse<FinancialRequestResponseFinancialResponseV02> response){
        return new FinancialRequestResponseStatus(new FinancialRequestResponseV02Status()
                .httpStatus(response.getStatusCode())
                .payload(response.getData())
                .customerContextKey(customerContextKey)
                .correlationId(getValueFromHeaders(CORRELATION_ID_HEADER,response.getHeaders())));
    }

    /**
     * Converts OpenAPI ApiException to ResponseStatus
     */
    protected ResponseStatus<FinancialRequestResponseFinancialResponseV02> toResponseStatus(String customerContextKey, ApiException apiException){
        List<Error> errorList = jsonToErrorList(apiException.getResponseBody());

        FinancialRequestResponseFinancialResponseV02 errorPayload = getErrorPayload(errorList, ErrorPayload::getFinancialRequestResponse);

        return new FinancialRequestResponseStatus(new FinancialRequestResponseV02Status()
                .httpStatus(apiException.getCode())
                .errors(errorList)
                .payload(errorPayload)
                .customerContextKey(customerContextKey)
                .correlationId(getValueFromHeaders(CORRELATION_ID_HEADER,apiException.getResponseHeaders())));
    }

    /**
     * Generate request payload for transaction
     */
    protected FinancialRequestInitiationFinancialInitiationV02 generateRequest(){
        return getRequestExampleGenerator().buildFinancialRequest();
    }


    /**
     * Returns JSON String for request payload
     */
    protected String requestToJson(FinancialRequestInitiationFinancialInitiationV02 request){
        return request.toJson();
    }
}
