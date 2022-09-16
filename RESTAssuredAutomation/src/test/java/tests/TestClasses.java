package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import restapi.ApimVersions;
import restapi.Authentication;
import restapi.AuthenticationObject;
import restapi.ContentTypes;
import restapi.ErrorHandling;
import restapi.GrantTypes;
import restapi.Scopes;
import restapi.devportal.DevportalApis;
import restapi.publisher.PublisherApiLifecycle;
import restapi.publisher.PublisherApiProducts;
import restapi.publisher.PublisherApis;
import restapi.publisher.PublisherGlobalMediationPolicies;
import restapi.publisher.PublisherSubscriptions;
import restapi.publisher.ThrottlingPolicies;

public class TestClasses {
	String accessToken;
        private static Logger logger = LogManager.getLogger(TestClasses.class);

	@Test
	public void dataGeneration() {

        AuthenticationObject authenticationObject = new AuthenticationObject();
        authenticationObject.setUsername("admin");
        authenticationObject.setUserpassword("admin");
        authenticationObject.setEndpoint("https://localhost:9443/client-registration/v0.17/register");
        authenticationObject.setTokenUrl("https://localhost:8243/token"); //For API-M 3.2.0
        // authenticationObject.setTokenUrl("https://localhost:9443/oauth2/token"); //For API-M 4.1.0
        authenticationObject.setPayloadPath("./src/test/payloads/payload.json");
        authenticationObject.setScopes(Scopes.API_PUBLISH, Scopes.API_CREATE, Scopes.API_VIEW, Scopes.API_IMPORT_EXPORT, Scopes.API_MANAGE, Scopes.SUBSCRIPTION_VIEW, Scopes.SUBSCRIPTION_BLOCK);
        authenticationObject.setContentType(ContentTypes.APPLICATION_JSON);
        authenticationObject.setGrantType(GrantTypes.PASSSWORD);

        Authentication authentication = new Authentication(authenticationObject);
        accessToken = authentication.getAccessToken();

        //API
        PublisherApis api = new PublisherApis(accessToken, ApimVersions.APIM_3_2);

        Response createApiRes = api.createApi(ContentTypes.APPLICATION_JSON, "apicretion_payload.json");
        logger.info("Status Code [CREATE API]: "+createApiRes.statusCode());

        Response searchApiRes = api.searchApis();
        logger.info("Status Code [SEARCH API]: "+searchApiRes.statusCode());
        
        String apiId = searchApiRes.jsonPath().get("list[0]['id']");
        // logger.info("[SEARCHED API ID]: "+apiId);

        Response uploadApiThumbnailRes = api.uploadThumbnailImage("thumbnail.jpg", apiId);
        logger.info("Status Code [UPLOAD API THUMBNAIL]: "+uploadApiThumbnailRes.statusCode());

        Response changeApiStatusRes = api.changeApiStatus(apiId, "Publish");
        logger.info("Status Code [CHANGE API STATUS]: "+changeApiStatusRes.statusCode());

        //API Product 
        PublisherApiProducts apiProd = new PublisherApiProducts(accessToken,ApimVersions.APIM_3_2);

        Response searchApiProductRes = apiProd.searchApiProduct();
        logger.info("Status Code [SEARCH API PRODUCT]: "+searchApiProductRes.statusCode());
        String apiProductId = searchApiProductRes.jsonPath().get("list[0]['id']");

        Response createApiProductRes = apiProd.createApiProduct(ContentTypes.APPLICATION_JSON, "apiproduct_creation_3_2.json");
        logger.info("Status Code [CREATE API PRODUCT]: "+createApiProductRes.statusCode());

        Response updateApiProductRes = apiProd.updateApiProduct(apiProductId, ContentTypes.APPLICATION_JSON, "updateApiProductPayload_3_2.json");
        logger.info("Status Code [UPDATE API PRODUCT]: "+updateApiProductRes.statusCode());

        Response uploadProductThumbnailRes = apiProd.uploadProductThumbnail("thumbnail.jpg", apiProductId);
        logger.info("Status Code [UPDATE API PRODUCT THUMBNAIL]: "+uploadProductThumbnailRes.statusCode());

        Response deletePendingLifecycleStateChangeTasksRes = api.deletePendingLifecycleStateChangeTasks(apiId);
        logger.info("Status Code [DELETE API PRODUCT LIFECYCLE]: "+deletePendingLifecycleStateChangeTasksRes.statusCode());
}

