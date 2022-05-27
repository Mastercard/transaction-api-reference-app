

# MsgfinancialinitiationTransaction77

Contains transaction details.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**txTp** | **String** |  |  [optional]
**txSubTp** | **String** |  |  [optional]
**addtlSvc** | [**List&lt;MsgfinancialinitiationAdditionalService1&gt;**](MsgfinancialinitiationAdditionalService1.md) | Additional functions or services to be performed in conjunction with the transaction. |  [optional]
**txAttr** | [**MsgfinancialinitiationTransactionAttribute1Code**](MsgfinancialinitiationTransactionAttribute1Code.md) |  |  [optional]
**othrTxAttr** | **String** | Other transaction attribute defined at national or private level. |  [optional]
**altrnMsgRsn** | **List&lt;String&gt;** | Supports message reason codes that are not defined in external code list. |  [optional]
**preAuthstnTmLmt** | **String** |  |  [optional]
**spclPrgrmmQlfctn** | [**List&lt;MsgfinancialinitiationSpecialProgrammeQualification1&gt;**](MsgfinancialinitiationSpecialProgrammeQualification1.md) | Data to qualify for incentive or other related programmes. |  [optional]
**txId** | [**MsgfinancialinitiationTransactionIdentification8**](MsgfinancialinitiationTransactionIdentification8.md) |  |  [optional]
**dsptData** | [**List&lt;InitiationDisputeData1&gt;**](InitiationDisputeData1.md) | Information about the dispute. |  [optional]
**txAmts** | [**MsgfinancialinitiationTransactionAmounts1**](MsgfinancialinitiationTransactionAmounts1.md) |  |  [optional]
**addtlAmts** | [**List&lt;MsgfinancialinitiationAdditionalAmounts1&gt;**](MsgfinancialinitiationAdditionalAmounts1.md) |  |  [optional]
**addtlFees** | [**List&lt;MsgfinancialinitiationAdditionalFee1&gt;**](MsgfinancialinitiationAdditionalFee1.md) | Fees not included in the transaction amount. |  [optional]
**fndsSvcs** | [**MsgfinancialinitiationFundingService1**](MsgfinancialinitiationFundingService1.md) |  |  [optional]
**acctFr** | [**MsgfinancialinitiationAccountDetails2**](MsgfinancialinitiationAccountDetails2.md) |  |  [optional]
**acctTo** | [**MsgfinancialinitiationAccountDetails2**](MsgfinancialinitiationAccountDetails2.md) |  |  [optional]
**addtlData** | [**List&lt;MsgfinancialinitiationAdditionalData1&gt;**](MsgfinancialinitiationAdditionalData1.md) | Contains additional data. |  [optional]



