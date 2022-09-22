package tests;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ataf.actions.BaseTest;
import io.restassured.RestAssured;
import restapi.Authentication;
import restapi.AuthenticationObject;
import restapi.ContentTypes;
import restapi.GrantTypes;
import restapi.Scopes;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;


public class CreateAPIs extends BaseTest{
	String accessToken;
	int apiCount;
	
	@BeforeTest
	@Parameters({"baseURL","apiCount"})	
	public void initTest(String baseurlParm, int apiCount) throws URISyntaxException {
		bindBaseURL(baseurlParm);
        AuthenticationObject authenticationObject = new AuthenticationObject();
        authenticationObject.setUsername("admin");
        authenticationObject.setUserpassword("admin");
        authenticationObject.setEndpoint(baseURL.resolve("client-registration/v0.17/register").toString());
        authenticationObject.setTokenUrl(baseURL.resolve("oauth2/token").toString());
        authenticationObject.setPayloadPath("./src/test/payloads/payload.json");
        authenticationObject.setScopes(Scopes.API_PUBLISH, Scopes.API_CREATE, Scopes.API_VIEW, Scopes.API_IMPORT_EXPORT);
        authenticationObject.setContentType(ContentTypes.APPLICATION_JSON);
        authenticationObject.setGrantType(GrantTypes.PASSSWORD);
        
        Authentication authentication = new Authentication(authenticationObject);
        accessToken = authentication.getAccessToken();
        System.out.println(accessToken);
        
        this.apiCount = apiCount;
	}
	
