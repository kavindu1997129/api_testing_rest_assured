package restapi.publisher;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import restapi.ApimVersions;
import restapi.ContentTypes;

public class PublisherApis {

    String accessToken="";
    String endPoint="";
    ApimVersions version;

    Response searchApisResponse;
    Response createApiResponse;
    Response uploadThumbnailImageResponse;
    Response getApiDetailsResponse;
    Response createNewApiVersiResponse;
    Response updateApiResponse;
    Response deleteApiResponse;

    byte[] apiCreationPayloadJson;
    String apiCreationPayloadString;

    byte[] createapiproductplj;
    String createapiproductpls;

    byte[] updateApiPayloadJson;
    String updateApiPayloadString;

    String publisherApisString = "/apis";
    String resourceParenPath = "./src/test/payloads/";

    public PublisherApis(String accessToken, ApimVersions version){
        this.accessToken = accessToken;
        this.version = version;

        FileInputStream input;
	    Properties properties;

        try {
            String path =  "./src/test/resources/config.properties";
			properties = new Properties();
			input = new FileInputStream(path);
			properties.load(input);
            if(version == ApimVersions.APIM_3_2){
                this.endPoint = properties.getProperty("base_url_3_2");
            }
            else{
                this.endPoint = properties.getProperty("base_url_4_1");
            }
            
        } catch (Exception e) {
        }
        
    }

    public Response searchApis(){
        try {
            searchApisResponse = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(accessToken)
				.get(endPoint+publisherApisString);    
        } catch (Exception e) {
        }
        
        return searchApisResponse;
    }

    public Response createApi(String contentType, String jsonPayloadPath){

        try {
            apiCreationPayloadJson = Files.readAllBytes(Paths.get(resourceParenPath+jsonPayloadPath));
		    apiCreationPayloadString = new String(apiCreationPayloadJson);

        createApiResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .body(apiCreationPayloadString)
            .contentType(contentType)
            .post(endPoint+publisherApisString);

        } catch (Exception e) {
            
        }

        return createApiResponse;

    } 

    public Response getApiDetails(String apiId){
        getApiDetailsResponse = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(accessToken)
				.get(endPoint+publisherApisString+"/"+apiId);

        return getApiDetailsResponse;
    }

    public Response createNewApiVersion(String apiId, String apiVersion, boolean defaultVersion){
        
        
        createNewApiVersiResponse = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(accessToken)
                .contentType("application/json")
				.post(endPoint+publisherApisString+"/copy-api?newVersion="+apiVersion+"&defaultVersion="+defaultVersion+"&apiId="+apiId);
        return createNewApiVersiResponse;

    }

    public Response updateApi(String contentType, String apiId, String jsonPayloadPath){

        try {
            updateApiPayloadJson = Files.readAllBytes(Paths.get(resourceParenPath+jsonPayloadPath));
		    updateApiPayloadString = new String(updateApiPayloadJson);

            updateApiResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .body(updateApiPayloadString)
            .contentType(contentType)
            .put(endPoint+publisherApisString+"/"+apiId);

        } catch (Exception e) {
        }
        
        return updateApiResponse;

    }

    public Response deleteApi(String apiId){
        deleteApiResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .delete(endPoint+publisherApisString+"/"+apiId);

        return deleteApiResponse;
    }

    public Response getSwaggerDefinition(){
        Response getSwaggerDefinitionResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .get(endPoint+publisherApisString);
        return getSwaggerDefinitionResponse;
    }


    public Response generateMockResponsePayloads(String apiId){
        Response generateMockResponsePayloadsResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType("application/json")
        .post(endPoint+publisherApisString+"/"+apiId+"/generate-mock-scripts");
        
        return generateMockResponsePayloadsResponse;
        
    }

    public Response getGeneratedMockResponsePayloads(String apiId){
        Response getGeneratedMockResponsePayloadsResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType("application/json")
        .get(endPoint+publisherApisString+"/"+apiId+"/generated-mock-scripts");
        
        return getGeneratedMockResponsePayloadsResponse;
        
    }

    public Response uploadThumbnailImage(String imagePath,  String apiId){
        Response uploadThumbnailImageResponse= RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(accessToken)
				.multiPart(new File(resourceParenPath+imagePath))
				.put(endPoint+publisherApisString+"/"+apiId+"/thumbnail");

        return uploadThumbnailImageResponse;
    }
    
    public Response getThumbnailImage(String apiId){
        Response getThumbnailImageResponse= RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(accessToken)
				.get(endPoint+publisherApisString+"/"+apiId+"/thumbnail");

        return getThumbnailImageResponse;
    }

    public Response getSubscriptionThrotlling(String apiId){
        Response getSubscriptionThrotllingResponse = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(accessToken)
				.get(endPoint+publisherApisString+"/"+apiId+"/subscription-policies");

        return getSubscriptionThrotllingResponse;
    }

    //API Lifecycle relaated methods
    public Response changeApiStatus(String apiId, String action){
        Response changeApiStatusResponse  = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(ContentTypes.APPLICATION_JSON)
        .post(endPoint+publisherApisString+"/change-lifecycle?apiId="+apiId+"&action="+action);

        return changeApiStatusResponse;
    }

    public Response getApiStatus(String apiId){
        Response changeApiStatusResponse  = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(ContentTypes.APPLICATION_JSON)
        .get(endPoint+publisherApisString+"/"+apiId+"/lifecycle-history");
        //System.out.println(endPoint+publisherApiLifecycleString+"?apiId="+apiId+"&action="+action);

        return changeApiStatusResponse;
    }

    public Response getComplexityRelatedDetailsOfApi(String apiId){
        Response getComplexityRelatedDetailsOfApiResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(ContentTypes.APPLICATION_JSON)
        .get(endPoint+publisherApisString+"/"+apiId+"/graphql-policies/complexity");

        return getComplexityRelatedDetailsOfApiResponse;
    }


    
}
