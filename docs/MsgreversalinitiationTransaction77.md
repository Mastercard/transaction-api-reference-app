

# MsgreversalinitiationTransaction77

Contains transaction details.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**txTp** | **String** | The code identifying the general type of transaction, such as a purchase or a balance inquiry. |  [optional] |
|**txSubTp** | **String** | The code identifying the subtype of the transaction, such as the kind of Funds Transfer or a prepaid card load. |  [optional] |
|**addtlSvc** | [**List&lt;MsgreversalinitiationAdditionalService1&gt;**](MsgreversalinitiationAdditionalService1.md) | Information about an additional service applied to the transaction, such as cash back. |  [optional] |
|**txAttr** | **MsgreversalinitiationTransactionAttribute1Code** |  |  [optional] |
|**othrTxAttr** | **String** | A free text field for providing a classification of the transaction when the Transaction Attribute Code is \&quot;OTHN\&quot; or \&quot;OTHP,\&quot; which indicate a national, network, or customer specific value. |  [optional] |
|**msgRsn** | **List&lt;String&gt;** | The conditions under which the message was sent. |  [optional] |
|**altrnMsgRsn** | **List&lt;String&gt;** | The code identifying a specific reason for the message. |  [optional] |
|**preAuthstnTmLmt** | **String** | The number of minutes within which the Acceptor is expected to complete the transaction. |  [optional] |
|**spclPrgrmmQlfctn** | [**List&lt;MsgreversalinitiationSpecialProgrammeQualification1&gt;**](MsgreversalinitiationSpecialProgrammeQualification1.md) | Information related to the Card&#39;s qualification in a Mastercard program that affects, for example, how the transaction is processed or the associated interchange fees. |  [optional] |
|**txId** | [**MsgreversalinitiationTransactionIdentification8**](MsgreversalinitiationTransactionIdentification8.md) |  |  [optional] |
|**dsptData** | [**List&lt;MsgreversalinitiationDisputeData1&gt;**](MsgreversalinitiationDisputeData1.md) | Information regarding a disputed transaction, such as information about chargebacks. |  [optional] |
|**txAmts** | [**MsgreversalinitiationTransactionAmounts1**](MsgreversalinitiationTransactionAmounts1.md) |  |  [optional] |
|**addtlAmts** | [**List&lt;MsgreversalinitiationAdditionalAmounts1&gt;**](MsgreversalinitiationAdditionalAmounts1.md) | Information about amounts that are not part of the Transaction Amount, such as an Issuer-assessed Cardholder fee. |  [optional] |
|**addtlFees** | [**List&lt;MsgreversalinitiationAdditionalFee1&gt;**](MsgreversalinitiationAdditionalFee1.md) | Information about fees not included in the transaction amount. |  [optional] |
|**fndsSvcs** | [**MsgreversalinitiationFundingService1**](MsgreversalinitiationFundingService1.md) |  |  [optional] |
|**acctFr** | [**MsgreversalinitiationAccountDetails2**](MsgreversalinitiationAccountDetails2.md) |  |  [optional] |
|**acctTo** | [**MsgreversalinitiationAccountDetails2**](MsgreversalinitiationAccountDetails2.md) |  |  [optional] |
|**txDesc** | **String** | Transaction data related to programs and services, content and format based on bilateral agreements. |  [optional] |
|**addtlData** | [**List&lt;MsgreversalinitiationAdditionalData1&gt;**](MsgreversalinitiationAdditionalData1.md) | Additional data about the transaction. The data is sent in a name-value pair: Transaction Additional Data Name and Transaction Additional Data Value. |  [optional] |



