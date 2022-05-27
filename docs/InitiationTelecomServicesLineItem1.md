

# InitiationTelecomServicesLineItem1

Telecom services line item carries detail level telephony billing data.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**startDtTm** | **String** | Contains the start date and time of the phone call. |  [optional]
**tmPrd** | **String** | Describes the period (such as peak or off peak) of the phone call. |  [optional]
**drtn** | **String** | Duration of phone call expressed in HHMMSS format. |  [optional]
**callFr** | [**InitiationTelecomCallDetails1**](InitiationTelecomCallDetails1.md) |  |  [optional]
**callTo** | [**InitiationTelecomCallDetails1**](InitiationTelecomCallDetails1.md) |  |  [optional]
**chrg** | [**List&lt;InitiationAmount11&gt;**](InitiationAmount11.md) | Contains the amount pertaining to the telephony billing event. |  [optional]
**ttlTax** | [**List&lt;InitiationTax33&gt;**](InitiationTax33.md) |  |  [optional]
**ttlAmt** | **Double** | Total amount applicable to the billing event. |  [optional]
**desc** | **String** |  |  [optional]
**addtlData** | **String** | Additional user-defined data pertaining to the telecommunications services. |  [optional]



