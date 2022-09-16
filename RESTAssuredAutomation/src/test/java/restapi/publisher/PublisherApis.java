package restapi.publisher;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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

    byte[] payloadplj1;
    String payloadpls1;

    byte[] payloadplj2;
    String payloadpls2;

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

    //-----------------------------------------------------------------------------------------------------------------

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

    public Response updateSwaggerDefinition(String apiId){
        Response updateSwaggerDefinitionRes = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .body(updateApiPayloadString)
        .contentType(ContentTypes.APPLICATION_JSON)
        .put(endPoint+publisherApisString+"/"+apiId+"/swagger");

        return updateSwaggerDefinitionRes;
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

    public Response getComplexityRelatedDetailsOfApi(String apiId){
        Response getComplexityRelatedDetailsOfApiResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(ContentTypes.APPLICATION_JSON)
        .get(endPoint+publisherApisString+"/"+apiId+"/graphql-policies/complexity");

        return getComplexityRelatedDetailsOfApiResponse;
    }

    public Response imporOpenAPIDefinition(String apiId, String openApiJsonPath, String dataPath){

        Response imporOpenAPIDefinitionRes = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(ContentTypes.APPLICATION_JSON)
        .multiPart(new File(resourceParenPath+openApiJsonPath))
        .multiPart(new File(resourceParenPath+dataPath))
        .post(endPoint+publisherApisString+"/import-openapi");

        return imporOpenAPIDefinitionRes;
    }

    public Response importWSDLDefinition(String apiId, String apiWSDL, String dataPath){
        Response importWSDLDefinitionRes = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(ContentTypes.APPLICATION_JSON)
        .multiPart(new File(resourceParenPath+apiWSDL))
        .multiPart(new File(resourceParenPath+dataPath))
        .post(endPoint+publisherApisString+"/import-wsdl");

        return importWSDLDefinitionRes;
    }

    public Response importAPIDefinition(String apiId, String schemaGraphGl, String dataPath){
        Response importAPIDefinitionRes = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(ContentTypes.APPLICATION_JSON)
        .multiPart(new File(resourceParenPath+schemaGraphGl))
        .multiPart(new File(resourceParenPath+dataPath))
        .post(endPoint+publisherApisString+"/import-graphql-schema");

        return importAPIDefinitionRes;
    }

    public Response getWsdlMetaInformation(String apiId){
        Response getWSDLMetaInformationRes = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(ContentTypes.APPLICATION_JSON)
        .get(endPoint+publisherApisString+"/"+apiId+"/wsdl-info");

        return getWSDLMetaInformationRes;
    }

    public Response getWsdlDefinition(String apiId){
        Response getWsdlDefinitionRes = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(ContentTypes.APPLICATION_JSON)
        .get(endPoint+publisherApisString+"/"+apiId+"/wsdl");

        return getWsdlDefinitionRes;
    }

    public Response updateWSDLDefinition(String apiId, String apiWSDL){
        Response updateWSDLDefinitionRes = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(ContentTypes.APPLICATION_JSON)
        .multiPart(new File(resourceParenPath+apiWSDL))
        .put(endPoint+publisherApisString+"/wsdl");

        return updateWSDLDefinitionRes;
    }

    public Response getResourcePathsofApi(String apiId){
        Response getResourcePathsofApiRes = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(ContentTypes.APPLICATION_JSON)
        .get(endPoint+publisherApisString+"/"+apiId+"/resource-paths");

        return getResourcePathsofApiRes;
    }

    //-----------------------------------------------------------------------------------------------------------------


    //-----------------------------------------------------------------------------------------------------------------

    public Response getResourcePolicyDefinitions(String apiId){
        // OAuth2Security (apim:api_view)
        Response getResourcePolicyDefinitionsRes = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(ContentTypes.APPLICATION_JSON)
        .get(endPoint+publisherApisString+"/"+apiId+"/resource-policies?resourcePath=checkPhoneNumber&verb=post&sequenceType=in");

        return getResourcePolicyDefinitionsRes;
    }

    public Response getResourcePolicyForResourceIdentifier(String apiId, String policyId){
        Response getResourcePolicyForResourceIdentifierRes = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(ContentTypes.APPLICATION_JSON)
        .get(endPoint+publisherApisString+"/"+apiId+"/resource-policies/"+policyId);

        return getResourcePolicyForResourceIdentifierRes;
    }

    public Response updateResourcePolicyForResourceIdentifier(String apiId, String policyId, String dataPath){

        try {
            payloadplj1 = Files.readAllBytes(Paths.get(resourceParenPath+dataPath));
		    payloadpls1 = new String(payloadplj1);
        } catch (Exception e) {
        }

        Response updateResourcePolicyForResourceIdentifierRes = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .body(payloadpls1)
        .contentType(ContentTypes.APPLICATION_JSON)
        .put(endPoint+publisherApisString+"/"+apiId+"/resource-policies/"+policyId);

        return updateResourcePolicyForResourceIdentifierRes;

    }

    //-----------------------------------------------------------------------------------------------------------------

    //-----------------------------------------------------------------------------------------------------------------

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

        return changeApiStatusResponse;
    }

    public Response getLifecycleStateDataOfApi(String apiId){
        Response getLifecycleStateDataOfApiResponse  = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(ContentTypes.APPLICATION_JSON)
        .get(endPoint+publisherApisString+"/"+apiId+"/lifecycle-state");

        return getLifecycleStateDataOfApiResponse;
    }

    public Response deletePendingLifecycleStateChangeTasks(String apiId){
        Response deletePendingLifecycleStateChangeTasksResponse  = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(ContentTypes.APPLICATION_JSON)
        .delete(endPoint+publisherApisString+"/"+apiId+"/lifecycle-state/pending-tasks");

        return deletePendingLifecycleStateChangeTasksResponse;
    }
    
}
