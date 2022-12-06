

# InitiationTransactionContext4

Context of the card payment transaction

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**mrchntCtgyCd** | **String** | The ISO 18245 code identifying the type of goods or services generally provided by the Acceptor, which is used to drive transaction processing logic. |  [optional] |
|**dfrrdDlvryInd** | **Boolean** | Indicates whether the delivery of good ordered will be deferred. |  [optional] |
|**txInitr** | **MsgreversalinitiationTransactionInitiator1Code** |  |  [optional] |
|**cardPrgrmm** | [**MsgreversalinitiationCardProgramme1**](MsgreversalinitiationCardProgramme1.md) |  |  [optional] |
|**sttlmSvc** | [**MsgreversalinitiationSettlementService1**](MsgreversalinitiationSettlementService1.md) |  |  [optional] |
|**rcncltn** | [**MsgreversalinitiationReconciliation3**](MsgreversalinitiationReconciliation3.md) |  |  [optional] |
|**captrDt** | **String** | The date the transaction was completed and captured. |  [optional] |



