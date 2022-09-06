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
	
	Response  res1;
	Response  res2;
	Response  res3;
	Response  res4;
	Response  res5;
	Response  res6;

	FileInputStream input;
	Properties p;
	byte[] authplj;
	byte[] apicreationplj;
	String authpls;
	String apicreationpls;

	
	@Test
	public void oauth2() {

		try {
			String path =  "./src/test/resources/config.properties";
			authplj = Files.readAllBytes(Paths.get("./src/test/java/tests/payload.json"));
			authpls = new String(authplj);
			apicreationplj = Files.readAllBytes(Paths.get("./src/test/java/tests/apicretioon_payload.json"));
			apicreationpls = new String(apicreationplj);
			p = new Properties();
			input = new FileInputStream(path);
			p.load(input);

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
		
		System.out.println(res1.jsonPath().prettify());
		
		res2 = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.basic(res1.jsonPath().get("clientId").toString(), res1.jsonPath().get("clientSecret").toString())  
				.queryParam("grant_type","password")
				.queryParam("username",p.getProperty("adminusername"))
				.queryParam("password","admin")
				.queryParam("scope","apim:api_view apim:api_create")
				.post(p.getProperty("hosturi")+"8243/token");
		
		System.out.println(res2.statusCode());
		System.out.println(res2.jsonPath().prettify());
		
		
		
		res3 = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(res2.jsonPath().get("access_token").toString())
				.body(apicreationpls)
				.contentType("application/json")
				.post(p.getProperty("publisher_url"));
		
		System.out.println(res3.jsonPath().prettify());
		
		res4 = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(res2.jsonPath().get("access_token").toString())
				.get(p.getProperty("publisher_url"));
		
		System.out.println(res4.jsonPath().prettyPrint());
			
	}
	
	@Test
	public void oauth2_test2() {
		
		res5 = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(res2.jsonPath().get("access_token").toString())
				.get(p.getProperty("publisher_url")+"/"+res4.jsonPath().get("list[0]['id']"));
		
		System.out.println(res5.jsonPath().prettyPrint());
	}
	
	@Test
	public void oauth2_test3() {
		
		res5 = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(res2.jsonPath().get("access_token").toString())
				.get(p.getProperty("publisher_url")+"/"+res4.jsonPath().get("list[0]['id']"));
		
		System.out.println(res5.jsonPath().prettyPrint());
	}

}
