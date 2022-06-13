

# InitiationPlan1

Attributes of the instalment plan.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**instlmtPmtTp** | **String** | Instalment payment type. |  [optional]
**intrstRate** | [**List&lt;InitiationInterestRateDetails1&gt;**](InitiationInterestRateDetails1.md) | Details of the interest rate. |  [optional]
**frstPmtDt** | **String** | Date of the first payment. |  [optional]
**frstAmt** | **Double** | Amount of the first payment when different from the subsequent payments. |  [optional]
**sbsqntAmt** | **Double** | Amount of subsequent payments. |  [optional]
**ttlNbOfPmts** | **Double** |  |  [optional]
**instlmtCcy** | **String** | Currency code associated with the instalment amount.  ISO 4217 \&quot;Codes for the representation of currencies and funds\&quot;. |  [optional]
**gracePrd** | [**InitiationGracePeriod1**](InitiationGracePeriod1.md) |  |  [optional]
**amtDtls** | [**List&lt;InitiationInstalmentAmountDetails1&gt;**](InitiationInstalmentAmountDetails1.md) | Contains the amount details of an instalment plan. |  [optional]
**grdTtlAmt** | **Double** | Total amount of the instalment including charges, insurance and taxes in addition to the funded amount. |  [optional]



