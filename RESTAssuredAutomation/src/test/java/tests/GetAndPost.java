package tests;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class GetAndPost {

	public void testGet() {
		
		baseURI = "https://reqres.in/api";
		given().get("/users?page=2").then().statusCode(200).body("data[4].first_name", equalTo("George")).body("data.first_name", equalTo("George"));
	}
	
	
	
	
}
