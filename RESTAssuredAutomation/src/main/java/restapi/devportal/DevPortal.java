package restapi.devportal;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DevportalApis {

    String accessToken;
    String endPoint;

    public DevportalApis(String accessToken, String endPoint){
        this.accessToken = accessToken;
        this.endPoint = endPoint;
    } 

    public Response searchApis(){
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
