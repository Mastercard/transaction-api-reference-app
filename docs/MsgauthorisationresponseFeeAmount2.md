

# MsgauthorisationresponseFeeAmount2

Amount, currency, exchange rate and quotation date, sign and label.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**amt** | **Double** |  |  [optional]
**ccy** | [**MsgauthorisationresponseISO3NumericCurrencyCode**](MsgauthorisationresponseISO3NumericCurrencyCode.md) |  |  [optional]
**xchgRate** | **Double** |  |  [optional]
**qtnDt** | **String** | Date and time at which the exchange rate has been quoted. |  [optional]
**sgn** | **Boolean** | Indicates whether the amount value is positive or negative.  Negative: the receiver of the message owes the fee to the sender. Positive: the sender of the message owes the fee to the receiver. |  [optional]



