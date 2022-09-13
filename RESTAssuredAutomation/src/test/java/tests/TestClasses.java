package tests;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import restapi.Authentication;
import restapi.AuthenticationObject;
import restapi.ContentTypes;
import restapi.GrantTypes;
import restapi.Scopes;
import restapi.devportal.DevportalApis;
import restapi.publisher.PublisherApiProducts;
import restapi.publisher.PublisherApis;

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
        authenticationObject.setScopes(Scopes.API_PUBLISH, Scopes.API_CREATE, Scopes.API_VIEW, Scopes.API_IMPORT_EXPORT);
        authenticationObject.setContentType(ContentTypes.APPLICATION_JSON);
        authenticationObject.setGrantType(GrantTypes.PASSSWORD);



        Authentication authentication = new Authentication(authenticationObject);
        accessToken = authentication.getAccessToken();

        PublisherApis api = new PublisherApis(accessToken);
        api.createApi(ContentTypes.APPLICATION_JSON, "./src/test/payloads/apicretion_payload.json").then().assertThat().statusCode(409);;
        String apiId = api.searchApis().jsonPath().get("list[0]['id']");
        api.uploadThumbnailImage("./src/test/payloads/thumbnail.jpg", apiId);
        //api.createNewApiVersion(apiId, "2.0.1", false);

        System.out.println(api.createNewApiVersion(apiId, "2.0.1", false).statusCode());
        
        System.out.println(api.uploadThumbnailImage("./src/test/payloads/thumbnail.jpg", apiId).statusCode());
        System.out.println(apiId);
        
        //PublisherApiProducts apiProd = new PublisherApiProducts(accessToken);
        //String apiProductId = apiProd.searchApiProduct().jsonPath().get("list[0]['id']");
        //String apiProductId = apiProd.searchApiProduct().jsonPath().get("list[0]['id']");
        //System.out.println(accessToken);
        //System.out.println(apiProd.createApiProduct(ContentTypes.APPLICATION_JSON, "./src/test/payloads/apiproduct_creation.json").statusCode());

        }
    
}
