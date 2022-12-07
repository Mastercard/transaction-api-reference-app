

# MsgauthorisationresponseRiskAssessment1

Indicates to the card issuer the level of risk associated with the transaction.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**rskAssmntNtty** | [**MsgauthorisationresponsePartyIdentification200**](MsgauthorisationresponsePartyIdentification200.md) |  |  [optional] |
|**rskAssmntTp** | **String** | The type of Risk Assessment performed. |  [optional] |
|**rsn** | **List&lt;String&gt;** | The code(s) identify the factor(s) that contributed to the Risk Score. |  [optional] |
|**rslt** | **String** | The score indicating the level of risk for the transaction. |  [optional] |
|**addtlRskData** | [**List&lt;MsgauthorisationresponseAdditionalRiskData1&gt;**](MsgauthorisationresponseAdditionalRiskData1.md) | Additional risk data associated with the transaction. |  [optional] |



