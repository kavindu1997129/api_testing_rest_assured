package apitest.publisher;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PublisherApis {

    String accessToken;
    String endPoint;

    Response searchApisResponse;
    Response createApiResponse;
    Response uploadThumbnailImageResponse;
    Response getApiDetailsResponse;
    Response createNewApiVersiResponse;
    Response updateApiResponse;

    byte[] apiCreationPayloadJson;
    String apiCreationPayloadString;

    byte[] createapiproductplj;
    String createapiproductpls;

    byte[] updateApiPayloadJson;
    String updateApiPayloadString;

    


    public PublisherApis(String accessToken, String endPoint){
        this.accessToken = accessToken;
        this.endPoint = endPoint;
    }

    public Response searchApis(){
        try {
            searchApisResponse = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(accessToken)
				.get(endPoint);
            
        } catch (Exception e) {

        }
        
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

    public Response uploadThumbnailImage(String imagePath,  String apiId){
        uploadThumbnailImageResponse= RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(accessToken)
				.multiPart(new File(imagePath))
				.put(endPoint+"/"+apiId+"/thumbnail");

        return uploadThumbnailImageResponse;
    }

    public Response getApiDetails(String apiId){
        getApiDetailsResponse = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(accessToken)
				.get(endPoint+"/"+apiId);

        return getApiDetailsResponse;
    }

    public Response createNewApiVersion(String apiId, String apiVersion, boolean defaultVersion){
        
        
        createNewApiVersiResponse = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(accessToken)
				.post(endPoint+"/copy-api?newVersion="+apiVersion+"&defaultVersion="+defaultVersion+"&apiId="+apiId);
        return createNewApiVersiResponse;

    }

    public Response updateApi(String contentType, String apiId, String jsonPayloadPath){

        try {
            updateApiPayloadJson = Files.readAllBytes(Paths.get(jsonPayloadPath));
		    updateApiPayloadString = new String(updateApiPayloadJson);

            updateApiResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .body(updateApiPayloadString)
            .contentType(contentType)
            .put(endPoint+"/"+apiId);

        } catch (Exception e) {
        }
        
        return updateApiResponse;

    }


    
}
