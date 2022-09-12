package apitest.publisher;

import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PublisherApiProducts {

    String endPoint;
    String accessToken;

    byte[] createApiProductPayloadJson;
    String createApiProductPayloadString;
    Response createApiProductResponse;

    public PublisherApiProducts(String endPoint,String accessToken){
        this.accessToken = accessToken;
        this.endPoint = endPoint;
    }

    public Response searchApiProduct(){
        Response searchApiProductResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .get(endPoint); 

        return searchApiProductResponse;
    }

    public Response createApiProduct(String contentType, String jsonPayloadPath){

        try {
            createApiProductPayloadJson = Files.readAllBytes(Paths.get(jsonPayloadPath));
		    createApiProductPayloadString = new String(createApiProductPayloadJson);

            createApiProductResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .body(createApiProductPayloadString)
            .contentType(contentType)
            .post(endPoint);
        } catch (Exception e) {
            
        }

        return createApiProductResponse;

    } 

    public Response getDetailsOfApiProduct(String apiProductId){
        Response getDetailsOfApiProductResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType("application/json")
        .get(endPoint+"/"+apiProductId);

        System.out.println(endPoint+"/"+apiProductId);

        return getDetailsOfApiProductResponse;
    }

    public Response getSwaggerDefinition(String apiId){
        Response getSwaggerDefinitionResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .get(endPoint+"/"+"swagger"); 

        return getSwaggerDefinitionResponse;
    }
    
}
