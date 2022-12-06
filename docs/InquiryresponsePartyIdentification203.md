

# InquiryresponsePartyIdentification203

Identification of a party.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **String** | Identification of the acceptor. ISO 8583 bit 42 |  [optional] |
|**nmAndLctn** | **String** | Name and location of acceptor.  ISO 8583:87/93 bit 43 &amp; 8583:2003 bit 43-71 (when used for Acceptor name and location) |  [optional] |
|**adr** | [**InquiryresponseAddress1**](InquiryresponseAddress1.md) |  |  [optional] |
|**addtlData** | [**List&lt;MsginquiryresponseAdditionalData1&gt;**](MsginquiryresponseAdditionalData1.md) | Contains additional data. |  [optional] |
|**spnsrdMrchnt** | [**List&lt;InquiryresponseSponsoredMerchant1&gt;**](InquiryresponseSponsoredMerchant1.md) | Sponsored merchant is a merchant that uses the payment services of another entity that acts as the card acceptor. |  [optional] |



