

# InitiationAddendumData1

Component contains data structures applicable to certain merchant verticals that require industry-specific data within transaction messages.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**purchsIdrTp** | [**InitiationPurchaseIdentifierType1Code**](InitiationPurchaseIdentifierType1Code.md) |  |  [optional]
**othrPurchsIdrTp** | **String** | Used when Purchase Identifier Type is Other National or Other Private. |  [optional]
**purchsIdr** | **String** | Contains a value identifying Invoice Data or Purchase Request Data. |  [optional]
**addtlAccptrData** | [**InitiationAdditionalAcceptorData1**](InitiationAdditionalAcceptorData1.md) |  |  [optional]
**fleet** | [**InitiationFleetData2**](InitiationFleetData2.md) |  |  [optional]
**pssngrTrnsprt** | [**InitiationPassengerTransport1**](InitiationPassengerTransport1.md) |  |  [optional]
**telecomSvcs** | [**InitiationTelecomServices1**](InitiationTelecomServices1.md) |  |  [optional]
**instlmt** | [**InitiationInstalment3**](InitiationInstalment3.md) |  |  [optional]
**addtlData** | [**List&lt;MsgfinancialinitiationAdditionalData1&gt;**](MsgfinancialinitiationAdditionalData1.md) | Contains additional data for the addendum. |  [optional]



