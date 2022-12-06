

# ReversalresponseCardData5

Non-protected sensitive data associated with the card or payment token performing the transaction.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**pan** | **String** | The unique sequence of numbers assigned by the Issuer to the Cardholder account that identifies the Issuer and type of Card, or a surrogate of the PAN such as a payment token. |  [optional] |
|**prtctdPANInd** | **Boolean** | To indicate whether the PAN is using Protected Data for encryption or not. |  [optional] |
|**cardSeqNb** | **String** | The number that distinguishes the Card from another with the same PAN. |  [optional] |
|**pmtAcctRef** | **String** | The identifier assigned to the PAN and used to link payment tokens associated with that PAN. |  [optional] |
|**panAcctRg** | **String** | The leading digits of the PAN that identify the Issuer&#39;s Card portfolio. |  [optional] |
|**cardPrtflIdr** | **String** | The Identifier assigned to the Issuer&#39;s Card Portfolio. |  [optional] |
|**addtlCardData** | **String** | The code identifying the kind of special processing the Card qualifies for, which is defined at the individual account level and may be associated with a benefit offered by the issuer. |  [optional] |



