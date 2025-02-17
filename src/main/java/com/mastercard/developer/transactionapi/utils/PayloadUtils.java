package com.mastercard.developer.transactionapi.utils;

import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.ErrorPayload;
import org.openapitools.client.model.ErrorWrapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PayloadUtils {

    public static List<Error> jsonToErrorList(String errorResponseBody) {
        try{
            ErrorWrapper errorWrapper = new Gson().fromJson(errorResponseBody, ErrorWrapper.class);
            return errorWrapper.getErrors().getError();
        }catch (Exception e){
            log.warn("Unexpected error body: {}",errorResponseBody);
        }

        return Collections.emptyList();
    }

    public static <P> P getErrorPayload(List<Error> errorList, Function<ErrorPayload,P> extractor) {
        return Optional.ofNullable(errorList)
                .filter(l -> !l.isEmpty())
                .map(l -> l.get(0))
                .map(Error::getPayload)
                .map(extractor)
                .orElse(null);
    }
}
