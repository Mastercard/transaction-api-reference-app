# TransactionApiApi

All URIs are relative to *https://mtf.services-asn.mastercard.com/transaction-api*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**transactionApiProcessAuthorisationRequest**](TransactionApiApi.md#transactionApiProcessAuthorisationRequest) | **POST** /cain-authorisation-requests | Authorisation request |
| [**transactionApiProcessInquriyRequest**](TransactionApiApi.md#transactionApiProcessInquriyRequest) | **POST** /cain-inquiry-requests | Inquiry request |
| [**transactionApiProcessReversalRequest**](TransactionApiApi.md#transactionApiProcessReversalRequest) | **POST** /cain-reversal-requests | Reversal request |


<a name="transactionApiProcessAuthorisationRequest"></a>
# **transactionApiProcessAuthorisationRequest**
> ResponseAuthorisationResponseV02 transactionApiProcessAuthorisationRequest(initiationAuthorisationInitiationV02)

Authorisation request

Process the authorisation requests

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TransactionApiApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://mtf.services-asn.mastercard.com/transaction-api");

    TransactionApiApi apiInstance = new TransactionApiApi(defaultClient);
    InitiationAuthorisationInitiationV02 initiationAuthorisationInitiationV02 = new InitiationAuthorisationInitiationV02(); // InitiationAuthorisationInitiationV02 | AuthorisationInitiationV02 request
    try {
      ResponseAuthorisationResponseV02 result = apiInstance.transactionApiProcessAuthorisationRequest(initiationAuthorisationInitiationV02);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TransactionApiApi#transactionApiProcessAuthorisationRequest");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **initiationAuthorisationInitiationV02** | [**InitiationAuthorisationInitiationV02**](InitiationAuthorisationInitiationV02.md)| AuthorisationInitiationV02 request | |

### Return type

[**ResponseAuthorisationResponseV02**](ResponseAuthorisationResponseV02.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A successful response. |  -  |
| **400** | INVALID_FIELD |  -  |

<a name="transactionApiProcessInquriyRequest"></a>
# **transactionApiProcessInquriyRequest**
> ResponseInquiryResponseV01 transactionApiProcessInquriyRequest(initiationInquiryInitiationV01)

Inquiry request

Process the inquiries request

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TransactionApiApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://mtf.services-asn.mastercard.com/transaction-api");

    TransactionApiApi apiInstance = new TransactionApiApi(defaultClient);
    InitiationInquiryInitiationV01 initiationInquiryInitiationV01 = new InitiationInquiryInitiationV01(); // InitiationInquiryInitiationV01 | InquiryInitiationV01 request
    try {
      ResponseInquiryResponseV01 result = apiInstance.transactionApiProcessInquriyRequest(initiationInquiryInitiationV01);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TransactionApiApi#transactionApiProcessInquriyRequest");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **initiationInquiryInitiationV01** | [**InitiationInquiryInitiationV01**](InitiationInquiryInitiationV01.md)| InquiryInitiationV01 request | |

### Return type

[**ResponseInquiryResponseV01**](ResponseInquiryResponseV01.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A successful response. |  -  |
| **400** | INVALID_FIELD |  -  |

<a name="transactionApiProcessReversalRequest"></a>
# **transactionApiProcessReversalRequest**
> ResponseReversalResponseV02 transactionApiProcessReversalRequest(initiationReversalInitiationV02)

Reversal request

Process the reversal requests

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TransactionApiApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://mtf.services-asn.mastercard.com/transaction-api");

    TransactionApiApi apiInstance = new TransactionApiApi(defaultClient);
    InitiationReversalInitiationV02 initiationReversalInitiationV02 = new InitiationReversalInitiationV02(); // InitiationReversalInitiationV02 | ReversalInitiationV02 request
    try {
      ResponseReversalResponseV02 result = apiInstance.transactionApiProcessReversalRequest(initiationReversalInitiationV02);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TransactionApiApi#transactionApiProcessReversalRequest");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **initiationReversalInitiationV02** | [**InitiationReversalInitiationV02**](InitiationReversalInitiationV02.md)| ReversalInitiationV02 request | |

### Return type

[**ResponseReversalResponseV02**](ResponseReversalResponseV02.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A successful response. |  -  |
| **400** | INVALID_FIELD |  -  |

