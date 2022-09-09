package tests;

import org.testng.annotations.Test;
import apitest.Authentication;
import apitest.AuthenticationObject;
import apitest.devportal.DevportalApis;
import apitest.publisher.PublisherApis;
import io.restassured.path.json.JsonPath;

public class TestClasses {
	String accessToken;
	@Test
	public void oauth2() {

        AuthenticationObject authenticationObject = new AuthenticationObject();
        authenticationObject.setUsername("admin");
        authenticationObject.setUserpassword("admin");
        authenticationObject.setEndpoint("https://localhost:9443/client-registration/v0.17/register");
        authenticationObject.setTokenUrl("https://localhost:8243/token");
        authenticationObject.setPayloadPath("./src/test/payloads/payload.json");
        authenticationObject.setScope("apim:api_view apim:api_create");
        authenticationObject.setContentType("application/json");
        authenticationObject.setGrantType("password");



        Authentication authentication = new Authentication(authenticationObject);
                accessToken = authentication.getAccessToken();
        //System.out.println(authentication.getAccessToken());	

        // PublisherApis api = new PublisherApis(accessToken,"https://localhost:9443/api/am/publisher/v1/apis");
        // String apiId =api.searchApis().jsonPath().get("list[0]['id']");
        // System.out.println(api.getSubscriptionThrotlling(apiId).jsonPath().prettyPrint());

        DevportalApis api = new DevportalApis(accessToken,"https://localhost:9443/api/am/store/v1/apis");
        String apiId =api.searchApis().jsonPath().get("list[0]['id']");
        System.out.println(api.getSubscriptionThrotlling(apiId).statusCode());
        
	}
    
}
