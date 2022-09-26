package restapi.devportal;

import java.io.FileInputStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import restapi.ApimVersions;

public class DevPortal {
    
    public static class Apis{
    	
    	String accessToken;
        String endPoint;
        
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
					.get(endPoint);
	        
	    return searchApisResponse;
	    }

        public Response getApiDetails(String apiId){
            Response getApiDetailsResponse = RestAssured.given()
    				.relaxedHTTPSValidation()
    				.auth()
    				.oauth2(accessToken)
    				.get(endPoint+"/"+apiId);

            return getApiDetailsResponse;
        }


        public Response getSwaggerDefinition(){
            Response getSwaggerDefinitionResponse = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .get(endPoint);
            return getSwaggerDefinitionResponse;
        }


        public Response getGraphQLDefinition(String apiId){
            Response getGraphQLDefinitionResponse = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .get(endPoint+"/"+apiId+"/graphql-schema");
            return getGraphQLDefinitionResponse;
        }

        
        public Response getApiWsdlDefinition(String apiId){
            Response getApiWsdlDefinitionResponse = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .get(endPoint+"/"+apiId+"/wsdl");
            return getApiWsdlDefinitionResponse;
        }

        public Response getThumbnailImage(String apiId){
            Response getThumbnailImageResponse= RestAssured.given()
    				.relaxedHTTPSValidation()
    				.auth()
    				.oauth2(accessToken)
    				.get(endPoint+"/"+apiId+"/thumbnail");

            return getThumbnailImageResponse;
        }

        public Response getSubscriptionThrotlling(String apiId){
            Response getSubscriptionThrotllingResponse = RestAssured.given()
    				.relaxedHTTPSValidation()
    				.auth()
    				.oauth2(accessToken)
    				.get(endPoint+"/"+apiId+"/subscription-policies");

            return getSubscriptionThrotllingResponse;
        }
	
    }
    
    public static class ApiDocumentation{
    	
    	
    	
    }

    
    
}
