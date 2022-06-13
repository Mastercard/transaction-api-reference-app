

# MsgfinancialinitiationTransactionContext1

Context of the card payment transaction.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**mrchntCtgyCd** | **String** | Category code related to the type of services or goods the merchant provides for the transaction. |  [optional]
**mrchntCtgySpcfcData** | **String** | Further details about the merchant that is used in with the merchant category code (MCC) for the particular purchase. |  [optional]
**cstmrCnsnt** | **Boolean** |  |  [optional]
**iCCFllbckInd** | **Boolean** |  |  [optional]
**mgntcStrpFllbckInd** | **Boolean** |  |  [optional]
**latePresntmntInd** | **Boolean** |  |  [optional]
**fnlAuthstnInd** | **Boolean** | Identifies final authorisation messages for the purpose of managing open-to buy or available balance. |  [optional]
**txInitr** | [**MsgfinancialinitiationTransactionInitiator1Code**](MsgfinancialinitiationTransactionInitiator1Code.md) |  |  [optional]
**cardPrgrmm** | [**MsgfinancialinitiationCardProgramme1**](MsgfinancialinitiationCardProgramme1.md) |  |  [optional]
**sttlmSvc** | [**MsgfinancialinitiationSettlementService1**](MsgfinancialinitiationSettlementService1.md) |  |  [optional]
**rcncltn** | [**MsgfinancialinitiationReconciliation3**](MsgfinancialinitiationReconciliation3.md) |  |  [optional]



