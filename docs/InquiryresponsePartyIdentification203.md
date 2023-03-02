

# InquiryresponsePartyIdentification203

Identification of a party.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **String** | The identifier assigned by the Acquirer to the Acceptor and its location, or to the Payment Facilitator. |  [optional] |
|**nmAndLctn** | **String** | The name of the Acceptor that should be recognizable by the Cardholder and may include other descriptors, such as a store number. |  [optional] |
|**adr** | [**InquiryresponseAddress1**](InquiryresponseAddress1.md) |  |  [optional] |
|**addtlData** | [**List&lt;MsginquiryresponseAdditionalData1&gt;**](MsginquiryresponseAdditionalData1.md) | Additional data about or related to the Acceptor, such as the Payment Facilitator ID.  The data is sent in a name-value pair: Acceptor Additional Data Name and Acceptor Additional Data Value. |  [optional] |
|**spnsrdMrchnt** | [**List&lt;InquiryresponseSponsoredMerchant1&gt;**](InquiryresponseSponsoredMerchant1.md) | A Merchant that uses the services of a Payment Facilitator.  Referred to as Submerchant in Mastercard Rules. |  [optional] |



