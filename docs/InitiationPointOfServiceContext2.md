

# InitiationPointOfServiceContext2

Context of the transaction at the point of service.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**cardPres** | **Boolean** | Indicates whether the transaction has been initiated by a card physically present or not.  True: Card physically present during the transaction False: Card physically absent during the transaction.  ISO 8583:87 bit 25, ISO 8583:93 bit 22-6. |  [optional]
**crdhldrPres** | **Boolean** |  |  [optional]
**crdhldrActvtd** | **Boolean** |  |  [optional]
**trnspndrInittd** | **Boolean** | Transaction initiated through a transponder or not. True: Transaction initiated through a transponder. False: Transaction not initiated through a transponder. |  [optional]
**attnddInd** | **Boolean** |  |  [optional]
**uattnddLvlCtgy** | **String** | Transaction category level on an unattended terminal. |  [optional]
**eComrcInd** | **Boolean** |  |  [optional]
**eComrcData** | [**List&lt;InitiationECommerceData1&gt;**](InitiationECommerceData1.md) | Contains electronic commerce data. |  [optional]
**mOTOInd** | **Boolean** |  |  [optional]
**prtlApprvlSpprtd** | **Boolean** |  |  [optional]
**delydAuthstnInd** | **Boolean** |  |  [optional]
**sctyChrtcs** | [**List&lt;InitiationSecurityCharacteristics1Code&gt;**](InitiationSecurityCharacteristics1Code.md) |  |  [optional]
**othrSctyChrtcs** | **String** | Other security characteristics. |  [optional]
**cardDataNtryMd** | [**MsgreversalinitiationCardDataReading7Code**](MsgreversalinitiationCardDataReading7Code.md) |  |  [optional]
**othrCardDataNtryMd** | **String** | Other type of card data entry mode. |  [optional]
**storgLctn** | **String** | Storage location of payment credential (for example, Acceptor or third party wallet). |  [optional]
**spclConds** | [**List&lt;MsgreversalinitiationSpecialConditions1&gt;**](MsgreversalinitiationSpecialConditions1.md) | Data used to assign specific conditions at the card acceptor location and decided by bilateral agreements. |  [optional]



