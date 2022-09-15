package tests;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import restapi.ApimVersions;
import restapi.Authentication;
import restapi.AuthenticationObject;
import restapi.ContentTypes;
import restapi.GrantTypes;
import restapi.Scopes;
import restapi.devportal.DevportalApis;
import restapi.publisher.PublisherApiLifecycle;
import restapi.publisher.PublisherApiProducts;
import restapi.publisher.PublisherApis;

public class TestClasses {
	String accessToken;
	@Test
	public void dataGeneration() {

        AuthenticationObject authenticationObject = new AuthenticationObject();
        authenticationObject.setUsername("admin");
        authenticationObject.setUserpassword("admin");
        authenticationObject.setEndpoint("https://localhost:9443/client-registration/v0.17/register");
        // authenticationObject.setTokenUrl("https://localhost:8243/token"); //For API-M 3.2.0
        authenticationObject.setTokenUrl("https://localhost:9443/oauth2/token"); //For API-M 4.1.0
        authenticationObject.setPayloadPath("./src/test/payloads/payload.json");
        authenticationObject.setScopes(Scopes.API_PUBLISH, Scopes.API_CREATE, Scopes.API_VIEW, Scopes.API_IMPORT_EXPORT, Scopes.API_MANAGE);
        authenticationObject.setContentType(ContentTypes.APPLICATION_JSON);
        authenticationObject.setGrantType(GrantTypes.PASSSWORD);



        Authentication authentication = new Authentication(authenticationObject);
        accessToken = authentication.getAccessToken();

        PublisherApis api = new PublisherApis(accessToken, ApimVersions.APIM_4_1);
        Response createApiRes = api.createApi(ContentTypes.APPLICATION_JSON, "./src/test/payloads/apicretion_payload.json");
        String apiId = api.searchApis().jsonPath().get("list[0]['id']");
        System.out.println(apiId);
        Response uploadApiThumbnailRes = api.uploadThumbnailImage("./src/test/payloads/thumbnail.jpg", apiId);
        Response changeApiStatusRes = api.changeApiStatus(apiId, "Publish");
        Response getApiStatusRes = api.getApiStatus(apiId);
        // Response createNewApiVersioRes = api.createNewApiVersion(apiId, "2.0.1", false);
        // Response getComplexityRelatedDetailsOfApiRes = api.getComplexityRelatedDetailsOfApi(apiId);

        Response getApiThumbnailRes = api.getThumbnailImage(apiId);
        
        System.out.println("createApiRes: "+createApiRes.statusCode());
        System.out.println("apiId: "+apiId);
        System.out.println("changeApiStatusRes: "+changeApiStatusRes.statusCode());
        System.out.println("getApiStatusRes: "+getApiStatusRes.statusCode());
        System.out.println("uploadApiThumbnailRes: "+uploadApiThumbnailRes.statusCode());
        // System.out.println("getComplexityRelatedDetailsOfApiRes: "+getComplexityRelatedDetailsOfApiRes.statusCode());

        //System.out.println(getApiThumbnailRes.statusCode());
        
        PublisherApiProducts apiProd = new PublisherApiProducts(accessToken,ApimVersions.APIM_4_1);
        Response searchApiProductRes = apiProd.searchApiProduct();
        String apiProductId = searchApiProductRes.jsonPath().get("list[0]['id']");
        Response createApiProductRes = apiProd.createApiProduct(ContentTypes.APPLICATION_JSON, "./src/test/payloads/apiproduct_creation_4_1.json");
        Response updateApiProductRes = apiProd.updateApiProduct(apiProductId, ContentTypes.APPLICATION_JSON, "./src/test/payloads/updateApiProductPayload_4_1.json");
        Response uploadProductThumbnailRes = apiProd.uploadProductThumbnail("./src/test/payloads/thumbnail.jpg", apiProductId);
        
        System.out.println("createApiProductRes: "+createApiProductRes.statusCode());
        System.out.println("updateApiProductRes: " +updateApiProductRes.statusCode());
        System.out.println("uploadProductThumbnailRes: "+ uploadProductThumbnailRes.statusCode());

}

        // @Test
        // public void validateDataAPIM_3_2(){
        //         PublisherApis api = new PublisherApis(accessToken, ApimVersions.APIM_3_2);
        //         Response searchApi = api.searchApis();
        //         String apiId = searchApi.jsonPath().get("list[0]['id']");
        //         Response getApiDetails = api.getApiDetails(apiId);
        //         Response getApiThumbnail = api.getThumbnailImage(apiId);
        //         Response getApiStatus = api.getApiStatus(apiId);

        //         System.out.println("Search APIs: "+searchApi.statusCode());
        //         System.out.println("Get API details: "+getApiDetails.statusCode());
        //         System.out.println("Get API Thumbnail: "+getApiThumbnail.statusCode());
        //         System.out.println("Get API status: "+getApiStatus.statusCode());

        //         PublisherApiProducts apiProd = new PublisherApiProducts(accessToken);
        //         Response searchApiProdsRes = apiProd.searchApiProduct();
        //         String apiProductId  = searchApiProdsRes.jsonPath().get("list[0]['id']");
        //         Response getDetailsOfApiProdRes = apiProd.getDetailsOfApiProduct(apiProductId);
        //         Response getThumbnailOfApiprodRes = apiProd.getProductThumbnail(apiProductId);

        //         System.out.println("Search Api Product: "+ searchApiProdsRes.statusCode());
        //         System.out.println("Get details of API product: "+ getDetailsOfApiProdRes.statusCode());
        //         System.out.println("Get thumbnail of API product: "+ getThumbnailOfApiprodRes.statusCode());

        // }

        @Test
        public void validateDataAPIM_4_1(){
                PublisherApis api = new PublisherApis(accessToken,ApimVersions.APIM_4_1);
                Response searchApi = api.searchApis();
                String apiId = searchApi.jsonPath().get("list[0]['id']");
                Response getApiDetails = api.getApiDetails(apiId);
                Response getApiThumbnail = api.getThumbnailImage(apiId);
                Response getApiStatus = api.getApiStatus(apiId);

                System.out.println("Search APIs: "+searchApi.statusCode());
                System.out.println("Get API details: "+getApiDetails.statusCode());
                System.out.println("Get API Thumbnail: "+getApiThumbnail.statusCode());
                System.out.println("Get API status: "+getApiStatus.statusCode());

                PublisherApiProducts apiProd = new PublisherApiProducts(accessToken, ApimVersions.APIM_4_1);
                Response searchApiProdsRes = apiProd.searchApiProduct();
                String apiProductId  = searchApiProdsRes.jsonPath().get("list[0]['id']");
                Response getDetailsOfApiProdRes = apiProd.getDetailsOfApiProduct(apiProductId);
                Response getThumbnailOfApiprodRes = apiProd.getProductThumbnail(apiProductId);

                System.out.println("Search Api Product: "+ searchApiProdsRes.statusCode());
                System.out.println("Get details of API product: "+ getDetailsOfApiProdRes.statusCode());
                System.out.println("Get thumbnail of API product: "+ getThumbnailOfApiprodRes.statusCode());

        }
    
}
