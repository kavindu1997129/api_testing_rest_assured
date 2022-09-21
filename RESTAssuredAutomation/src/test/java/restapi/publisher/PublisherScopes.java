package restapi.publisher;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import restapi.ApimVersions;
import restapi.ContentTypes;

public class PublisherScopes {

    String endPoint;
    String accessToken;
    ApimVersions version;

    String publisherScopesString = "/scopes";

    byte[] payloadJson1;
    String payloadString1;

    String resourceParentPath = "./src/test/payloads/";

    public PublisherScopes(String accessToken, ApimVersions version){
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

    public Response getAllSharedScopes(){

        Response getAllSharedScopesResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .get(endPoint+publisherScopesString); 

        return getAllSharedScopesResponse;
    }

    public Response addNewSharedScopes(String jsonPayloadPath){

        try {
            payloadJson1 = Files.readAllBytes(Paths.get(resourceParentPath+jsonPayloadPath));
		    payloadString1 = new String(payloadJson1);

        } catch (Exception e) {
            

        }

        Response getAllSharedScopesResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(ContentTypes.APPLICATION_JSON)
        .body(payloadString1)
        .post(endPoint+publisherScopesString); 

        return getAllSharedScopesResponse;
    }

    public Response getSharedScopeById(String scopeId){

        Response getSharedScopeByIdResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .get(endPoint+publisherScopesString+"/"+scopeId); 

        return getSharedScopeByIdResponse;
    }

    public Response updateSharedScope(String scopeId, String jsonPayloadPath){

        try {
            payloadJson1 = Files.readAllBytes(Paths.get(resourceParentPath+jsonPayloadPath));
		    payloadString1 = new String(payloadJson1);

        } catch (Exception e) {
            
        }

        Response updateSharedScopeResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(ContentTypes.APPLICATION_JSON)
        .body(payloadString1)
        .put(endPoint+publisherScopesString+"/"+scopeId); 

        return updateSharedScopeResponse;

    }

    public Response deleteSharedScope(String scopeId){

        Response deleteSharedScopeResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .delete(endPoint+publisherScopesString+"/"+scopeId); 

        return deleteSharedScopeResponse;
    }

    //-------------This method need to check, Bug found in the documentation--------------
    public Response checkGivenScopeAlreadyAvailable(String scopeId){

        Response checkGivenScopeAlreadyAvailableResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .head(endPoint+publisherScopesString+"/"+scopeId); 

        System.out.println(endPoint+publisherScopesString+"/"+scopeId);

        return checkGivenScopeAlreadyAvailableResponse;
    }

    public Response getUsageOfSharedScope(String scopeId){

        Response getUsageOfSharedScopeResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .get(endPoint+publisherScopesString+"/"+scopeId+"/usage"); 

        return getUsageOfSharedScopeResponse;
    }
    
    
}
