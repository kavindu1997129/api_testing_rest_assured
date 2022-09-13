package restapi.publisher;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import restapi.ContentTypes;

public class PublisherApiLifecycle {

    String accessToken="";
    String endPoint = "";


    String publisherApiLifecycleString = "/apis/change-lifecycle";

    public PublisherApiLifecycle(String accessToken){
        this.accessToken = accessToken;

        FileInputStream input;
	    Properties properties;
        
        try {
            String path =  "./src/test/resources/config.properties";
			properties = new Properties();
			input = new FileInputStream(path);
			properties.load(input);
            this.endPoint = properties.getProperty("base_url");
            
        } catch (Exception e) {
        }
    }

    public Response changeApiStatus(String apiId, String action){
        Response changeApiStatusResponse  = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .contentType(ContentTypes.APPLICATION_JSON)
        .post(endPoint+publisherApiLifecycleString+"?apiId="+apiId+"&action="+action);
        System.out.println(endPoint+publisherApiLifecycleString+"?apiId="+apiId+"&action="+action);

        return changeApiStatusResponse;
    }

    // public Response updateApiProduct(String apiProductId, String contentType,  String jsonPayloadPath){

    //     try {
    //         apiProductUpdatePayloadJson = Files.readAllBytes(Paths.get(jsonPayloadPath));
	// 	    apiProductUpdatePayloadString = new String(apiProductUpdatePayloadJson);
    //     } catch (Exception e) {
    //     }

    //     Response updateApiProductResponse = RestAssured.given()
    //     .relaxedHTTPSValidation()
    //     .auth()
    //     .oauth2(accessToken)
    //     .contentType(contentType)
    //     .body(apiProductUpdatePayloadString)
    //     .put(endPoint+publisherApiLifecycleString+"/"+apiProductId);

    //     return  updateApiProductResponse;

    // }
    
}
