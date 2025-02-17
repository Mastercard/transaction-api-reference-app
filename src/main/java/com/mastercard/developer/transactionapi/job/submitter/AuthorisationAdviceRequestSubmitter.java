package com.mastercard.developer.transactionapi.job.submitter;

import com.mastercard.developer.transactionapi.client.TransactionApiClient;
import com.mastercard.developer.transactionapi.client.model.AuthorisationResponseStatus;
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
import org.openapitools.client.model.AuthorisationInitiationAuthorisationInitiationV02;
import org.openapitools.client.model.AuthorisationResponseAuthorisationResponseV02;
import org.openapitools.client.model.AuthorisationResponseV02Status;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.ErrorPayload;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mastercard.developer.transactionapi.client.TransactionApiConstants.CORRELATION_ID_HEADER;
import static com.mastercard.developer.transactionapi.utils.ClientUtils.getValueFromHeaders;
import static com.mastercard.developer.transactionapi.utils.PayloadUtils.getErrorPayload;
import static com.mastercard.developer.transactionapi.utils.PayloadUtils.jsonToErrorList;

/**
 * Emulates a stream of authorisation advice requests for the Transaction API.
 */
@Slf4j
@Service
public class AuthorisationAdviceRequestSubmitter extends RequestSubmitter<AuthorisationInitiationAuthorisationInitiationV02,AuthorisationResponseAuthorisationResponseV02> {

    public AuthorisationAdviceRequestSubmitter(TransactionApiClient transactionApiClient,
                                               RequestContextManager requestContextManager,
                                               RequestExampleGenerator requestExampleGenerator,
                                               TransactionApiProperties transactionApiProperties,
                                               ResponseProcessor responseProcessor) {
        super(FlowType.AUTHORISATION_ADVICE, transactionApiClient, requestContextManager, requestExampleGenerator, transactionApiProperties, responseProcessor);
    }

    /**
     * Submits an authorisation advice request.
     */
    protected ApiResponse<AuthorisationResponseAuthorisationResponseV02> submitRequest(String customerContextKey, AuthorisationInitiationAuthorisationInitiationV02 request) throws TransactionApiException {
        return getTransactionApiClient().submitAuthorisationAdviceRequest(customerContextKey, request);
    }

    /**
     * Converts OpenAPI ApiResponse to ResponseStatus
     */
    protected ResponseStatus<AuthorisationResponseAuthorisationResponseV02> toResponseStatus(String customerContextKey, ApiResponse<AuthorisationResponseAuthorisationResponseV02> response){
        return new AuthorisationResponseStatus(new AuthorisationResponseV02Status()
                .httpStatus(response.getStatusCode())
                .payload(response.getData())
                .customerContextKey(customerContextKey)
                .correlationId(getValueFromHeaders(CORRELATION_ID_HEADER,response.getHeaders())));
    }

    /**
     * Converts OpenAPI ApiException to ResponseStatus
     */
    protected ResponseStatus<AuthorisationResponseAuthorisationResponseV02> toResponseStatus(String customerContextKey, ApiException apiException){
        List<Error> errorList = jsonToErrorList(apiException.getResponseBody());

        AuthorisationResponseAuthorisationResponseV02 errorPayload = getErrorPayload(errorList, ErrorPayload::getAuthorisationResponse);

        return new AuthorisationResponseStatus(new AuthorisationResponseV02Status()
                .httpStatus(apiException.getCode())
                .errors(errorList)
                .payload(errorPayload)
                .customerContextKey(customerContextKey)
                .correlationId(getValueFromHeaders(CORRELATION_ID_HEADER,apiException.getResponseHeaders())));
    }

    /**
     * Generate request payload for transaction
     */
    protected AuthorisationInitiationAuthorisationInitiationV02 generateRequest(){
        return getRequestExampleGenerator().buildAuthorisationRequest();
    }


    /**
     * Returns JSON String for request payload
     */
    protected String requestToJson(AuthorisationInitiationAuthorisationInitiationV02 request){
        return request.toJson();
    }
}
