

# MsgauthorisationinitiationAdditionalAmounts1

Amounts that are not part of the transaction amount and not included in reconciliation.  ISO 8583 bit 54

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**tp** | **MsgauthorisationinitiationTypeOfAmount12Code** |  |  [optional] |
|**othrTp** | **String** | The code identifying the type of an Additional Amount when the Additional Amount Type is \&quot;OTHN\&quot; or \&quot;OTHP,\&quot; which indicate a national, network, or customer specific value. |  [optional] |
|**amt** | [**MsgauthorisationinitiationAmount14**](MsgauthorisationinitiationAmount14.md) |  |  [optional] |



