package apitest;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Authentication {
    Response  res1, res2, res3, res4, res5, res6, res7, res8, res9, res10;

	FileInputStream input;
	Properties p;
	byte[] authplj;
	String authpls;
    String accessToken;

    String username = "";
    String userpassword = "";
    String endpoint = "";
    String payloadPath = "";
    String tokenUrl = "";

    

    public Authentication(String username, String userpassword, String endpoint, String tokenUrl, String payloadPath) {
        this.username = username;
        this.userpassword = userpassword;
        this.endpoint = endpoint;
        this.payloadPath = payloadPath;
        this.tokenUrl = tokenUrl;
    }

    public String getAccessToken(){
        try {
			authplj = Files.readAllBytes(Paths.get(payloadPath));
			authpls = new String(authplj);
            res1 = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.preemptive()
				.basic(username,userpassword)
				.body(authpls)
				.contentType("application/json")
				.post(endpoint
                );
		
		    res2 = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.basic(res1.jsonPath().get("clientId").toString(), res1.jsonPath().get("clientSecret").toString())  
				.queryParam("grant_type","password")
				.queryParam("username",username)
				.queryParam("password",userpassword)
				.queryParam("scope","apim:api_view apim:api_create")
				.post(tokenUrl);
	
		    accessToken = res2.jsonPath().get("access_token").toString();

		} 
        catch (Exception e) {
			System.out.println(e);
		}
        return accessToken;
		
    }

}
