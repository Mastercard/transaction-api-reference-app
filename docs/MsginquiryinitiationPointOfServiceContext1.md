

# MsginquiryinitiationPointOfServiceContext1

Contains point of interaction information specific to a given transaction that may change from transaction to transaction.
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
**mOTOInd** | **Boolean** |  |  [optional]
**prtlApprvlSpprtd** | **Boolean** |  |  [optional]
**cardDataNtryMd** | [**MsginquiryinitiationCardDataReading7Code**](MsginquiryinitiationCardDataReading7Code.md) |  |  [optional]
**othrCardDataNtryMd** | **String** | Other type of card data entry mode. |  [optional]
**storgLctn** | **String** | Storage location of payment credential (for example, Acceptor or third party wallet). |  [optional]
**spclConds** | [**List&lt;MsginquiryinitiationSpecialConditions1&gt;**](MsginquiryinitiationSpecialConditions1.md) | Data used to assign specific conditions at the card acceptor location and decided by bilateral agreements. |  [optional]


