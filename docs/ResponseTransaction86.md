

# ResponseTransaction86

Contains transaction details.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**txTp** | **String** | The code identifying the general type of transaction, such as a purchase or a balance inquiry. |  [optional] |
|**txSubTp** | **String** | The code identifying the subtype of the transaction, such as the kind of Funds Transfer or a prepaid card load. |  [optional] |
|**txAttr** | **MsgreversalresponseTransactionAttribute1Code** |  |  [optional] |
|**othrTxAttr** | **String** | A free text field for providing a classification of the transaction when the Transaction Attribute Code is \&quot;OTHN\&quot; or \&quot;OTHP,\&quot; which indicate a national, network, or customer specific value. |  [optional] |
|**spclPrgrmmQlfctn** | [**List&lt;MsgreversalresponseSpecialProgrammeQualification1&gt;**](MsgreversalresponseSpecialProgrammeQualification1.md) | Data to qualify for incentive or other related programmes. |  [optional] |
|**txId** | [**MsgreversalresponseTransactionIdentification8**](MsgreversalresponseTransactionIdentification8.md) |  |  [optional] |
|**txAmts** | [**MsgreversalresponseTransactionAmounts1**](MsgreversalresponseTransactionAmounts1.md) |  |  [optional] |
|**addtlAmts** | [**List&lt;MsgreversalresponseAdditionalAmounts1&gt;**](MsgreversalresponseAdditionalAmounts1.md) | Amounts that are not part of the transaction amount and not included in reconciliation.  ISO 8583 bit 54 |  [optional] |
|**addtlFees** | [**List&lt;MsgreversalresponseAdditionalFee1&gt;**](MsgreversalresponseAdditionalFee1.md) | Fees not included in the transaction amount. |  [optional] |
|**orgnlAddtlFees** | [**List&lt;MsgreversalresponseAdditionalFee1&gt;**](MsgreversalresponseAdditionalFee1.md) | Fees not included in the original transaction amount. |  [optional] |
|**acctFr** | [**MsgreversalresponseAccountDetails2**](MsgreversalresponseAccountDetails2.md) |  |  [optional] |
|**acctTo** | [**MsgreversalresponseAccountDetails2**](MsgreversalresponseAccountDetails2.md) |  |  [optional] |
|**txDesc** | **String** | Transaction data related to programs and services, content and format based on bilateral agreements. |  [optional] |
|**addtlData** | [**List&lt;MsgreversalresponseAdditionalData1&gt;**](MsgreversalresponseAdditionalData1.md) | Contains additional data. |  [optional] |



