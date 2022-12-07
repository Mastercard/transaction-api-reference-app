

# MsgauthorisationresponseHeader42

Set of characteristics related to the protocol.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**msgFctn** | **MsgauthorisationresponseMessageFunction16Code** |  |  [optional] |
|**prtcolVrsn** | **String** | The version of the Mastercard Switch Platform Specifications. |  [optional] |
|**xchgId** | **String** | The identifier of a set of messages exchanged between two parties, usually an Acquirer and an Issuer. |  [optional] |
|**creDtTm** | **String** | The date and time the message was created. |  [optional] |
|**initgPty** | [**MsgauthorisationresponseGenericIdentification172**](MsgauthorisationresponseGenericIdentification172.md) |  |  [optional] |
|**tracData** | [**List&lt;MsgauthorisationresponseAdditionalData1&gt;**](MsgauthorisationresponseAdditionalData1.md) | Private-use data defined by the message originator to be returned unaltered in any subsequent message.  The data is sent in a name-value pair: Trace Data Name and Trace Data Value. |  [optional] |



