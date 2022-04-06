# TransactionApiApi

All URIs are relative to *https://api.mastercard.com/transaction-api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**initiateAuthorisation**](TransactionApiApi.md#initiateAuthorisation) | **POST** /cain-authorisation-requests | Send an ISO20022 initiation authorisation request


<a name="initiateAuthorisation"></a>
# **initiateAuthorisation**
> AuthorisationResponseV02 initiateAuthorisation(authorisationInitiationV02)

Send an ISO20022 initiation authorisation request

Endpoint to send an ISO20022 initiation authorisation request to

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
    defaultClient.setBasePath("https://api.mastercard.com/transaction-api");

    TransactionApiApi apiInstance = new TransactionApiApi(defaultClient);
    AuthorisationInitiationV02 authorisationInitiationV02 = new AuthorisationInitiationV02(); // AuthorisationInitiationV02 | A JSON object containing AuthorisationInitiationV02 Information
    try {
      AuthorisationResponseV02 result = apiInstance.initiateAuthorisation(authorisationInitiationV02);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TransactionApiApi#initiateAuthorisation");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorisationInitiationV02** | [**AuthorisationInitiationV02**](AuthorisationInitiationV02.md)| A JSON object containing AuthorisationInitiationV02 Information |

### Return type

[**AuthorisationResponseV02**](AuthorisationResponseV02.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful Authorisation responses |  -  |

