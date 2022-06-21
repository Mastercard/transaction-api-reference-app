

# MsgreversalresponseTransactionIdentification8

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**lclDtTm** | **String** | Local date and time the transaction takes place at the card acceptor location. |  [optional]
**trnsmssnDtTm** | **String** | Date and time expressed in UTC of the message as sent by the initiator. |  [optional]
**sysTracAudtNb** | **String** | Number assigned by a transaction originator to assist in identifying a transaction uniquely. The trace number remains unchanged for all messages within a two-message exchange (for example, request/repeat and response). |  [optional]
**rtrvlRefNb** | **String** | Reference supplied by the system retaining the original source information and used to assist in locating that information or a copy thereof. |  [optional]
**lifeCyclSpprt** | [**MsgreversalresponseLifeCycleSupport1Code**](MsgreversalresponseLifeCycleSupport1Code.md) |  |  [optional]
**lifeCyclTracIdData** | [**MsgreversalresponseTransactionLifeCycleIdentification1**](MsgreversalresponseTransactionLifeCycleIdentification1.md) |  |  [optional]
**cardIssrRefData** | **String** | Data supplied by a card issuer in an authorisation response, financial response message or in a chargeback transaction that the acquirer may be required to provide in subsequent transactions. |  [optional]
**orgnlDataElmts** | [**MsgreversalresponseOriginalDataElements1**](MsgreversalresponseOriginalDataElements1.md) |  |  [optional]


