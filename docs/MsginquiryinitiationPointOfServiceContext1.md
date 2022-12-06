

# MsginquiryinitiationPointOfServiceContext1

Contains point of interaction information specific to a given transaction that may change from transaction to transaction.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**cardPres** | **Boolean** | Indicates whether the transaction has been initiated by a card physically present or not.  True: Card physically present during the transaction False: Card physically absent during the transaction.  ISO 8583:87 bit 25, ISO 8583:93 bit 22-6. |  [optional] |
|**crdhldrPres** | **Boolean** | Indicates whether the transaction has been initiated in presence of the cardholder or not.  True: Cardholder present during the transaction False: Cardholder absent during the transaction.  ISO 8583:87 bit 25, ISO 8583:93 bit 22-5 |  [optional] |
|**crdhldrActvtd** | **Boolean** | Indicates whether the automated device was operated solely by the cardholder or not (for example, vending machine, automated fuel dispenser, ATM, kiosk, etc.).  True: Device operated solely by the cardholder False: Device not operated solely by the cardholder.  ISO 8583:2003 bit 22-3 |  [optional] |
|**trnspndrInittd** | **Boolean** | Transaction initiated through a transponder or not. True: Transaction initiated through a transponder. False: Transaction not initiated through a transponder. |  [optional] |
|**attnddInd** | **Boolean** | Card acceptor representative in attendance at the point of service during the transaction. When an acceptor�s terminal is semi-attended (for example, multiple terminals supervised by a single clerk), it will be identified as �attended�.  True: Attended transaction at the terminal False: Non-attended transaction at the terminal |  [optional] |
|**uattnddLvlCtgy** | **String** | Transaction category level on an unattended terminal. |  [optional] |
|**eComrcInd** | **Boolean** | Indicates whether the point of service is an e-commerce one or not: True: e-commerce False: non e-commerce Default: False  ISO 8583:2003 bit 22-3 |  [optional] |
|**motoInd** | **Boolean** | Indicates whether the context of the point of service is a MOTO one or not. True: MOTO False: non-MOTO Default: False  ISO 8583:2003 bit 25 ISO 8583:2003 bit 22-5 ISO 8583:2003 bit 22-3 |  [optional] |
|**prtlApprvlSpprtd** | **Boolean** | Indicates whether the point of service supports partial approval or not. True: partial approval is supported False: partial approval is not supported |  [optional] |
|**cardDataNtryMd** | **MsginquiryinitiationCardDataReading7Code** |  |  [optional] |
|**othrCardDataNtryMd** | **String** | Other type of card data entry mode. |  [optional] |
|**storgLctn** | **String** | Storage location of payment credential (for example, Acceptor or third party wallet). |  [optional] |
|**spclConds** | [**List&lt;MsginquiryinitiationSpecialConditions1&gt;**](MsginquiryinitiationSpecialConditions1.md) | Data used to assign specific conditions at the card acceptor location and decided by bilateral agreements. |  [optional] |



