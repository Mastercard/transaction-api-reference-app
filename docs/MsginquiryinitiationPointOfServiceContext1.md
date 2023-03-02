

# MsginquiryinitiationPointOfServiceContext1

Contains point of interaction information specific to a given transaction that may change from transaction to transaction.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**cardPres** | **Boolean** | The indicator that the transaction was initiated with the Card physically present at the point of interaction. |  [optional] |
|**crdhldrPres** | **Boolean** | The indicator that the transaction was initiated with the Cardholder present at the point of interaction. |  [optional] |
|**crdhldrActvtd** | **Boolean** | The indicator that the transaction was initiated on an automated device operated solely by the Cardholder, such as an ATM. |  [optional] |
|**trnspndrInittd** | **Boolean** | The indicator that the transaction was initiated with a transponder. |  [optional] |
|**attnddInd** | **Boolean** | The indicator that the transaction was initiated on a Terminal attended by an Acceptor representative. |  [optional] |
|**uattnddLvlCtgy** | **String** | The code identifying the type of Terminal used by the Cardholder to initiate the unattended transaction, such as a self-service Terminal. |  [optional] |
|**eComrcInd** | **Boolean** | The indicator that the point of interaction was an ecommerce one. |  [optional] |
|**motoInd** | **Boolean** | The indicator that the transaction was imitated by the Cardholder via mail or telephone. |  [optional] |
|**prtlApprvlSpprtd** | **Boolean** | The indicator that the Terminal supports partial approvals, which allows for the authorization of less than the full transaction amount so the Cardholder can use multiple forms of payment. code identifying the type of Terminal used by the Cardholder to initiate the unattended transaction, such as a self-service Terminal. |  [optional] |
|**cardDataNtryMd** | **MsginquiryinitiationCardDataReading7Code** |  |  [optional] |
|**othrCardDataNtryMd** | **String** | A free text field for identifying the Card data input method when the Terminal Card Data Input Method Code is \&quot;OTHN\&quot; or \&quot;OTHP,\&quot; which indicate a national, network, or customer specific value. |  [optional] |
|**storgLctn** | **String** | The code identifying where the Card data is stored, such as on a Wallet or by the Acceptor. |  [optional] |
|**spclConds** | [**List&lt;MsginquiryinitiationSpecialConditions1&gt;**](MsginquiryinitiationSpecialConditions1.md) | Data used to assign specific conditions at the card acceptor location and decided by bilateral agreements. |  [optional] |



