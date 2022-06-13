

# InquiryresponseTransactionIdentification11

Identification of the transaction for network management.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**lclDtTm** | **String** |  |  [optional]
**trnsmssnDtTm** | **String** |  |  [optional]
**sysTracAudtNb** | **String** | Number assigned by a transaction originator to assist in identifying a transaction uniquely. The trace number remains unchanged for all messages within a two-message exchange (for example, request/repeat and response).  ISO 8583 bit 11. |  [optional]
**rtrvlRefNb** | **String** |  |  [optional]
**lifeCyclSpprtInd** | **String** | Indicate the point in the transaction lifecycle at which the lifecycle identifier was assigned. |  [optional]
**lifeCyclTracIdData** | [**MsginquiryresponseTransactionLifeCycleIdentification1**](MsginquiryresponseTransactionLifeCycleIdentification1.md) |  |  [optional]
**lifeCyclTracIdMssng** | **String** | Reason for not providing a lifecycle trace identification information. |  [optional]
**acqrrRefData** | **String** |  |  [optional]
**acqrrRefNb** | **String** |  |  [optional]
**cardIssrRefData** | **String** | Data supplied by a card issuer in an authorisation response, financial response message or in a chargeback transaction that the acquirer may be required to provide in subsequent transactions. ISO 8583:1993 and ISO 8583:2003 bit 95. |  [optional]



