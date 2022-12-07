

# InquiryresponseHeader39

Set of characteristics related to the protocol.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**msgFctn** | **InquiryresponseMessageFunction17Code** |  |  [optional] |
|**prtcolVrsn** | **String** | Version of the acquirer to issuer protocol specifications. |  [optional] |
|**xchgId** | **String** | Unique identification of an exchange of messages between two parties. |  [optional] |
|**creDtTm** | **String** | Date and time at which the message was sent. |  [optional] |
|**initgPty** | [**MsginquiryresponseGenericIdentification172**](MsginquiryresponseGenericIdentification172.md) |  |  [optional] |
|**tracData** | [**List&lt;MsginquiryresponseAdditionalData1&gt;**](MsginquiryresponseAdditionalData1.md) | Information sent in the request message to be returned in the response one, for instance to help in the retrieval of the context of the exchange.  ISO 8583:93/2003 bit 59 |  [optional] |



