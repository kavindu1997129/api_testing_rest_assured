package tests;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Oauth2Test {
	
//	@Test
//	public void test1() {
//		
//		Response response = RestAssured.
//							given().
//							auth().
//							oauth2("a9ba19312cac88119ee086ab84130fbefdd0f615").
//							post("http://coop.apps.symfonycasts.com/api/3714/chickens-feed");
//		
//		System.out.println("Response :" +response);
//	}
//	
	@Test
	public void test2() {
		
		Response response2 = RestAssured.given().
				formParam("client_id", "CyPressApp_kavindu").
				formParam("client_secret", "9e77e471afcb28ccc9457ce8221a99e3").
				formParam("grant_type","client_credentials").
				post("http://coop.apps.symfonycasts.com/token");
		
				System.out.println(response2.jsonPath().prettify());
	}

}
