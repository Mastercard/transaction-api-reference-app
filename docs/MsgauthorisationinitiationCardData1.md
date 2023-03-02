

# MsgauthorisationinitiationCardData1

Non-protected sensitive data associated with the card or payment token performing the transaction.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**pan** | **String** | The unique sequence of numbers assigned by the Issuer to the Cardholder account that identifies the Issuer and type of Card, or a surrogate of the PAN such as a payment token. |  [optional] |
|**cardSeqNb** | **String** | The number that distinguishes the Card from another with the same PAN. |  [optional] |
|**xpryDt** | **String** | The year and month after which the Card is no longer valid. It is designated by the Issuer and is embossed and encoded on the Card. |  [optional] |
|**trck1** | **String** | The information encoded on Track 1 of the Card&#39;s magnetic stripe according to the the ISO 7813 specification. |  [optional] |
|**trck2** | [**MsgauthorisationinitiationTrack2Data1Choice**](MsgauthorisationinitiationTrack2Data1Choice.md) |  |  [optional] |
|**pmtAcctRef** | **String** | The identifier assigned to the PAN and used to link payment tokens associated with that PAN. |  [optional] |
|**cardCtryCd** | **String** | The ISO 3166-1 numeric country code of the Card&#39;s Issuer. |  [optional] |
|**cardPdctTp** | **String** | The code identifying the product associated with the Card. |  [optional] |
|**addtlCardData** | **String** | The code identifying the kind of special processing the Card qualifies for, which is defined at the individual account level and may be associated with a benefit offered by the issuer. |  [optional] |



