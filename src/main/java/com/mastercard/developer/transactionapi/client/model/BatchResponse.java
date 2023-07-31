package com.mastercard.developer.transactionapi.client.model;

import lombok.Builder;
import lombok.Data;

import java.time.Duration;
import java.util.List;

/**
 * Common holder class for all batch responses.
 * @param <P>  response item payload type
 */
@Data
@Builder
public class BatchResponse<P> {

    private List<ResponseItem<P>> items;

    private boolean hasMore;
    private Duration retryAfter;
}
