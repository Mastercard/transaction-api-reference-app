

# CryptographicKey13

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** |  | 
**addtlId** | **List&lt;byte[]&gt;** |  |  [optional]
**vrsn** | **String** |  | 
**tp** | [**TpEnum**](#TpEnum) |  |  [optional]
**fctn** | [**List&lt;FctnEnum&gt;**](#List&lt;FctnEnum&gt;) |  |  [optional]
**actvtnDt** | [**OffsetDateTime**](OffsetDateTime.md) |  |  [optional]
**deactvtnDt** | [**OffsetDateTime**](OffsetDateTime.md) |  |  [optional]
**keyVal** | [**ContentInformationType19**](ContentInformationType19.md) |  |  [optional]
**keyChckVal** | **List&lt;byte[]&gt;** |  |  [optional]
**addtlMgmtInf** | [**List&lt;GenericInformation1&gt;**](GenericInformation1.md) |  |  [optional]



## Enum: TpEnum

Name | Value
---- | -----
AES_2 | &quot;AES_2&quot;
AES_5 | &quot;AES_5&quot;
AES_9 | &quot;AES_9&quot;
DKP_9 | &quot;DKP_9&quot;
EDE_3 | &quot;EDE_3&quot;
EDE_4 | &quot;EDE_4&quot;



## Enum: List&lt;FctnEnum&gt;

Name | Value
---- | -----
DCPT | &quot;DCPT&quot;
DDEC | &quot;DDEC&quot;
DENC | &quot;DENC&quot;
ENCR | &quot;ENCR&quot;
KEYD | &quot;KEYD&quot;
KEYG | &quot;KEYG&quot;
KEYI | &quot;KEYI&quot;
KEYX | &quot;KEYX&quot;
MACG | &quot;MACG&quot;
MACV | &quot;MACV&quot;
PIND | &quot;PIND&quot;
PINE | &quot;PINE&quot;
PINV | &quot;PINV&quot;
SIGG | &quot;SIGG&quot;
SUGV | &quot;SUGV&quot;
TRNI | &quot;TRNI&quot;
TRNX | &quot;TRNX&quot;



