package tests;

import org.testng.annotations.Test;
import apitest.Authentication;
import apitest.publisher.Apis;

public class TestClasses {
	String accessToken;
	@Test
	public void oauth2() {

        Authentication authentication = new Authentication("admin","admin","https://localhost:9443/client-registration/v0.17/register","https://localhost:8243/token","./src/test/payloads/payload.json","apim:api_view apim:api_create","password","application/json");
        accessToken = authentication.getAccessToken();
        System.out.println(authentication.getAccessToken());	

        Apis api = new Apis(accessToken,"https://localhost:9443/api/am/publisher/v1");
        System.out.println(api.searchApis("apis"));

	 }
    
}
