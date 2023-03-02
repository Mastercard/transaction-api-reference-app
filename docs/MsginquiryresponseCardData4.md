

# MsginquiryresponseCardData4

Non-protected sensitive data associated with the card or payment token performing the transaction.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**pan** | **String** | The unique sequence of numbers assigned by the Issuer to the Cardholder account that identifies the Issuer and type of Card, or a surrogate of the PAN such as a payment token. |  [optional] |
|**cardSeqNb** | **String** | The number that distinguishes the Card from another with the same PAN. |  [optional] |
|**pmtAcctRef** | **String** | The identifier assigned to the PAN and used to link payment tokens associated with that PAN. |  [optional] |
|**panAcctRg** | **String** | The leading digits of the PAN that identify the Issuer&#39;s Card portfolio. |  [optional] |
|**panFourLastDgts** | **String** | The last four digits of the PAN. |  [optional] |
|**cardCtryCd** | **String** | The ISO 3166-1 numeric country code of the Card&#39;s Issuer. |  [optional] |
|**cardPdctTp** | **String** | The code identifying the product associated with the Card. |  [optional] |



