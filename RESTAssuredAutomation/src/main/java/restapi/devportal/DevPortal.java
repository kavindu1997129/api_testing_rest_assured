package restapi.devportal;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.logging.log4j.util.StringBuilderFormattable;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import restapi.ApimVersions;
import restapi.ContentTypes;

public class DevPortal {
    
    public static class Apis{
    	
    	String accessToken;
        String endPoint;
        
        String publisherApisString = "/apis";
        String resourceParenPath = "./src/test/payloads/";
        
    	public Apis(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            this.endPoint = endPoint;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("devportal_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("devportal_url_4_1");
                }
                
            } catch (Exception e) {
            }
    	}
    	
    	public  Response searchApis(){
	        Response searchApisResponse;
	        searchApisResponse = RestAssured.given()
					.relaxedHTTPSValidation()
					.auth()
					.oauth2(accessToken)
					.get(endPoint+publisherApisString);
	        
	    return searchApisResponse;
	    }

        public Response getApiDetails(String apiId){
            Response getApiDetailsResponse = RestAssured.given()
    				.relaxedHTTPSValidation()
    				.auth()
    				.oauth2(accessToken)
    				.get(endPoint+publisherApisString+"/"+apiId);

            return getApiDetailsResponse;
        }


        public Response getSwaggerDefinition(){
            Response getSwaggerDefinitionResponse = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .get(endPoint+publisherApisString);
            return getSwaggerDefinitionResponse;
        }


        public Response getGraphQLDefinition(String apiId){
            Response getGraphQLDefinitionResponse = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .get(endPoint+publisherApisString+"/"+apiId+"/graphql-schema");
            return getGraphQLDefinitionResponse;
        }

        
        public Response getApiWsdlDefinition(String apiId){
            Response getApiWsdlDefinitionResponse = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .get(endPoint+publisherApisString+"/"+apiId+"/wsdl");
            return getApiWsdlDefinitionResponse;
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
	
    }
    
    public static class Sdks{
    	
    }
    
    public static class ApiDocumentation{
    
    	String accessToken;
        String endPoint;
        
        String publisherApisString = "/apis";
        String resourceParenPath = "./src/test/payloads/";
        
        byte[] payloadplj1;
        String payloadpls1;
        
    	public ApiDocumentation(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("devportal_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("devportal_url_4_1");
                }
                
            } catch (Exception e) {
            }
    	}
    	
    	public Response getListOfDocOfApi(String apiId){
            Response getListOfDocOfApiResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/"+apiId+"/documents");

            return getListOfDocOfApiResponse;
        }
        
        public Response addNewDocToApi(String apiId, String jsonPayloadPath){
        	
        	try {
        		payloadplj1 = Files.readAllBytes(Paths.get(resourceParenPath+jsonPayloadPath));
        		payloadpls1 = new String(payloadplj1);

            } catch (Exception e) {
            }
        	
            Response addNewDocToApiResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .body(payloadpls1)
            .post(endPoint+publisherApisString+"/"+apiId+"/documents");

            return addNewDocToApiResponse;
        }
        
        public Response getDocOfApi(String apiId, String documenetId){
            Response getDocOfApiResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/"+apiId+"/documents/"+documenetId);

            return getDocOfApiResponse;
        }
        
        public Response updateDocOfApi(String apiId, String documenetId){
            Response updateDocOfApiResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .put(endPoint+publisherApisString+"/"+apiId+"/documents/"+documenetId);

            return updateDocOfApiResponse;
        }
        
        public Response deleteDocOfApi(String apiId, String documenetId){
            Response deleteDocOfApiResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .delete(endPoint+publisherApisString+"/"+apiId+"/documents/"+documenetId);

            return deleteDocOfApiResponse;
        }
        
        public Response getContentOfDocOfApi(String apiId, String documenetId){
            Response getContentOfDocOfApiResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/"+apiId+"/documents/"+documenetId+"/content");

            return getContentOfDocOfApiResponse;
        }
        
        public Response uploadContentOfDocOfApi(String apiId, String documenetId, String dataPath){
            Response uploadContentOfDocOfApiResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .multiPart(new File(resourceParenPath+dataPath))
            .post(endPoint+publisherApisString+"/"+apiId+"/documents/"+documenetId+"/content");

            return uploadContentOfDocOfApiResponse;
        }
        
