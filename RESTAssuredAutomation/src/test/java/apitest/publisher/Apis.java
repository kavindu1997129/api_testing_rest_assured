package apitest.publisher;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Apis {

    String accessToken;
    String endPoint;

    Response searchApiResponse;

    public Apis(String accessToken, String endPoint){
        this.accessToken = accessToken;
        this.endPoint = endPoint+"/";
    }

    public String searchApis(String path){
        searchApiResponse = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(accessToken)
				.get(endPoint+path);
        
        return searchApiResponse.jsonPath().prettyPrint();
    }
    
}
