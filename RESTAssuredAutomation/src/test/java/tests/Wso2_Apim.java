package tests;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Wso2_Apim {
	
	Response  res;
	Response  res2;
	Response  res3;
	Response  res4;
	Response  res5;
	Response  res6;
	
	
	@Test
	public void oauth2() {
		
		res = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.preemptive()
				.basic("admin", "admin")   
				.body("{\"callbackUrl\":\"www.google.lk\",\"clientName\":\"rest_api_publisher\",\"owner\":\"admin\",\"grantType\":\"client_credentialspasswordrefresh_token\",\"saasApp\":true}")
				.contentType("application/json")
				.post("https://localhost:9443/client-registration/v0.17/register");
		
		System.out.println(res.jsonPath().prettify());
		
		res2 = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.basic(res.jsonPath().get("clientId").toString(), res.jsonPath().get("clientSecret").toString())  
				.queryParam("grant_type","password")
				.queryParam("username","admin")
				.queryParam("password","admin")
				.queryParam("scope","apim:api_view apim:api_create")
				.post("https://localhost:8243/token");
		
		System.out.println(res2.statusCode());
		System.out.println(res2.jsonPath().prettify());
		
		
		
		res3 = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(res2.jsonPath().get("access_token").toString())
				.body("{\"id\":\"01234567-0123-0123-0123-012345678901\",\"name\":\"PizzaShackAPI\",\"description\":\"ThisisasimpleAPIforPizzaShackonlinepizzadeliverystore.\",\"context\":\"pizza\",\"version\":\"1.0.0\",\"provider\":\"admin\",\"lifeCycleStatus\":\"CREATED\",\"wsdlInfo\":{\"type\":\"WSDL\"},\"wsdlUrl\":\"/apimgt/applicationdata/wsdls/admin--soap1.wsdl\",\"testKey\":\"8swdwj9080edejhj\",\"responseCachingEnabled\":true,\"cacheTimeout\":300,\"destinationStatsEnabled\":\"Disabled\",\"hasThumbnail\":false,\"isDefaultVersion\":false,\"enableSchemaValidation\":false,\"enableStore\":true,\"type\":\"HTTP\",\"transport\":[\"http\",\"https\"],\"tags\":[\"pizza\",\"food\"],\"policies\":[\"Unlimited\"],\"apiThrottlingPolicy\":\"Unlimited\",\"authorizationHeader\":\"Authorization\",\"securityScheme\":[\"oauth2\"],\"maxTps\":{\"production\":1000,\"sandbox\":1000},\"visibility\":\"PUBLIC\",\"visibleRoles\":[],\"visibleTenants\":[],\"endpointSecurity\":{\"type\":\"BASIC\",\"username\":\"admin\",\"password\":\"password\"},\"gatewayEnvironments\":[\"ProductionandSandbox\"],\"deploymentEnvironments\":[{\"type\":\"Kubernetes\",\"clusterName\":[\"minikube\"]}],\"labels\":[],\"mediationPolicies\":[{\"name\":\"json_to_xml_in_message\",\"type\":\"in\"},{\"name\":\"xml_to_json_out_message\",\"type\":\"out\"},{\"name\":\"json_fault\",\"type\":\"fault\"}],\"subscriptionAvailability\":\"CURRENT_TENANT\",\"subscriptionAvailableTenants\":[],\"additionalProperties\":{\"property1\":\"string\",\"property2\":\"string\"},\"monetization\":{\"enabled\":true,\"properties\":{\"property1\":\"string\",\"property2\":\"string\"}},\"accessControl\":\"NONE\",\"accessControlRoles\":[],\"businessInformation\":{\"businessOwner\":\"businessowner\",\"businessOwnerEmail\":\"businessowner@wso2.com\",\"technicalOwner\":\"technicalowner\",\"technicalOwnerEmail\":\"technicalowner@wso2.com\"},\"corsConfiguration\":{\"corsConfigurationEnabled\":false,\"accessControlAllowOrigins\":[\"string\"],\"accessControlAllowCredentials\":false,\"accessControlAllowHeaders\":[\"string\"],\"accessControlAllowMethods\":[\"string\"]},\"workflowStatus\":\"APPROVED\",\"createdTime\":\"2017-02-20T13:57:16.229\",\"lastUpdatedTime\":\"2017-02-20T13:57:16.229\",\"endpointConfig\":{\"endpoint_type\":\"http\",\"sandbox_endpoints\":{\"url\":\"https://localhost:9443/am/sample/pizzashack/v1/api/\"},\"production_endpoints\":{\"url\":\"https://localhost:9443/am/sample/pizzashack/v1/api/\"},\"endpoint_security\":{\"sandbox\":{\"password\":null,\"tokenUrl\":\"http://localhost:9443/token\",\"clientId\":\"cid123\",\"clientSecret\":\"cs123\",\"customParameters\":{},\"type\":\"OAUTH\",\"grantType\":\"CLIENT_CREDENTIALS\",\"enabled\":true,\"username\":null},\"production\":{\"password\":null,\"tokenUrl\":\"http://localhost:9443/token\",\"clientId\":\"cid123\",\"clientSecret\":\"cs123\",\"customParameters\":{},\"type\":\"OAUTH\",\"grantType\":\"CLIENT_CREDENTIALS\",\"enabled\":true,\"username\":null}}},\"endpointImplementationType\":\"INLINE\",\"scopes\":[{\"scope\":{\"id\":\"01234567-0123-0123-0123-012345678901\",\"name\":\"apim:api_view\",\"displayName\":\"api_view\",\"description\":\"ThisScopecanusedtoviewApis\",\"bindings\":[\"admin\",\"Internal/creator\",\"Internal/publisher\"],\"usageCount\":3},\"shared\":true}],\"operations\":[{\"target\":\"/order/{orderId}\",\"verb\":\"POST\",\"authType\":\"Application&ApplicationUser\",\"throttlingPolicy\":\"Unlimited\"},{\"target\":\"/menu\",\"verb\":\"GET\",\"authType\":\"Application&ApplicationUser\",\"throttlingPolicy\":\"Unlimited\"}],\"threatProtectionPolicies\":{\"list\":[{\"policyId\":\"string\",\"priority\":0}]},\"categories\":[],\"keyManagers\":[]}")
				.contentType("application/json")
				.post("https://localhost:9443/api/am/publisher/v1/apis");
		
		System.out.println(res3.jsonPath().prettify());
		
		res4 = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(res2.jsonPath().get("access_token").toString())
				.get("https://127.0.0.1:9443/api/am/publisher/v1/apis");
		
		System.out.println(res4.jsonPath().prettyPrint());
		
			
	}
	
	@Test
	public void oauth2_test2() {
		
		res5 = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(res2.jsonPath().get("access_token").toString())
				.get("https://127.0.0.1:9443/api/am/publisher/v1/apis/"+res4.jsonPath().get("list[0]['id']"));
		
		System.out.println(res5.jsonPath().prettyPrint());
	}
	
	@Test
	public void oauth2_test3() {
		
		res5 = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(res2.jsonPath().get("access_token").toString())
				.get("https://127.0.0.1:9443/api/am/publisher/v1/apis/"+res4.jsonPath().get("list[0]['id']"));
		
		System.out.println(res5.jsonPath().prettyPrint());
	}

}