        public Response checkDocExistsByName(String apiId, String documenetId, String docName){
            Response checkDocExistsByNameResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .post(endPoint+publisherApisString+"/"+apiId+"/documents/"+documenetId+"/validate?name="+docName);

            return checkDocExistsByNameResponse;
        }
    	
    }
    
    public static class Rating{
    	
    	String accessToken;
        String endPoint;
        
        String publisherApisString = "/apis";
        String resourceParenPath = "./src/test/payloads/";
        
        byte[] payloadplj1;
        String payloadpls1;
        
    	public Rating(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("devportal_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("devportal_url_4_1");
                }
                
            } catch (Exception e) {
            }
    	}
    	
    	public Response getApiRatings(String apiId){
            Response getApiRatingsResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/"+apiId+"/ratings");

            return getApiRatingsResponse;
        }
    	
    	public Response getApiRatingOfUser(String apiId){
            Response getApiRatingOfUserResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/"+apiId+"/user-rating");

            return getApiRatingOfUserResponse;
        }
    	
    	public Response addOrUpdateLoggedUserRatingOfApi(String apiId, String jsonPayloadPath){
    		
    		try {
        		payloadplj1 = Files.readAllBytes(Paths.get(resourceParenPath+jsonPayloadPath));
        		payloadpls1 = new String(payloadplj1);
            } catch (Exception e) {
            }
    		
            Response addOrUpdateLoggedUserRatingOfApiResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .body(payloadplj1)
            .put(endPoint+publisherApisString+"/"+apiId+"/user-rating");

            return addOrUpdateLoggedUserRatingOfApiResponse;
        }
    	
    	public Response deleteUserApirating(String apiId){
            Response deleteUserApiratingResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .delete(endPoint+publisherApisString+"/"+apiId+"/user-rating");

            return deleteUserApiratingResponse;
        }
    }
    
    public static class Comments{
    	
    	String accessToken;
        String endPoint;
        
        String publisherApisString = "/apis";
        String resourceParenPath = "./src/test/payloads/";
        
        byte[] payloadplj1;
        String payloadpls1;
        
    	public Comments(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("devportal_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("devportal_url_4_1");
                }
                
            } catch (Exception e) {
            }
    	}
    	
    	public Response getApiComments(String apiId){
            Response getApiCommentsResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/"+apiId+"/comments");

            return getApiCommentsResponse;
        }
    	
    	public Response addApiComment(String apiId, String jsonPayloadPath){
    		
    		try {
        		payloadplj1 = Files.readAllBytes(Paths.get(resourceParenPath+jsonPayloadPath));
        		payloadpls1 = new String(payloadplj1);
            } catch (Exception e) {
            }
    		
            Response addApiCommentResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .body(payloadplj1)
            .post(endPoint+publisherApisString+"/"+apiId+"/comments");

            return addApiCommentResponse;
        }
    	
    	public Response getDetailsOfApiComment(String apiId, String commentId){
            Response getDetailsOfApiCommentResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/"+apiId+"/comments/"+commentId);

            return getDetailsOfApiCommentResponse;
        }
    	
    	public Response deleteApiComment(String apiId, String commentId){
            Response deleteApiCommentResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .delete(endPoint+publisherApisString+"/"+apiId+"/comments/"+commentId);

            return deleteApiCommentResponse;
        }
    	
    }
    
    public static class Appilications{
    	
    	String accessToken;
        String endPoint;
        
        String publisherApisString = "/apis";
        String resourceParenPath = "./src/test/payloads/";
        
        byte[] payloadplj1;
        String payloadpls1;
        
    	public Appilications(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("devportal_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("devportal_url_4_1");
                }
                
            } catch (Exception e) {
            }
    	}
    	
    	public Response searchApplications(){
            Response searchApplicationsResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/applications");

            return searchApplicationsResponse;
        }
    	
    	public Response createNewApplications(String jsonPayloadPath){
    		
    		try {
        		payloadplj1 = Files.readAllBytes(Paths.get(resourceParenPath+jsonPayloadPath));
        		payloadpls1 = new String(payloadplj1);
            } catch (Exception e) {
            }
    		
            Response createNewApplicationsResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .body(payloadplj1)
            .post(endPoint+publisherApisString+"/applications");

            return createNewApplicationsResponse;
        }
    	
    	public Response getDetailsOfApplication(String  applicationId){
            Response searchApplicationsResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/applications/"+applicationId);

            return searchApplicationsResponse;
        }
    	
    	public Response updateApplications(String applicationId,String jsonPayloadPath){
    		
    		try {
        		payloadplj1 = Files.readAllBytes(Paths.get(resourceParenPath+jsonPayloadPath));
        		payloadpls1 = new String(payloadplj1);
            } catch (Exception e) {
            }
    		
            Response updateApplicationsResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .body(payloadplj1)
            .put(endPoint+publisherApisString+"/applications/"+applicationId);

            return updateApplicationsResponse;
        }
    	
    	public Response deleteApplication(String  applicationId){
            Response deleteApplicationResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/applications/"+applicationId);

            return deleteApplicationResponse;
        }
    	
    	
    	
    	
    }

    
    
}
