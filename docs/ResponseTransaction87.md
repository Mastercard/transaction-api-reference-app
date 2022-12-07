

# ResponseTransaction87

Inquiry transaction

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**txTp** | **String** | Type of transaction associated with the main service. ISO 8583:87/93/2003 bit 3 |  [optional] |
|**txSubTp** | **String** | Provides further granularity of purpose of TransactionType |  [optional] |
|**spclPrgrmmQlfctn** | [**List&lt;MsginquiryresponseSpecialProgrammeQualification1&gt;**](MsginquiryresponseSpecialProgrammeQualification1.md) | Data to qualify for incentive or other related programmes. |  [optional] |
|**txId** | [**InquiryresponseTransactionIdentification11**](InquiryresponseTransactionIdentification11.md) |  |  [optional] |
|**rcncltnAmt** | [**MsginquiryresponseAmount4**](MsginquiryresponseAmount4.md) |  |  [optional] |
|**addtlAmts** | [**List&lt;InquiryresponseAdditionalAmounts2&gt;**](InquiryresponseAdditionalAmounts2.md) | Amounts that are not part of the transaction amount and not included in reconciliation.  ISO 8583 bit 54 |  [optional] |
|**addtlFees** | [**List&lt;MsginquiryresponseAdditionalFee1&gt;**](MsginquiryresponseAdditionalFee1.md) | Fees not included in the transaction amount but included in the settlement. |  [optional] |
|**acctBal** | [**List&lt;MsginquiryresponseAccountBalance1&gt;**](MsginquiryresponseAccountBalance1.md) | Balance of an account. |  [optional] |
|**acctFr** | [**MsginquiryresponseAccountDetails2**](MsginquiryresponseAccountDetails2.md) |  |  [optional] |
|**addtlData** | [**List&lt;MsginquiryresponseAdditionalData1&gt;**](MsginquiryresponseAdditionalData1.md) | Contains additional data. |  [optional] |



