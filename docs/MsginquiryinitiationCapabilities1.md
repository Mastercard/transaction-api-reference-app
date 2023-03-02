

# MsginquiryinitiationCapabilities1

Capabilities of the terminal.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**cardRdngCpblty** | **List&lt;MsginquiryinitiationCardDataReading7Code&gt;** | The code(s) identifying the method the Terminal supports for entering Card data, such as via a magnetic stripe or a chip.  If this field specifies a national or network-specific value (\&quot;OTHN\&quot; or \&quot;OTHP\&quot;), a more specific value may be required in Terminal Card Data Input Capability (Other). |  [optional] |
|**othrCardRdngCpblties** | **List&lt;String&gt;** | A free text field for identifying a method the Terminal can use to write to a Card when the Card Writing Capability Code is \&quot;OTHN\&quot; or \&quot;OTHP,\&quot; which indicate a national, network, or customer specific value. |  [optional] |
|**cardWrtgCpblties** | **List&lt;MsginquiryinitiationCardDataWriting1Code&gt;** | The code(s) identifying the method the Terminal can use to write to a Card, such as a magnetic stripe or a chip.  If this field specifies a national or network-specific value (\&quot;OTHN\&quot; or \&quot;OTHP\&quot;), a more specific value may be required in Terminal Card Writing Capability (Other). |  [optional] |
|**othrCardWrtgCpblties** | **List&lt;String&gt;** | A free text field for identifying a method the Terminal can use to write to a Card when the Card Writing Capability Code is \&quot;OTHN\&quot; or \&quot;OTHP,\&quot; which indicate a national, network, or customer specific value. |  [optional] |
|**pinLngthCpblties** | **String** | The maximum number of digits the Terminal can accept when a Cardholder enters their PIN. |  [optional] |
|**pinPadInprtv** | **Boolean** | The indicator that the Terminal has a PIN pad that is not working. |  [optional] |
|**cardCaptrCpbl** | **Boolean** | The indicator that the Terminal can retain a Card if instructed to do so in an Authorization Response. |  [optional] |
|**crdhldrVrfctnCpblty** | [**List&lt;MsginquiryinitiationCardholderVerificationCapabilities1&gt;**](MsginquiryinitiationCardholderVerificationCapabilities1.md) | Cardholder verification capabilities performing the transaction at the point of service. ISO 8583:93 bit 22-2, ISO 8583:2003 bit 27-2 |  [optional] |



