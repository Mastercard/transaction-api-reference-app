package com.mastercard.developer.transactionapi.job.submitter;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.InquiryResponseStatus;
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
import org.openapitools.client.model.InquiryInitiationInquiryInitiationV01;
import org.openapitools.client.model.InquiryResponseInquiryResponseV01;
import org.openapitools.client.model.InquiryResponseV01Status;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mastercard.developer.transactionapi.client.TransactionApiConstants.CORRELATION_ID_HEADER;
import static com.mastercard.developer.transactionapi.utils.ClientUtils.getValueFromHeaders;
import static com.mastercard.developer.transactionapi.utils.PayloadUtils.getErrorPayload;
import static com.mastercard.developer.transactionapi.utils.PayloadUtils.jsonToErrorList;

/**
 * Emulates a stream of inquiry requests for the Transaction API.
 */
@Slf4j
@Service
public class InquiryRequestSubmitter extends RequestSubmitter<InquiryInitiationInquiryInitiationV01,InquiryResponseInquiryResponseV01> {

    public InquiryRequestSubmitter(TransactionApiClient transactionApiClient,
                                   RequestContextManager requestContextManager,
                                   RequestExampleGenerator requestExampleGenerator,
                                   TransactionApiProperties transactionApiProperties,
                                   ResponseProcessor responseProcessor) {
        super(FlowType.INQUIRY, transactionApiClient, requestContextManager, requestExampleGenerator, transactionApiProperties, responseProcessor);
    }

    /**
     * Submits an inquiry request.
     */
    protected ApiResponse<InquiryResponseInquiryResponseV01> submitRequest(String customerContextKey, InquiryInitiationInquiryInitiationV01 request) throws TransactionApiException {
        return getTransactionApiClient().submitInquiryRequest(customerContextKey, request);
    }

    /**
     * Converts OpenAPI ApiResponse to ResponseStatus
     */
    protected ResponseStatus<InquiryResponseInquiryResponseV01> toResponseStatus(String customerContextKey, ApiResponse<InquiryResponseInquiryResponseV01> response){
        return new InquiryResponseStatus(new InquiryResponseV01Status()
                .httpStatus(response.getStatusCode())
                .payload(response.getData())
                .customerContextKey(customerContextKey)
                .correlationId(getValueFromHeaders(CORRELATION_ID_HEADER,response.getHeaders())));
    }

    /**
     * Converts OpenAPI ApiException to ResponseStatus
     */
    protected ResponseStatus<InquiryResponseInquiryResponseV01> toResponseStatus(String customerContextKey, ApiException apiException){
        List<Error> errorList = jsonToErrorList(apiException.getResponseBody());

        InquiryResponseInquiryResponseV01 errorPayload = getErrorPayload(errorList, ErrorPayload::getInquiryResponse);

        return new InquiryResponseStatus(new InquiryResponseV01Status()
                .httpStatus(apiException.getCode())
                .errors(errorList)
                .payload(errorPayload)
                .customerContextKey(customerContextKey)
                .correlationId(getValueFromHeaders(CORRELATION_ID_HEADER,apiException.getResponseHeaders())));
    }

    /**
     * Generate request payload for transaction
     */
    protected InquiryInitiationInquiryInitiationV01 generateRequest(){
        return getRequestExampleGenerator().buildInquiryRequest();
    }


    /**
     * Returns JSON String for request payload
     */
    protected String requestToJson(InquiryInitiationInquiryInitiationV01 request){
        return request.toJson();
    }
}
