

# InitiationTransaction105

Inquiry transaction

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**txTp** | **String** | Type of transaction associated with the main service. ISO 8583:87/93/2003 bit 3 |  [optional] |
|**txSubTp** | **String** | Provides further granularity of purpose of TransactionType |  [optional] |
|**addtlSvc** | [**List&lt;MsginquiryinitiationAdditionalService1&gt;**](MsginquiryinitiationAdditionalService1.md) | Additional functions or services to be performed in conjunction with the transaction. |  [optional] |
|**msgRsn** | **List&lt;String&gt;** | Reason to send the message. ISO 8583:93/2003 bit 25.  The ISO 8583 maintenance agency (MA) manages this code list. |  [optional] |
|**altrnMsgRsn** | **String** | Supports message reason codes that are not defined  in external code list. |  [optional] |
|**spclPrgrmmQlfctn** | [**List&lt;MsginquiryinitiationSpecialProgrammeQualification1&gt;**](MsginquiryinitiationSpecialProgrammeQualification1.md) | Data to qualify for incentive or other related programmes. |  [optional] |
|**txId** | [**InquiryinitiationTransactionIdentification11**](InquiryinitiationTransactionIdentification11.md) |  |  [optional] |
|**txCcy** | **String** | Transaction currency of the acceptor. ISO 4217 |  [optional] |
|**dtldAmt** | [**List&lt;InitiationDetailedAmount20&gt;**](InitiationDetailedAmount20.md) | Further details of some or all amounts in the transaction amount.  The detailed amount is used to calculate the reconciliation amount for messages in which the transaction amount is absent. |  [optional] |
|**rcncltnAmt** | [**MsginquiryinitiationAmount4**](MsginquiryinitiationAmount4.md) |  |  [optional] |
|**addtlAmts** | [**List&lt;InquiryinitiationAdditionalAmounts2&gt;**](InquiryinitiationAdditionalAmounts2.md) | Amounts that are not part of the transaction amount and not included in reconciliation.  ISO 8583 bit 54 |  [optional] |
|**addtlFees** | [**List&lt;MsginquiryinitiationAdditionalFee1&gt;**](MsginquiryinitiationAdditionalFee1.md) | Fees not included in the transaction amount but included in the settlement. |  [optional] |
|**acctFr** | [**MsginquiryinitiationAccountDetails2**](MsginquiryinitiationAccountDetails2.md) |  |  [optional] |
|**txDesc** | **String** | Transaction data related to programmes and services, content and format based on bilateral agreements. |  [optional] |
|**addtlData** | [**List&lt;MsginquiryinitiationAdditionalData1&gt;**](MsginquiryinitiationAdditionalData1.md) | Contains additional data. |  [optional] |



