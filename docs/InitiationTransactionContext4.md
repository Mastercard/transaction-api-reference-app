

# InitiationTransactionContext4

Context of the card payment transaction

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**mrchntCtgyCd** | **String** | Category code related to the type of services or goods the merchant provides for the transaction.  ISO 8583:87 bit 18, ISO 8583:93 bit 18 &amp; 26, ISO 8583:2003 bit 26 ISO 18245 |  [optional] |
|**dfrrdDlvryInd** | **Boolean** | Indicates a deferred delivery as defined by each specific implementation.  True: deferred delivery. False: Delivery is not identified as deffered. Default: False. |  [optional] |
|**txInitr** | **MsgreversalinitiationTransactionInitiator1Code** |  |  [optional] |
|**cardPrgrmm** | [**MsgreversalinitiationCardProgramme1**](MsgreversalinitiationCardProgramme1.md) |  |  [optional] |
|**sttlmSvc** | [**MsgreversalinitiationSettlementService1**](MsgreversalinitiationSettlementService1.md) |  |  [optional] |
|**rcncltn** | [**MsgreversalinitiationReconciliation3**](MsgreversalinitiationReconciliation3.md) |  |  [optional] |
|**captrDt** | **String** | Date the transaction was completed and captured. ISO 8583 bit 17 |  [optional] |



