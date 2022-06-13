

# MsgauthorisationinitiationCardData1

Non-protected sensitive data associated with the card or payment token performing the transaction.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**pAN** | **String** |  |  [optional]
**cardSeqNb** | **String** |  |  [optional]
**xpryDt** | **String** | Expiry date of the card or payment token.  ISO 8583 bit 14. |  [optional]
**trck1** | **String** |  |  [optional]
**trck2** | [**MsgauthorisationinitiationTrack2Data1Choice**](MsgauthorisationinitiationTrack2Data1Choice.md) |  |  [optional]
**pmtAcctRef** | **String** |  |  [optional]
**cardPdctTp** | **String** | Type of card product. |  [optional]
**addtlCardData** | **String** | Additional card issuer specific data. |  [optional]



