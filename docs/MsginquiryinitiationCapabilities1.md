

# MsginquiryinitiationCapabilities1

Capabilities of the terminal.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**cardRdngCpblty** | [**List&lt;MsginquiryinitiationCardDataReading7Code&gt;**](MsginquiryinitiationCardDataReading7Code.md) |  |  [optional]
**othrCardRdngCpblties** | **List&lt;String&gt;** | Other types of card reading capabilities. |  [optional]
**cardWrtgCpblties** | [**List&lt;MsginquiryinitiationCardDataWriting1Code&gt;**](MsginquiryinitiationCardDataWriting1Code.md) | Card writing or output capabilities of the terminal performing the transaction. ISO 8583:93 bit 22-10, ISO 8583:2003 bit 27-8_9. |  [optional]
**othrCardWrtgCpblties** | **List&lt;String&gt;** | Other card writing or output capabilities of the terminal performing the transaction. |  [optional]
**pINLngthCpblties** | **Double** | Maximum number of digits that the Point of Interaction is able to accept when the cardholder enters its PIN.  ISO 8583:87 bit 26, ISO 8583:93 bit 22-12, ISO 8583:2003 bit 27-11. |  [optional]
**pINPadInprtv** | **Boolean** | PIN pad is inoperative.  Default: False - PIN pad is operative or not applicable. True: PIN pas is inoperative. |  [optional]
**cardCaptrCpbl** | **Boolean** | Indicates whether the terminal can capture cards or not. True: The terminal is able to capture cards False: The terminal is not able to capture cards.  ISO 8583:87 bit 25, ISO 8583:93 bit 22-3, ISO 8583:2003 bit 27-10. |  [optional]
**crdhldrVrfctnCpblty** | [**List&lt;MsginquiryinitiationCardholderVerificationCapabilities1&gt;**](MsginquiryinitiationCardholderVerificationCapabilities1.md) |  |  [optional]



