

# InitiationSaleContext7

Context of the sale associated with the card payment transaction.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**saleId** | **String** | Identification of the sale terminal (electronic cash register or point of sale terminal) or the sale system. |  [optional]
**saleRefId** | **String** | Global reference of the sale transaction for the sale system. |  [optional]
**saleRefNb** | **String** | Identify a sale transaction assigned by the sale system. |  [optional]
**goodsAndSvcsTp** | [**InitiationGoodsAndServices1Code**](InitiationGoodsAndServices1Code.md) |  |  [optional]
**goodAndSvcsSubTp** | [**InitiationGoodsAndServicesSubType1Code**](InitiationGoodsAndServicesSubType1Code.md) |  |  [optional]
**goodAndSvcsOthrSubTp** | **String** | Other goods and services sub type applied to the transaction. |  [optional]
**spltPmtInd** | **Boolean** | Also referred to as split tender. Indicates whether the payment transaction is a partial payment of the sale transaction. True: Partial payment of a sale transaction False: Not a partial payment of a sale transaction. |  [optional]



