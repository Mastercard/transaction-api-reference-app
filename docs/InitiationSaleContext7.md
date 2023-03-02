

# InitiationSaleContext7

Context of the sale associated with the card payment transaction.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**saleId** | **String** | Identification of the sale terminal (electronic cash register or point of sale terminal) or the sale system. |  [optional] |
|**saleRefId** | **String** | An identifier of the transaction in the sale system, such as a concatenation of the transaction ID and other information that can distinguish the transaction across stores, such as a store number. |  [optional] |
|**saleRefNb** | **String** | An identifier of the transaction in the sale system. |  [optional] |
|**goodsAndSvcsTp** | **InitiationGoodsAndServices1Code** |  |  [optional] |
|**goodAndSvcsSubTp** | **InitiationGoodsAndServicesSubType1Code** |  |  [optional] |
|**goodAndSvcsOthrSubTp** | **String** | Other goods and services sub type applied to the transaction. |  [optional] |
|**spltPmtInd** | **Boolean** | The indicator that the transaction is a partial payment and the full amount was paid with multiple payment methods. |  [optional] |



