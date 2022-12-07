

# ResponseTransaction78

Contains transaction details.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**txTp** | **String** | The code identifying the general type of transaction, such as a purchase or a balance inquiry. |  [optional] |
|**txSubTp** | **String** | The code identifying the subtype of the transaction, such as the kind of Funds Transfer or a prepaid card load. |  [optional] |
|**txAttr** | **MsgauthorisationresponseTransactionAttribute1Code** |  |  [optional] |
|**othrTxAttr** | **String** | A free text field for providing a classification of the transaction when the Transaction Attribute Code is \&quot;OTHN\&quot; or \&quot;OTHP,\&quot; which indicate a national, network, or customer specific value. |  [optional] |
|**spclPrgrmmQlfctn** | [**List&lt;MsgauthorisationresponseSpecialProgrammeQualification1&gt;**](MsgauthorisationresponseSpecialProgrammeQualification1.md) | Data to qualify for incentive or other related programmes. |  [optional] |
|**txId** | [**MsgauthorisationresponseTransactionIdentification8**](MsgauthorisationresponseTransactionIdentification8.md) |  |  [optional] |
|**txAmts** | [**MsgauthorisationresponseTransactionAmounts1**](MsgauthorisationresponseTransactionAmounts1.md) |  |  [optional] |
|**addtlAmts** | [**List&lt;MsgauthorisationresponseAdditionalAmounts1&gt;**](MsgauthorisationresponseAdditionalAmounts1.md) | Information about amounts that are not part of the Transaction Amount, such as an Issuer-assessed Cardholder fee. |  [optional] |
|**addtlFees** | [**List&lt;MsgauthorisationresponseAdditionalFee1&gt;**](MsgauthorisationresponseAdditionalFee1.md) | Information about fees not included in the transaction amount. |  [optional] |
|**acctBal** | [**List&lt;MsgauthorisationresponseAccountBalance1&gt;**](MsgauthorisationresponseAccountBalance1.md) | Information related to the balance of the account involved in the transaction, such as the available balance or the amount owed. |  [optional] |
|**acctFr** | [**MsgauthorisationresponseAccountDetails2**](MsgauthorisationresponseAccountDetails2.md) |  |  [optional] |
|**acctTo** | [**MsgauthorisationresponseAccountDetails2**](MsgauthorisationresponseAccountDetails2.md) |  |  [optional] |
|**addtlData** | [**List&lt;MsgauthorisationresponseAdditionalData1&gt;**](MsgauthorisationresponseAdditionalData1.md) | Additional data about the transaction. The data is sent in a name-value pair: Transaction Additional Data Name and Transaction Additional Data Value. |  [optional] |



