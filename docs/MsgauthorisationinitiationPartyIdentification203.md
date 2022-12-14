

# MsgauthorisationinitiationPartyIdentification203

Identification of a party.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **String** | The identifier assigned by the Acquirer to the Acceptor and its location, or to the Payment Facilitator. |  [optional] |
|**addtlId** | **String** | The identifier assigned by Mastercard to the Acceptor. |  [optional] |
|**nmAndLctn** | **String** | The name of the Acceptor that should be recognizable by the Cardholder and may include other descriptors, such as a store number. |  [optional] |
|**adr** | [**MsgauthorisationinitiationAddress1**](MsgauthorisationinitiationAddress1.md) |  |  [optional] |
|**email** | **String** | The email address of the Acceptor that can be used for transaction inquiries. |  [optional] |
|**urlAdr** | **String** | The web address of the Acceptor. |  [optional] |
|**phneNb** | **String** | The business phone number of the Acceptor, if different from the Acceptor Customer Service Phone Number. |  [optional] |
|**cstmrSvc** | **String** | The phone number of the Acceptor that can be used for transaction inquiries. |  [optional] |
|**addtlCtctInf** | **String** | Additional information for contacting the Acceptor, such as an additional phone number or a contact name. |  [optional] |
|**taxRegnId** | **String** | The identifier of the Acceptor issued by a taxation authority. |  [optional] |
|**addtlData** | [**List&lt;MsgauthorisationinitiationAdditionalData1&gt;**](MsgauthorisationinitiationAdditionalData1.md) | Additional data about or related to the Acceptor, such as the Payment Facilitator ID.  The data is sent in a name-value pair: Acceptor Additional Data Name and Acceptor Additional Data Value. |  [optional] |
|**spnsrdMrchnt** | [**List&lt;MsgauthorisationinitiationSponsoredMerchant1&gt;**](MsgauthorisationinitiationSponsoredMerchant1.md) | Sponsored merchant is a merchant that uses the payment services of another entity that acts as the card acceptor. |  [optional] |