        @Test
        public void validateDataAPIM_3_2(){
                PublisherApis api = new PublisherApis(accessToken,ApimVersions.APIM_3_2);
                
                Response searchApi = api.searchApis();
                logger.info("Status Code [SEARCH API]: "+searchApi.statusCode());
                String apiId = searchApi.jsonPath().get("list[0]['id']");

                Response getApiDetails = api.getApiDetails(apiId);
                logger.info("Status Code [GET APIS DETAILS]: "+getApiDetails.statusCode());

                Response getApiThumbnail = api.getThumbnailImage(apiId);
                logger.info("Status Code [GET API THUMBNAIL]: "+getApiThumbnail.statusCode());

                Response getApiStatus = api.getApiStatus(apiId);
                logger.info("Status Code [GET API STATUS]: "+getApiStatus.statusCode());

                Response getApiStatusRes = api.getApiStatus(apiId);
                logger.info("Status Code [GET API STATUS]: "+getApiStatusRes.statusCode());

                Response getLifecycleStateDataOfApiRes = api.getLifecycleStateDataOfApi(apiId);
                logger.info("Status Code [GET STATE DATA OF API]: "+getLifecycleStateDataOfApiRes.statusCode());

                PublisherSubscriptions subs = new PublisherSubscriptions(accessToken, ApimVersions.APIM_3_2);
                Response getAllSubs = subs.getAllSubscriptions(apiId);
                logger.info("Status Code [GET ALL SUBSCRIPTIONS]: "+getAllSubs.statusCode());
                String subscriptionId  = getAllSubs.jsonPath().get("list[0]['subscriptionId']");
                logger.info("[SUBSCRIPTIONS ID]: "+subscriptionId);

                Response blockSubs = subs.blockSubscription(subscriptionId, "BLOCKED");
                logger.info("Status Code [BLOCK SUBSCRIPTIONS]: "+blockSubs.statusCode());

                Response unblockSubs = subs.unblockSubscription(subscriptionId);
                logger.info("Status Code [UNBLOCK SUBSCRIPTIONS]: "+unblockSubs.statusCode());

                Response getDetailsOfSubscriber = subs.getDetailsOfSubscriber(subscriptionId);
                logger.info("Status Code [GET DETAILS OF A SUBSCRIBER]: "+getDetailsOfSubscriber.statusCode());

                Response getApiThumbnailRes = api.getThumbnailImage(apiId);
                logger.info("Status Code [GET API THUMBNAIL]: "+getApiThumbnailRes.statusCode());

                Response getResourcePathsofApiRes = api.getResourcePathsofApi(apiId);
                logger.info("Status Code [GET RESOURCE PATH OF API]: "+getResourcePathsofApiRes.statusCode());

                Response getResourcePolicyDefinitionsRes = api.getResourcePolicyDefinitions(apiId);
                logger.info("Status Code [GET RESOURCE POLICY DEFINITION API]: "+getResourcePolicyDefinitionsRes.statusCode());

                Response getResourcePolicyForResourceIdentifierRes = api.getResourcePolicyForResourceIdentifier(apiId,"178");
                logger.info("Status Code [GET RESOURCE POLICY FOR RESOURCE IDENTIFIER]: "+getResourcePolicyForResourceIdentifierRes.statusCode());

                PublisherApiProducts apiProd = new PublisherApiProducts(accessToken, ApimVersions.APIM_3_2);
                Response searchApiProdsRes = apiProd.searchApiProduct();
                logger.info("Status Code [SEARCH API PRODUCTS]: "+searchApiProdsRes.statusCode());
                String apiProductId  = searchApiProdsRes.jsonPath().get("list[0]['id']");
                
                Response getDetailsOfApiProdRes = apiProd.getDetailsOfApiProduct(apiProductId);
                logger.info("Status Code [GET DETAILS OF API PRODUCT]: "+getDetailsOfApiProdRes.statusCode());
                
                Response getThumbnailOfApiprodRes = apiProd.getProductThumbnail(apiProductId);
                logger.info("Status Code [GET THUMBNAIL OF API PRODUCT]: "+getThumbnailOfApiprodRes.statusCode());

                ThrottlingPolicies policies = new ThrottlingPolicies(accessToken, ApimVersions.APIM_3_2);
                Response getAllPolicies = policies.getThrottlingPoliciesForGivenType("api");
                logger.info("Status Code [GET THROTTLING POLICIES]: "+getAllPolicies.statusCode());

                String policyLevel = getAllPolicies.jsonPath().get("list[3]['policyLevel']");
                String policyName = getAllPolicies.jsonPath().get("list[3]['name']");

                Response getDetaisOfPolicy = policies.getDetailsOfPolicy(policyLevel, policyName);
                logger.info("Status Code [GET DETAILS OF A POLICY]: "+getDetaisOfPolicy.statusCode());

                PublisherGlobalMediationPolicies gPolicies = new PublisherGlobalMediationPolicies(accessToken, ApimVersions.APIM_3_2);
                Response getGlobalMediationPolicyRes = gPolicies.getGlobalMediationPolicies();
                logger.info("Status Code [GET GLOBAL MEDIATION POLICY]: "+getGlobalMediationPolicyRes.statusCode());

        }

        // @Test
        // public void validateDataAPIM_4_1(){
        //         PublisherApis api = new PublisherApis(accessToken,ApimVersions.APIM_4_1);
                
        //         Response searchApi = api.searchApis();
        //         logger.info("Status Code [SEARCH API]: "+searchApi.statusCode());
        //         String apiId = searchApi.jsonPath().get("list[0]['id']");

        //         Response getApiDetails = api.getApiDetails(apiId);
        //         logger.info("Status Code [GET APIS DETAILS]: "+getApiDetails.statusCode());

        //         Response getApiThumbnail = api.getThumbnailImage(apiId);
        //         logger.info("Status Code [GET API THUMBNAIL]: "+getApiThumbnail.statusCode());

        //         Response getApiStatus = api.getApiStatus(apiId);
        //         logger.info("Status Code [GET API STATUS]: "+getApiStatus.statusCode());

        //         PublisherApiProducts apiProd = new PublisherApiProducts(accessToken, ApimVersions.APIM_4_1);
        //         Response searchApiProdsRes = apiProd.searchApiProduct();
        //         logger.info("Status Code [SEARCH API PRODUCTS]: "+searchApiProdsRes.statusCode());
        //         String apiProductId  = searchApiProdsRes.jsonPath().get("list[0]['id']");
                
        //         Response getDetailsOfApiProdRes = apiProd.getDetailsOfApiProduct(apiProductId);
        //         logger.info("Status Code [GET DETAILS OF API PRODUCT]: "+getDetailsOfApiProdRes.statusCode());
                
        //         Response getThumbnailOfApiprodRes = apiProd.getProductThumbnail(apiProductId);
        //         logger.info("Status Code [GET THUMBNAIL OF API PRODUCT]: "+getThumbnailOfApiprodRes.statusCode());

        // }
    
}
