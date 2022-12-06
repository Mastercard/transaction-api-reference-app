

# MsgauthorisationinitiationTransactionContext1

Context of the card payment transaction.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**mrchntCtgyCd** | **String** | The ISO 18245 code identifying the type of goods or services generally provided by the Acceptor, which is used to drive transaction processing logic. |  [optional] |
|**mrchntCtgySpcfcData** | **String** | The code identifying a classification of the transaction, such as a bill payment or hotel rental. |  [optional] |
|**cstmrCnsnt** | **Boolean** | The indicator that the Cardholder gave express consent for a service, such as for a Funds Transfer. |  [optional] |
|**iccFllbckInd** | **Boolean** | The indicator that an attempted chip entry failed and the Card was swiped or the Card data was entered manually instead. |  [optional] |
|**mgntcStrpFllbckInd** | **Boolean** | The indicator that an attempted magnetic stripe entry failed and the Card data was entered manually instead. |  [optional] |
|**fnlAuthstnInd** | **Boolean** | The indicator that the authorization request is for the final amount that should be billed to the Cardholder. |  [optional] |
|**txInitr** | **MsgauthorisationinitiationTransactionInitiator1Code** |  |  [optional] |
|**cardPrgrmm** | [**MsgauthorisationinitiationCardProgramme1**](MsgauthorisationinitiationCardProgramme1.md) |  |  [optional] |
|**sttlmSvc** | [**MsgauthorisationinitiationSettlementService1**](MsgauthorisationinitiationSettlementService1.md) |  |  [optional] |



