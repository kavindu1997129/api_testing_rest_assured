package apitest.publisher;

import java.nio.file.Files;
import java.nio.file.Paths;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Apis {

    String accessToken;
    String endPoint;

    Response searchApisResponse;
    Response createApiResponse;

    byte[] apiCreationPayloadJson;
    String apiCreationPayloadString;

    public Apis(String accessToken, String endPoint){
        this.accessToken = accessToken;
        this.endPoint = endPoint;
    }

    public Response searchApis(){
        searchApisResponse = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(accessToken)
				.get(endPoint);
        
        return searchApisResponse;
    }

    public Response createApi(String contentType, String jsonPayloadPath){

        try {
            apiCreationPayloadJson = Files.readAllBytes(Paths.get(jsonPayloadPath));
		    apiCreationPayloadString = new String(apiCreationPayloadJson);

        createApiResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .body(apiCreationPayloadString)
            .contentType(contentType)
            .post(endPoint);
        } catch (Exception e) {
            
        }

        return createApiResponse;

    } 
    
}
