

# MsginquiryinitiationFeeAmount2

Amount, currency, exchange rate and quotation date, sign and label.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**amt** | **String** | Amount exclusive of currency.  ISO 8583:87 bit 8, 28, 29, 30 &amp; 31 ISO 8583:93/2003 bit 8 &amp; 46 |  [optional] |
|**ccy** | **String** | Currency for the type of amount. |  [optional] |
|**xchgRate** | **String** | Exchange rate of the currency code associated with the amount.  ISO 8583 bit 9 (for use with reconciliation/settlement amount) ISO 8583 bit 10 (for use with cardholder billing amount) |  [optional] |
|**qtnDt** | **String** | Date and time at which the exchange rate has been quoted. |  [optional] |
|**sgn** | **Boolean** | Indicates whether the amount value is positive or negative.  Negative: the receiver of the message owes the fee to the sender. Positive: the sender of the message owes the fee to the receiver. |  [optional] |



