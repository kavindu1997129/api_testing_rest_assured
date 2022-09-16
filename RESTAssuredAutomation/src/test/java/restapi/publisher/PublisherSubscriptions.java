package restapi.publisher;

import java.io.FileInputStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import restapi.ApimVersions;
import restapi.ContentTypes;

public class PublisherSubscriptions {

    String accessToken = "";
    ApimVersions version;
    String endPoint;

    String publisherApisString = "/subscriptions";
    String resourceParenPath = "./src/test/payloads/";

    public PublisherSubscriptions(String accessToken, ApimVersions version){
        this.accessToken = accessToken;
        this.version = version;

        FileInputStream input;
	    Properties properties;


        try {
            String path =  "./src/test/resources/config.properties";
			properties = new Properties();
			input = new FileInputStream(path);
			properties.load(input);
            if(version == ApimVersions.APIM_3_2){
                this.endPoint = properties.getProperty("base_url_3_2");
            }
            else{
                this.endPoint = properties.getProperty("base_url_4_1");
            }
            
        } catch (Exception e) {
        }
    }

    public Response getAllSubscriptions(String apiId){
        Response getAllSubscriptionsRes = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"?apiId="+apiId);

        return getAllSubscriptionsRes;
    }

    public Response blockSubscription(String subscriptionId, String blockStatus){
        Response blockSubscriptionRes = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .post(endPoint+publisherApisString+"/block-subscription?subscriptionId="+subscriptionId+"&blockState="+blockStatus);

        return blockSubscriptionRes;

    }

    public Response unblockSubscription(String subscriptionId){
        Response unblockSubscriptionRes = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .post(endPoint+publisherApisString+"/unblock-subscription?subscriptionId="+subscriptionId);

        return unblockSubscriptionRes;

    }

    public Response getDetailsOfSubscriber(String subscriptionId){
        Response getDetailsOfSubscriberRes = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/"+subscriptionId+"/subscriber-info");

        return getDetailsOfSubscriberRes;
    }

    
}
