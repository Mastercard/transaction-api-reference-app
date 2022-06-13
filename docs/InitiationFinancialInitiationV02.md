

# InitiationFinancialInitiationV02

The FinancialInitiation message is sent by an acquirer or an agent to an issuer to request approval of a card transaction or to inform about the completion of an authorisation. It allows the approved transaction amount to be billed or posted on the cardholder's account. It can also be sent by an issuer to an acquirer or agent to advise that an authorisation has been successfully completed for the final amount and requests the clearing of the transaction.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**hdr** | [**MsgfinancialinitiationHeader42**](MsgfinancialinitiationHeader42.md) |  |  [optional]
**body** | [**InitiationFinancialInitiation1**](InitiationFinancialInitiation1.md) |  |  [optional]



