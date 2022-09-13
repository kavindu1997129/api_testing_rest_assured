package restapi.publisher;

import java.io.FileInputStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PublisherApiLifecycle {

    String accessToken="";
    String endPoint = "";

    String publisherApiLifecycleString = "/change-lifecycle";

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

    public Response changeApiStatus(String apiId){
        Response changeApiStatusResponse  = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .post(endPoint+publisherApiLifecycleString);

        return changeApiStatusResponse;
    }
    
}
