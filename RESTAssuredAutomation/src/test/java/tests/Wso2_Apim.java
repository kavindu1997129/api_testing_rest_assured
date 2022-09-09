package tests;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Wso2_Apim {
	
	Response  res1, res2, res3, res4, res5, res6, res7, res8, res9, res10;

	FileInputStream input;
	Properties p;
	byte[] authplj;
	byte[] apicreationplj;
	byte[] createapiproductplj;
	String authpls;
	String apicreationpls;
	String createapiproductpls;


	String accessToken;

	
	@Test
	public void oauth2() {

		try {
			String path =  "./src/test/resources/config.properties";
			p = new Properties();
			input = new FileInputStream(path);
			p.load(input);
			
			authplj = Files.readAllBytes(Paths.get("./src/test/payloads/payload.json"));
			authpls = new String(authplj);
			apicreationplj = Files.readAllBytes(Paths.get("./src/test/payloads/apicretion_payload.json"));
			apicreationpls = new String(apicreationplj);
			createapiproductplj = Files.readAllBytes(Paths.get("./src/test/payloads/creat_api_pprodt.json"));
			createapiproductpls = new String(createapiproductplj);
			

		} catch (Exception e) {
			System.out.println(e);
		}
		
		res1 = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.preemptive()
				.basic(p.getProperty("adminusername"),p.getProperty("adminpassword"))
				.body(authpls)
				.contentType("application/json")
				.post(p.getProperty("hosturi")+"9443/client-registration/v0.17/register");
		
		///System.out.println(res1.jsonPath().prettify());
		
		res2 = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.basic(res1.jsonPath().get("clientId").toString(), res1.jsonPath().get("clientSecret").toString())  
				.queryParam("grant_type","password")
				.queryParam("username",p.getProperty("adminusername"))
				.queryParam("password","admin")
				.queryParam("scope","apim:api_view apim:api_create")
				.post(p.getProperty("hosturi")+"8243/token");
	
		accessToken = res2.jsonPath().get("access_token").toString();

		res3 = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(accessToken)
				.body(apicreationpls)
				.contentType("application/json")
				.post(p.getProperty("publisher_url"));

		System.out.println("Api Creeation: " + res3.jsonPath().prettyPrint());
		
		res4 = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(accessToken)
				.get(p.getProperty("publisher_url"));
		
		System.out.println(res4.jsonPath().prettyPrint());
			
	}
	
	@Test
	public void oauth2_test2() {
		
		res5 = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(accessToken)
				.get(p.getProperty("publisher_url")+"/"+res4.jsonPath().get("list[0]['id']"));
		
		System.out.println(res5.jsonPath().prettyPrint());
	}
	
	@Test
	public void oauth2_test3() {
		
		res6 = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(accessToken)
				.get(p.getProperty("publisher_url")+"/"+res4.jsonPath().get("list[0]['id']"));
	
	}

	@Test
	public void thumbnail_adding() {

		res7 = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(accessToken)
				.multiPart(new File("./src/test/payloads/thumbnail.jpg"))
				.put(p.getProperty("publisher_url")+"/"+res4.jsonPath().get("list[0]['id']")+"/thumbnail");

		System.out.println(res7.jsonPath().prettyPrint());

		res8= RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(accessToken)
				.contentType("application/json")
				.post(p.getProperty("publisher_url")+"/copy-api?newVersion=2.0.0&defaultVersion=false&apiId="+res4.jsonPath().get("list[0]['id']"));

		System.out.println(res8.jsonPath().prettyPrint());


		//-----------Example for create New API Product not working 
		// res9= RestAssured.given()
		// 		.relaxedHTTPSValidation()
		// 		.auth()
		// 		.oauth2(accessToken)
		// 		.body(createapiproductpls)
		// 		.contentType("application/json")
		// 		.post("https://127.0.0.1:9443/api/am/publisher/v1/api-products");

		// System.out.println(res9.jsonPath().prettyPrint());

		
	// "https://127.0.0.1:9443/api/am/publisher/v1/apis/change-lifecycle?apiId=890a4f4d-09eb-4877-a323-57f6ce2ed79b&action=Publish"

	// res10= RestAssured.given()
	// 			.relaxedHTTPSValidation()
	// 			.auth()
	// 			.oauth2(accessToken)
	// 			.contentType("application/json")
	// 			.post("https://localhost:9443/api/am/publisher/v1/apis/change-lifecycle?action=Publish&apiId=a3796a5f-7e2e-4b00-858f-4d859b8682f8");
	// 	System.out.println(res10.jsonPath().prettify());
	// RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}	

}
