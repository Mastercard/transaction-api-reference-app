

# MsgauthorisationinitiationTransaction77

Contains transaction details.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**txTp** | **String** | The code identifying the general type of transaction, such as a purchase or a balance inquiry. |  [optional] |
|**txSubTp** | **String** | The code identifying the subtype of the transaction, such as the kind of Funds Transfer or a prepaid card load. |  [optional] |
|**addtlSvc** | [**List&lt;MsgauthorisationinitiationAdditionalService1&gt;**](MsgauthorisationinitiationAdditionalService1.md) | Information about an additional service applied to the transaction, such as cash back. |  [optional] |
|**txAttr** | **MsgauthorisationinitiationTransactionAttribute1Code** |  |  [optional] |
|**othrTxAttr** | **String** | A free text field for providing a classification of the transaction when the Transaction Attribute Code is \&quot;OTHN\&quot; or \&quot;OTHP,\&quot; which indicate a national, network, or customer specific value. |  [optional] |
|**msgRsn** | **List&lt;String&gt;** | The conditions under which the message was sent. |  [optional] |
|**altrnMsgRsn** | **List&lt;String&gt;** | The code identifying a specific reason for the message. |  [optional] |
|**preAuthstnTmLmt** | **String** | The number of minutes within which the Acceptor is expected to complete the transaction. |  [optional] |
|**spclPrgrmmQlfctn** | [**List&lt;MsgauthorisationinitiationSpecialProgrammeQualification1&gt;**](MsgauthorisationinitiationSpecialProgrammeQualification1.md) | Information related to the Card&#39;s qualification in a Mastercard program that affects, for example, how the transaction is processed or the associated interchange fees. |  [optional] |
|**txId** | [**MsgauthorisationinitiationTransactionIdentification8**](MsgauthorisationinitiationTransactionIdentification8.md) |  |  [optional] |
|**txAmts** | [**MsgauthorisationinitiationTransactionAmounts1**](MsgauthorisationinitiationTransactionAmounts1.md) |  |  [optional] |
|**addtlAmts** | [**List&lt;MsgauthorisationinitiationAdditionalAmounts1&gt;**](MsgauthorisationinitiationAdditionalAmounts1.md) | Information about amounts that are not part of the Transaction Amount, such as an Issuer-assessed Cardholder fee. |  [optional] |
|**addtlFees** | [**List&lt;MsgauthorisationinitiationAdditionalFee1&gt;**](MsgauthorisationinitiationAdditionalFee1.md) | Information about fees not included in the transaction amount. |  [optional] |
|**fndsSvcs** | [**MsgauthorisationinitiationFundingService1**](MsgauthorisationinitiationFundingService1.md) |  |  [optional] |
|**acctFr** | [**MsgauthorisationinitiationAccountDetails2**](MsgauthorisationinitiationAccountDetails2.md) |  |  [optional] |
|**acctTo** | [**MsgauthorisationinitiationAccountDetails2**](MsgauthorisationinitiationAccountDetails2.md) |  |  [optional] |
|**txDesc** | **String** | Transaction data related to programs and services, content and format based on bilateral agreements. |  [optional] |
|**addtlData** | [**List&lt;MsgauthorisationinitiationAdditionalData1&gt;**](MsgauthorisationinitiationAdditionalData1.md) | Additional data about the transaction. The data is sent in a name-value pair: Transaction Additional Data Name and Transaction Additional Data Value. |  [optional] |



