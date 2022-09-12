package tests;

import org.testng.annotations.Test;
import apitest.Authentication;
import apitest.AuthenticationObject;
import apitest.devportal.DevportalApis;
import apitest.publisher.PublisherApiProducts;
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
        authenticationObject.setScope("apim:api_admin apim:api_import_export apim:api_view apim:api_create apim:api_product_import_export apim:api_product_create");
        //apim:api_view apim:api_create 
        //authenticationObject.setScope("apim:api_view apim:api_create");
        //authenticationObject.setScope(""apim:api_product_import_export");
        //authenticationObject.setScope("apim:api_publish");
        authenticationObject.setContentType("application/json");
        authenticationObject.setGrantType("password");



        Authentication authentication = new Authentication(authenticationObject);
        accessToken = authentication.getAccessToken();
        //System.out.println(authentication.getAccessToken());	

        //PublisherApis api = new PublisherApis(accessToken,"https://localhost:9443/api/am/publisher/v1/apis");
        //System.out.println(api.createApi("application/json","./src/test/payloads/apicretion_payload.json").jsonPath().prettify());

        // String apiId =api.searchApis().jsonPath().get("list[0]['id']");
        // System.out.println(api.getSubscriptionThrotlling(apiId).jsonPath().prettyPrint());

        //DevportalApis apiDev = new DevportalApis(accessToken,"https://localhost:9443/api/am/store/v1/apis");
        // PublisherApis apiPub = new PublisherApis(accessToken, "https://localhost:9443/api/am/publisher/v1/apis");
        // String apiId =apiPub.searchApis().jsonPath().get("list[0]['id']");
        // System.out.println(apiPub.deleteApi(apiId).statusCode());

        PublisherApiProducts apiProd = new PublisherApiProducts("https://localhost:9443/api/am/publisher/v1/api-products", accessToken);
        //String apiProductId = apiProd.searchApiProduct().jsonPath().get("list[0]['id']");
        String apiProductId = apiProd.searchApiProduct().jsonPath().get("list[0]['id']");
        System.out.println(apiProductId);
        System.out.println(apiProd.getDetailsOfApiProduct(apiProductId).jsonPath().prettify());
	}
    
}
