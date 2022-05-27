

# MsgreversalresponseOriginalDataElements1

Data elements contained in the original message. ISO 8583:1987 bit 90 and ISO 8583 1993/2003 bit 56.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**msgClss** | [**MsgreversalresponseMessageClass1Code**](MsgreversalresponseMessageClass1Code.md) |  |  [optional]
**acqrrId** | **String** |  |  [optional]
**sndrId** | **String** | Code identifying the sender of the original message. ISO 8583 bit 33. |  [optional]
**lclDtTm** | **String** | Local date and time the transaction takes place at the acceptor location. ISO 8583 bit 12. |  [optional]
**trnsmssnDtTm** | **String** |  |  [optional]
**sysTracAudtNb** | **String** | Transaction reference of the original message. ISO 8583 bit 11. |  [optional]
**rtrvlRefNb** | **String** |  |  [optional]
**lifeCyclSpprt** | [**MsgreversalresponseLifeCycleSupport1Code**](MsgreversalresponseLifeCycleSupport1Code.md) |  |  [optional]
**lifeCyclTracIdData** | [**MsgreversalresponseTransactionLifeCycleIdentification1**](MsgreversalresponseTransactionLifeCycleIdentification1.md) |  |  [optional]