	@Test
	public void dataGeneration() {
		System.out.println(this.apiCount);
		for(int i=1 ; i<=apiCount ; i++) {
	        Response createApiResponse  = RestAssured.given()
	                .relaxedHTTPSValidation()
	                .auth()
	                .oauth2(accessToken)
	                .body(getPayload(i))
	                .contentType("application/json")
	                .post("https://localhost:9443/api/am/publisher/v3/apis");
	        
	        JsonPath jsonPathEvaluator = createApiResponse.jsonPath();
			String apiID = jsonPathEvaluator.get("id");
	        System.out.println("API " + i + " : "+ createApiResponse.statusCode() + " | " + apiID);
	        //System.out.println(createApiResponse.body().prettyPrint());
		}

	}
	
	
  static String getPayload(int apiIndex) {

  byte[] payloadJson1;
  String payloadString1;
  String payload="";

  try {
      payloadJson1 = Files.readAllBytes(Paths.get("./src/test/payloads/apicretion_payload.json"));
      payload = new String(payloadJson1);
  } catch (Exception e) {
  }

// 	  String payload = """"
// 	  		{
//   "name": "PizzaShackAPI_%s",
//   "description": "This is a simple API for Pizza Shack online pizza delivery store.",
//   "context": "pizza_%s",
//   "version": "1.0.0",
//   "provider": "admin",
//   "lifeCycleStatus": "CREATED",
//   "wsdlInfo": {
//     "type": "WSDL"
//   },
//   "responseCachingEnabled": true,
//   "cacheTimeout": 300,
//   "hasThumbnail": false,
//   "isDefaultVersion": false,
//   "isRevision": false,
//   "revisionId": 1,
//   "enableSchemaValidation": false,
//   "type": "HTTP",
//   "audience": "PUBLIC",
//   "transport": [
//     "http",
//     "https"
//   ],
//   "tags": [
//     "pizza",
//     "food"
//   ],
//   "policies": [
//     "Unlimited"
//   ],
//   "apiThrottlingPolicy": "Unlimited",
//   "authorizationHeader": "Authorization",
//   "securityScheme": [
//     "oauth2"
//   ],
//   "maxTps": {
//     "production": 1000,
//     "sandbox": 1000
//   },
//   "visibility": "PUBLIC",
//   "visibleRoles": [],
//   "visibleTenants": [],
//   "mediationPolicies": [
//     {
//       "name": "json_to_xml_in_message",
//       "type": "in"
//     },
//     {
//       "name": "xml_to_json_out_message",
//       "type": "out"
//     },
//     {
//       "name": "json_fault",
//       "type": "fault"
//     }
//   ],
//   "subscriptionAvailability": "CURRENT_TENANT",
//   "subscriptionAvailableTenants": [],
//   "additionalProperties": [
//     {
//       "name": "string",
//       "value": "string",
//       "display": true
//     }
//   ],
//   "additionalPropertiesMap": {
//     "property1": {
//       "name": "string",
//       "value": "string",
//       "display": false
//     },
//     "property2": {
//       "name": "string",
//       "value": "string",
//       "display": false
//     }
//   },
//   "monetization": {
//     "enabled": true,
//     "properties": {
//       "property1": "string",
//       "property2": "string"
//     }
//   },
//   "accessControl": "NONE",
//   "accessControlRoles": [],
//   "businessInformation": {
//     "businessOwner": "businessowner",
//     "businessOwnerEmail": "businessowner@wso2.com",
//     "technicalOwner": "technicalowner",
//     "technicalOwnerEmail": "technicalowner@wso2.com"
//   },
//   "corsConfiguration": {
//     "corsConfigurationEnabled": false,
//     "accessControlAllowOrigins": [
//       "string"
//     ],
//     "accessControlAllowCredentials": false,
//     "accessControlAllowHeaders": [
//       "string"
//     ],
//     "accessControlAllowMethods": [
//       "string"
//     ]
//   },
//   "websubSubscriptionConfiguration": {
//     "enable": false,
//     "secret": "string",
//     "signingAlgorithm": "string",
//     "signatureHeader": "string"
//   },
//   "workflowStatus": "APPROVED",
//   "createdTime": "string",
//   "lastUpdatedTime": "string",
//   "endpointConfig": {
//     "endpoint_type": "http",
//     "sandbox_endpoints": {
//       "url": "https://localhost:9443/am/sample/pizzashack/v3/api/"
//     },
//     "production_endpoints": {
//       "url": "https://localhost:9443/am/sample/pizzashack/v3/api/"
//     }
//   },
//   "endpointImplementationType": "INLINE",
//   "scopes": [
//     {
//       "scope": {
//         "name": "apim:api_view",
//         "displayName": "api_view",
//         "description": "This Scope can used to view Apis",
//         "bindings": [
//           "admin",
//           "Internal/creator",
//           "Internal/publisher"
//         ]
//       },
//       "shared": true
//     }
//   ],
//   "operations": [
//     {
//       "target": "/order/{orderId}",
//       "verb": "POST",
//       "authType": "Application & Application User",
//       "throttlingPolicy": "Unlimited"
//     },
//     {
//       "target": "/menu",
//       "verb": "GET",
//       "authType": "Application & Application User",
//       "throttlingPolicy": "Unlimited"
//     }
//   ],
//   "threatProtectionPolicies": {
//     "list": [
//       {
//         "policyId": "string",
//         "priority": 0
//       }
//     ]
//   },
//   "categories": [],
//   "serviceInfo": {
//     "key": "PetStore-1.0.0",
//     "name": "PetStore",
//     "version": "1.0.0",
//     "outdated": false
//   },
//   "advertiseInfo": {
//     "advertised": true,
//     "apiExternalProductionEndpoint": "https://localhost:9443/devportal",
//     "apiExternalSandboxEndpoint": "https://localhost:9443/devportal",
//     "originalDevPortalUrl": "https://localhost:9443/devportal",
//     "apiOwner": "admin",
//     "vendor": "WSO2"
//   },
//   "gatewayVendor": "wso2",
//   "gatewayType": "wso2/synapse",
//   "asyncTransportProtocols": [
//     "http",
//     "https"
//   ]
// }
//  """;
	  
	  return String.format(payload, apiIndex,apiIndex);
	  
	}
}
