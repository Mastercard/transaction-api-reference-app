

# MsginquiryinitiationTransactionContext1

Context of the card payment transaction.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**mrchntCtgyCd** | **String** | Category code related to the type of services or goods the merchant provides for the transaction. |  [optional] |
|**mrchntCtgySpcfcData** | **String** | Further details about the merchant that is used in with the merchant category code (MCC) for the particular purchase. |  [optional] |
|**cstmrCnsnt** | **Boolean** | Notifies the express consent of the customer for a given service (used in DCC, funds transfers, money lending, etc.).  True: Explicit customer consent obtained False: Implicit customer consent obtained |  [optional] |
|**iccFllbckInd** | **Boolean** | Indicates a chip data fallback. True: Fallback False: No fallback Default: False |  [optional] |
|**mgntcStrpFllbckInd** | **Boolean** | Indicates a magnetic stripe fallback. True: Fallback False: No fallback Default: False |  [optional] |
|**fnlAuthstnInd** | **Boolean** | Identifies final authorisation messages for the purpose of managing open-to buy or available balance. |  [optional] |
|**txInitr** | **MsginquiryinitiationTransactionInitiator1Code** |  |  [optional] |
|**cardPrgrmm** | [**MsginquiryinitiationCardProgramme1**](MsginquiryinitiationCardProgramme1.md) |  |  [optional] |
|**sttlmSvc** | [**MsginquiryinitiationSettlementService1**](MsginquiryinitiationSettlementService1.md) |  |  [optional] |



