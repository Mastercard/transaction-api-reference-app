

# MsgreversalresponseFeeAmount2

Amount, currency, exchange rate and quotation date, sign and label.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**amt** | **String** | The amount of the Additional Fee. |  [optional] |
|**ccy** | **String** | The ISO 4217 numeric code for the currency of the Additional Fee Amount. |  [optional] |
|**xchgRate** | **String** | Exchange rate of the currency code associated with the amount.  ISO 8583 bit 9 (for use with reconciliation/settlement amount) ISO 8583 bit 10 (for use with cardholder billing amount) |  [optional] |
|**qtnDt** | **String** | The date of the quote for the Additional Fee Exchange Rate. |  [optional] |
|**sgn** | **Boolean** | Indicates whether the amount value is positive or negative.  Negative: the receiver of the message owes the fee to the sender. Positive: the sender of the message owes the fee to the receiver. |  [optional] |



