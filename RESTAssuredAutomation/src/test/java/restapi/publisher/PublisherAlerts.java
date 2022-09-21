package restapi.publisher;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import restapi.ApimVersions;
import restapi.ContentTypes;

public class PublisherAlerts {

    String endPoint;
    String accessToken;
    ApimVersions version;

    String publisherAlertsString = "/settings";

    byte[] payloadJson1;
    String payloadString1;

    String resourceParentPath = "./src/test/payloads/";

    public PublisherAlerts(String accessToken, ApimVersions version){
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

    public Response getPublisherAlertTypes(){
        Response getPublisherAlertTypesResponse  = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(ContentTypes.APPLICATION_JSON)
        .get(endPoint+"/alert-types");

        return getPublisherAlertTypesResponse;
    }

    public Response getPublisherAlertTypesSubscribed(){
        Response getPublisherAlertTypesSubscribedResponse  = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(ContentTypes.APPLICATION_JSON)
        .get(endPoint+"/alert-subscriptions");

        return getPublisherAlertTypesSubscribedResponse;
    }

    public Response subscribeToSelectedAlertType(String jsonPayloadPath){

        try {
            payloadJson1 = Files.readAllBytes(Paths.get(resourceParentPath+jsonPayloadPath));
		    payloadString1 = new String(payloadJson1);

        } catch (Exception e) {
            
        }

        Response subscribeToSelectedAlertTypeResponse  = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(ContentTypes.APPLICATION_JSON)
        .body(payloadString1)
        .put(endPoint+"/alert-subscriptions");

        return subscribeToSelectedAlertTypeResponse;
    }

    public Response unsubscribeUserAllAlertTypes(){
        Response unsubscribeUserAllAlertTypesResponse  = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(ContentTypes.APPLICATION_JSON)
        .delete(endPoint+"/alert-subscriptions");

        return unsubscribeUserAllAlertTypesResponse;
    }

    public Response getAbnormalRequests(String alertType){
        Response getAbnormalRequestsResponse  = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(ContentTypes.APPLICATION_JSON)
        .get(endPoint+"/alerts/"+alertType+"/configurations");

        return getAbnormalRequestsResponse;
    }

    public Response addAbnormalRequests(String alertType, String configurationId, String jsonPayloadPath){

        try {
            payloadJson1 = Files.readAllBytes(Paths.get(resourceParentPath+jsonPayloadPath));
		    payloadString1 = new String(payloadJson1);

        } catch (Exception e) {
        }

        Response addAbnormalRequestsResponse  = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(ContentTypes.APPLICATION_JSON)
        .body(payloadString1)
        .put(endPoint+"/alerts/"+alertType+"/configurations/"+configurationId);

        return addAbnormalRequestsResponse;
    }

    public Response deleteAbnormalRequests(String alertType, String configurationId){

        Response addAbnormalRequestsResponse  = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(ContentTypes.APPLICATION_JSON)
        .delete(endPoint+"/alerts/"+alertType+"/configurations/"+configurationId);

        return addAbnormalRequestsResponse;
    }
   
}
