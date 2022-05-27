

# MsgauthorisationinitiationTransactionContext1

Context of the card payment transaction.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**mrchntCtgyCd** | **String** | Category code related to the type of services or goods the merchant provides for the transaction. |  [optional]
**mrchntCtgySpcfcData** | **String** | Further details about the merchant that is used in with the merchant category code (MCC) for the particular purchase. |  [optional]
**cstmrCnsnt** | **Boolean** |  |  [optional]
**iCCFllbckInd** | **Boolean** |  |  [optional]
**mgntcStrpFllbckInd** | **Boolean** |  |  [optional]
**fnlAuthstnInd** | **Boolean** | Identifies final authorisation messages for the purpose of managing open-to buy or available balance. |  [optional]
**txInitr** | [**MsgauthorisationinitiationTransactionInitiator1Code**](MsgauthorisationinitiationTransactionInitiator1Code.md) |  |  [optional]
**cardPrgrmm** | [**MsgauthorisationinitiationCardProgramme1**](MsgauthorisationinitiationCardProgramme1.md) |  |  [optional]
**sttlmSvc** | [**MsgauthorisationinitiationSettlementService1**](MsgauthorisationinitiationSettlementService1.md) |  |  [optional]



