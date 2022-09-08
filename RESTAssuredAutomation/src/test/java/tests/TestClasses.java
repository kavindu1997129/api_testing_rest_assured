package tests;

import org.testng.annotations.Test;
import apitest.Authentication;

public class TestClasses {
	String accessToken;
	@Test
	public void oauth2() {

        Authentication authentication = new Authentication("admin","admin","https://localhost:9443/client-registration/v0.17/register","https://localhost:8243/token","./src/test/payloads/payload.json");
        System.out.println(authentication.getAccessToken());
			
	 }
    
}
