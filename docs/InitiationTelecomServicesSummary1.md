

# InitiationTelecomServicesSummary1

Telecom services summary component carries summary level telephony billing data.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**cstmr** | [**InitiationCustomer6**](InitiationCustomer6.md) |  |  [optional]
**bllgStmtPrdStart** | **String** | Contains the billing period start date for telecommunication or related services. |  [optional]
**bllgStmtPrdEnd** | **String** | Contains the billing period end date for telecommunication or related services. |  [optional]
**bllgEvt** | [**List&lt;InitiationAmount10&gt;**](InitiationAmount10.md) | Summary of the charges associated with the billing event. |  [optional]
**ttlTax** | [**List&lt;InitiationTax33&gt;**](InitiationTax33.md) | Total of taxes applicable to the billing amount. |  [optional]
**addtlData** | **String** | Additional user-defined data pertaining to the shipment. |  [optional]



