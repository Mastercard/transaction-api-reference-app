

# MsginquiryinitiationPartyIdentification203

Identification of a party.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **String** | Identification of the acceptor. ISO 8583 bit 42 |  [optional] |
|**addtlId** | **String** | Additional identification assigned by an agent to an acceptor. |  [optional] |
|**nmAndLctn** | **String** | Name and location of acceptor.  ISO 8583:87/93 bit 43 &amp; 8583:2003 bit 43-71 (when used for Acceptor name and location) |  [optional] |
|**adr** | [**MsginquiryinitiationAddress1**](MsginquiryinitiationAddress1.md) |  |  [optional] |
|**email** | **String** | Electronic mail address.  ISO 8583:2003 bit 43-71 (when used for Acceptor email address) |  [optional] |
|**urlAdr** | **String** | Universal Resource Locator (URL) address.  ISO 8583:2003 bit 43-71 (when used for Acceptor URL) |  [optional] |
|**phneNb** | **String** | Collection of information that identifies  a phone number as defined by telecom services.  ISO 8583:2003 bit 43-71 (when used for Acceptor phone number) |  [optional] |
|**cstmrSvc** | **String** | Phone number of the customer service.  ISO 8583:2003 bit 43-71 (when used for Acceptor customer service phone number) |  [optional] |
|**addtlCtctInf** | **String** | Additional information used to facilitate contact with the card acceptor, for instance sales agent name, dispute manager name.  ISO 8583:2003 bit 43-71 (when used for Acceptor additional contact information) |  [optional] |
|**taxRegnId** | **String** | Identification of a party by its tax registration number. |  [optional] |
|**addtlData** | [**List&lt;MsginquiryinitiationAdditionalData1&gt;**](MsginquiryinitiationAdditionalData1.md) | Contains additional data. |  [optional] |
|**spnsrdMrchnt** | [**List&lt;MsginquiryinitiationSponsoredMerchant1&gt;**](MsginquiryinitiationSponsoredMerchant1.md) | Sponsored merchant is a merchant that uses the payment services of another entity that acts as the card acceptor. |  [optional] |



