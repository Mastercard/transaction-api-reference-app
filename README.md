# Reference Implementation for Mastercard Transaction APIs for Acquirers

[![](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/Mastercard/transaction-api-reference/blob/master/LICENSE)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Mastercard_transaction-api-reference-app&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Mastercard_transaction-api-reference-app)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=Mastercard_transaction-api-reference-app&metric=bugs)](https://sonarcloud.io/summary/new_code?id=Mastercard_transaction-api-reference-app)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=Mastercard_transaction-api-reference-app&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=Mastercard_transaction-api-reference-app)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=Mastercard_transaction-api-reference-app&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=Mastercard_transaction-api-reference-app)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Mastercard_transaction-api-reference-app&metric=coverage)](https://sonarcloud.io/summary/new_code?id=Mastercard_transaction-api-reference-app)

## Table of Contents
- [Project Overview](#overview)
    * [Compatibility](#compatibility)
- [Usage](#usage)
    * [Prerequisites](#prerequisites)
    * [Security & Authentication](#references)
    * [Configuration](#configuration)
    * [Build and Execute](#build-and-execute)
- [Use Cases](#use-cases)
- [API Reference](#api-reference)
    * [Request Examples](#request-examples)
    * [Recommendation](#recommendation)
- [License](#license)
- [About This Project](#about-this-project)
  * [Integrating with OpenAPI Generator](#integrating-with-openapi-generator)
- [Support](#support)

## Project Overview <a name="overview"></a>
This is a reference application to demonstrate how the Mastercard Transaction API for Acquirers can be used for the supported operations. Please see here for details on the API: [Mastercard Developers](https://developer.mastercard.com/transaction-api-for-acquirers/documentation/). To call this API, a valid _Client Certificate_ file must be acquired and access provisioned as explained later in this documentation.

### Compatibility <a name="compatibility"></a>
* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or later

## Usage <a name="usage"></a>
### Prerequisites <a name="prerequisites"></a>
* [Mastercard Developers Account](https://developer.mastercard.com/dashboard) with access to the Mastercard Transaction API for Acquirers
* A text editor or IDE
* [Spring Boot 2.2+](https://spring.io/projects/spring-boot)
* [Apache Maven 3.3+](https://maven.apache.org/download.cgi)
* Set up the `JAVA_HOME` environment variable to match the location of your Java installation.

### Security and Authentication <a name="references"></a>
* [Using MTLS to access Mastercard APIs](https://developer.mastercard.com/platform/documentation/security-and-authentication/using-mtls-to-access-mastercard-apis/)

## Before You Start: Getting MTLS Client Certificates for Your Project
The client authentication side of the MTLS protocol involves a client certificate and a private key, known as a key pair. To use the key pair with Mastercard APIs, you will need to perform the onboarding steps detailed here.

### Client Certificates and Environments
There are two different types of MTLS client certificates, depending on the stage of your project:

1. Sandbox MTLS certificates, which give access to an API sandbox that mimics a live Production environment (*mtf.services.mastercard.com & *mtf.services-asn.mastercard.com/)
2. Production MLTS certificates, which allow an application to access the Production environment (*services.mastercard.com & *services-asn.mastercard.com)
*Domain/Server URL may vary depending on the API. Please check the API Reference section of the service documentation for the correct server URL.

### Creating and Renewing Client Certificate
The [Key Management Portal (KMP)](https://www.mastercardconnect.com/-/store-plus/item-details/A/ckmp) is an application available in [Mastercard Connect](https://www.mastercardconnect.com/). KMP is a self-service portal for Mastercard customers, which allows them to request and exchange keys and certificates with Mastercard.

The portal provides guided workflows to create and manage requests for key and certificate exchange, as well as an inventory of all PKI for Business Partners keys and certificates that have been exchanged between Mastercard and customers using KMP.

Access the Key Management Portal application on [Mastercard Connect](https://www.mastercardconnect.com/) to obtain MTLS client certificates. You can access the user guide within the KMP application for instructions on how to use the application.

#### Step-by-Step Guide on Requesting a Certificate
Visit the [Mastercard Transaction APIs for Acquirers Tutorials & Guides](https://developer.mastercard.com/transaction-api-for-acquirers/documentation/tutorial/tutorials-and-guides/) for a step-by-step guide on requesting an MTLS Certificate.

### Download the appropriate Client Certificate
Once you are notified that your Certificate Request is signed, you can access the client certificate in KMP.

When accessing your certificate, you will see an option to download the certificate. Ensure that the following options are selected:

1. Format PKCS #8
2. Uncheck “Include Root Chain”
The certificate will be available to download. Save it to a safe location so that it can be uploaded to your project in Mastercard Developers or used within your client's run command.

### Configuration <a name="configuration"></a>
With the PKCS12 file downloaded from [KMP](https://www.mastercardconnect.com/-/store-plus/item-details/A/ckmp) configure the properties for your client as explained below - 
* Open `${project.basedir}/src/main/resources/application.properties` and configure the below parameters.

  **The below properties will be required for authentication of API calls. You can modify them or add the appropriate values to the `run command` as environment variables**

  >**mastercard.api.key-file=**, this refers to the .p12 file found in the signing key. Please place .p12 file at src\main\resources\certs\ in the project folder and add classpath for .p12 file. 
  > If the certificate is not present, you will receive this error:
  > ```markdown
  > java.io.FileNotFoundException: src/main/resources/certs/certificate.p12 (No such file or directory)
  
  >**mastercard.api.format=**, for .p12 files this is "PKCS12" (without quotes)
  
  >**mastercard.api.keystore-password=**, this is the default value of the key alias. If it is modified, use what was identified when creating CSR on [KMP](https://www.mastercardconnect.com/-/store-plus/item-details/A/ckmp)
### Working with JKS instead of PKCS12
* If you received a different format of key file change the property **mastercard.api.format** accordingly. For example provide **mastercard.api.format=JKS** if you received the JKS file.
* Alternatively you can convert that to `.p12` using the following command (provide input where necessary).
```bash
keytool -importkeystore -srckeystore <jks file location> \
-destkeystore <pkcs12 file location> \
-srcstoretype JKS \
-deststoretype PKCS12 \
-deststorepass <keystore password>
```

### Build and Execute <a name="build-and-execute"></a>
Once you’ve added the correct properties, we can build the application. We can do this by navigating to the project’s base directory from the terminal and running the following command:

`mvn clean install`

When the project builds successfully you can then run the following command to start the project:

```bash
KEYFILE=my-keystore.p12 \
PASSWORD=my-keystore-password \
java -jar target/transaction-api-reference-1.0.0.jar
```
*** Note: the default environment is - `https://mtf.services-asn.mastercard.com` ***

See also:
* [OpenAPI Generator (Plugin for Maven)](https://mvnrepository.com/artifact/org.openapitools/openapi-generator-maven-plugin)
* [OpenAPI Generator (executable)](https://mvnrepository.com/artifact/org.openapitools/openapi-generator-cli)
* [CONFIG OPTIONS for Java](https://github.com/OpenAPITools/openapi-generator/blob/master/docs/generators/java.md)

## Use Cases <a name="use-cases"></a>
> Case 1: [Authorisation](https://developer.mastercard.com/transaction-api-for-acquirers/documentation/use-cases/acquirer-authorization/)
- The user performs an API call with an authorization request. Multiple use cases can be executed simultaneously.
- Refer to the model classes for field-level information.

  | URL     | Method    | Request |  Response |
  | --------|---------|-------|-------|
  | `/cain-authorisation-requests`  | POST   | [AuthorisationInitiationV02](docs/InitiationAuthorisationInitiationV02.md)    | [AuthorisationResponseV02](docs/ResponseAuthorisationResponseV02.md) |

> Case 2: [Reversal](https://developer.mastercard.com/transaction-api-for-acquirers/documentation/use-cases/acquirer-reversal/)
- The user performs an API call with an reversal request. Multiple use cases can be executed simultaneously.
- Refer to the model classes for field-level information.

  | URL     | Method    | Request |  Response |
  | --------|---------|-------|-------|
  | `/cain-reversal-requests` | POST | [ReversalInitiationV02](docs/InitiationReversalInitiationV02.md) | [ReversalResponseV02](docs/ResponseReversalResponseV02.md) |

## API Reference <a name="api-reference"></a>
To develop a client application that consumes a RESTful Transaction API with Spring Boot, refer to the [API Reference](https://developer.mastercard.com/transaction-api-for-acquirers/documentation/api-reference/) page.

### Request Examples <a name="request-examples"></a>
To learn which fields are required to make a request, refer to the example below.

| API Request Payload | Endpoint | HTTP Method | API Response Payload |
| --------|---------|-------|-------|
| [AuthorisationInitiationV02](https://developer.mastercard.com/transaction-api-for-acquirers/documentation/api-reference/acquirer-authorization/#request) | `/cain-authorisation-requests` | POST | [AuthorisationResponseV02](https://developer.mastercard.com/transaction-api-for-acquirers/documentation/api-reference/acquirer-authorization/#response). |


You can change the default input passed to APIs and modify values in the following files:
* `com.mastercard.developer.example.TransactionApiExample.java`

### Recommendation <a name="recommendation"></a>
It is recommended to create an instance of `ApiClient` per thread in a multi-threaded environment to avoid any potential issues.

## About this Project <a name="about-this-project"></a>
This project was created using the `OpenAPI Generator` for generating API client and SDK libraries, server stubs, documentation and configuration.

### Integrating with the OpenAPI Generator <a name="integrating-with-openapi-generator"></a>
[The OpenAPI Generator](https://github.com/OpenAPITools/openapi-generator) generates API client libraries from [OpenAPI Specs](https://github.com/OAI/OpenAPI-Specification).
It provides generators and library templates for supporting multiple languages and frameworks.

## Support <a name="support"></a>
If you would like further information, please send an email to apisupport@mastercard.com.

## License <a name="license"></a>
Copyright 2022 Mastercard

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at:

       http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.