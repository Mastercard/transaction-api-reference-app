

# InquiryresponseTransactionIdentification11

Identification of the transaction for network management.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**lclDtTm** | **String** | Local date and time the transaction takes place at the card acceptor location.  ISO 8583:87 bit 12 and 13, ISO 8583:93/2003 bit 12 |  [optional] |
|**trnsmssnDtTm** | **String** | Date and time expressed in UTC of the message as sent by the initiator. ISO 8583 bit 7 |  [optional] |
|**sysTracAudtNb** | **String** | Number assigned by a transaction originator to assist in identifying a transaction uniquely. The trace number remains unchanged for all messages within a two-message exchange (for example, request/repeat and response).  ISO 8583 bit 11. |  [optional] |
|**rtrvlRefNb** | **String** | Reference supplied by the system retaining the original source information and used to assist in locating that information or a copy thereof. ISO 8583 bit 37 |  [optional] |
|**lifeCyclSpprtInd** | **String** | Indicate the point in the transaction lifecycle at which the lifecycle identifier was assigned. |  [optional] |
|**lifeCyclTracIdData** | [**MsginquiryresponseTransactionLifeCycleIdentification1**](MsginquiryresponseTransactionLifeCycleIdentification1.md) |  |  [optional] |
|**acqrrRefData** | **String** | Data supplied by an acquirer in an authorisation or financial request, advice or notification that may be required to be provided in a subsequent transaction.  ISO 8583:93 bit 31 |  [optional] |
|**cardIssrRefData** | **String** | Data supplied by a card issuer in an authorisation response, financial response message or in a chargeback transaction that the acquirer may be required to provide in subsequent transactions. ISO 8583:1993 and ISO 8583:2003 bit 95. |  [optional] |



