package apitest.publisher;

import java.io.File;
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

    public Response deleteApiProduct(String apiId){
        Response deleteApiProductResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .delete(endPoint+"/"+apiId);

        return deleteApiProductResponse;
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

    public Response getSwaggerDefinition(String apiPproductId){
        Response getSwaggerDefinitionResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .get(endPoint+"/"+apiPproductId+"/swagger"); 

        return getSwaggerDefinitionResponse;
    }

    public Response updateApiProduct(String apiProductId){

        Response updateApiProductResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .put(endPoint+"/"+apiProductId); 

        return updateApiProductResponse;
    }

    public Response getProductThumbnail(String apiProductId){

        Response getProductThumbnailResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .get(endPoint+"/"+apiProductId+"/thumbnail"); 

        return getProductThumbnailResponse;
    }

    public Response uploadProductThumbnail(String imagePath,  String apiProductId){
        Response uploadProductThumbnailResponse= RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(accessToken)
				.multiPart(new File(imagePath))
				.put(endPoint+"/"+apiProductId+"/thumbnail");

        return uploadProductThumbnailResponse;
    }

    public Response isApiProductOutdated(String apiProductId){
        Response isApiProductOutdatedResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .get(endPoint+"/"+apiProductId+"/is-outdated"); 

        return isApiProductOutdatedResponse;
    }
    
}
