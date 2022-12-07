

# MsginquiryinitiationCardData1

Non-protected sensitive data associated with the card or payment token performing the transaction.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**pan** | **String** | Primary Account Number (PAN) of the card or a surrogate of the PAN such as a payment token. ISO 8583 bit 2 |  [optional] |
|**prtctdPANInd** | **Boolean** | To indicate whether the PAN is using ProtectedData for encryption or not. False: The PAN is used in plain text True: The PAN is encrypted by using ProtectedData Default: False |  [optional] |
|**cardSeqNb** | **String** | Distinguishes between instances of the same payment card. ISO 8583 bit 23 |  [optional] |
|**xpryDt** | **String** | Expiry date of the card or payment token.  ISO 8583 bit 14. |  [optional] |
|**svcCd** | **String** | Service attached to the card as defined in ISO 7813.  ISO 8583 bit 40. |  [optional] |
|**trck1** | **String** | ISO track 1 issued from the magnetic stripe card or from the ICC if the magnetic stripe was not read. The format conforms to ISO 7813, removing beginning and ending sentinels and longitudinal redundancy check characters.   ISO 8583 bit 45 |  [optional] |
|**trck2** | [**MsginquiryinitiationTrack2Data1Choice**](MsginquiryinitiationTrack2Data1Choice.md) |  |  [optional] |
|**pmtAcctRef** | **String** | Unique non-financial reference assigned to a given PAN. May be used to link the transaction activity to that PAN.  ISO 8583:87 bit 56 (TLV tag 01/dataset 71) ISO 8583:93 bit 112 (TLV tag 01/dataset 71) ISO 8583:2003 bit 51 (TLV tag 01/dataset 71) |  [optional] |
|**cardCtryCd** | **String** | Country code assigned to the card by the card issuer. ISO 8583 bit 20 |  [optional] |
|**cardPdctTp** | **String** | Type of card product. |  [optional] |
|**addtlCardData** | **String** | Additional card issuer specific data. |  [optional] |



