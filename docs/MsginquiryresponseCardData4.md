

# MsginquiryresponseCardData4

Non-protected sensitive data associated with the card or payment token performing the transaction.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**pan** | **String** | Primary Account Number (PAN) of the card or a surrogate of the PAN such as a payment token. ISO 8583 bit 2 |  [optional] |
|**cardSeqNb** | **String** | Distinguishes between instances of the same payment card. ISO 8583 bit 23 |  [optional] |
|**pmtAcctRef** | **String** | Unique non-financial reference assigned to a given PAN. May be used to link the transaction activity to that PAN.  ISO 8583:87 bit 56 (TLV tag 01/dataset 71) ISO 8583:93 bit 112 (TLV tag 01/dataset 71) ISO 8583:2003 bit 51 (TLV tag 01/dataset 71) |  [optional] |
|**panAcctRg** | **String** | Leading digits of the PAN that identifies the card portfolio (for example, Issuer Identification Number). This data should not to be presented to the card acceptor or its environment. (for example, acquirer should not to send or make available to merchant). |  [optional] |
|**panFourLastDgts** | **String** | Last four digits of the PAN. |  [optional] |
|**cardCtryCd** | **String** | Country code assigned to the card by the card issuer. ISO 8583 bit 20 |  [optional] |
|**cardPdctTp** | **String** | Type of card product. |  [optional] |



