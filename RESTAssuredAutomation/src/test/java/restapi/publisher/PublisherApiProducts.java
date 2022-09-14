package restapi.publisher;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import restapi.ContentTypes;

public class PublisherApiProducts {

    String endPoint;
    String accessToken;

    byte[] createApiProductPayloadJson;
    String createApiProductPayloadString;
    Response createApiProductResponse;

    byte[] apiProductUpdatePayloadJson;
    String apiProductUpdatePayloadString;

    String publisherApisProductString = "/api-products";

    public PublisherApiProducts(String accessToken){
        this.accessToken = accessToken;

        FileInputStream input;
	    Properties properties;

        try {
            String path =  "./src/test/resources/config.properties";
			properties = new Properties();
			input = new FileInputStream(path);
			properties.load(input);
            this.endPoint = properties.getProperty("base_url");
            
        } catch (Exception e) {
        }
    }

    public Response searchApiProduct(){
        Response searchApiProductResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .get(endPoint+publisherApisProductString); 

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
            .post(endPoint+publisherApisProductString);
        } catch (Exception e) {
            
        }

        return createApiProductResponse;

    } 

    public Response deleteApiProduct(String apiId){
        Response deleteApiProductResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .delete(endPoint+publisherApisProductString+"/"+apiId);

        return deleteApiProductResponse;
    }

    public Response getDetailsOfApiProduct(String apiProductId){
        Response getDetailsOfApiProductResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType("application/json")
        .get(endPoint+publisherApisProductString+"/"+apiProductId);

        return getDetailsOfApiProductResponse;
    }

    public Response getSwaggerDefinition(String apiPproductId){
        Response getSwaggerDefinitionResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .get(endPoint+publisherApisProductString+"/"+apiPproductId+"/swagger"); 

        return getSwaggerDefinitionResponse;
    }

    public Response updateApiProduct(String apiProductId, String contentType,  String jsonPayloadPath){

        try {
            apiProductUpdatePayloadJson = Files.readAllBytes(Paths.get(jsonPayloadPath));
		    apiProductUpdatePayloadString = new String(apiProductUpdatePayloadJson);
        } catch (Exception e) {
        }

        Response updateApiProductResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(contentType)
        .body(apiProductUpdatePayloadString)
        .put(endPoint+publisherApisProductString+"/"+apiProductId);

        return  updateApiProductResponse;

    }

    public Response getProductThumbnail(String apiProductId){

        Response getProductThumbnailResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .get(endPoint+publisherApisProductString+"/"+apiProductId+"/thumbnail"); 

        return getProductThumbnailResponse;
    }

    public Response uploadProductThumbnail(String imagePath,  String apiProductId){
        Response uploadProductThumbnailResponse= RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(accessToken)
                .contentType(ContentTypes.MULTIPART_FORMDATA)
				.multiPart(new File(imagePath))
				.put(endPoint+publisherApisProductString+"/"+apiProductId+"/thumbnail");

        return uploadProductThumbnailResponse;
    }

    public Response isApiProductOutdated(String apiProductId){
        Response isApiProductOutdatedResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .get(endPoint+publisherApisProductString+"/"+apiProductId+"/is-outdated"); 

        return isApiProductOutdatedResponse;
    }
    
    
}
