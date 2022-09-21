package restapi.publisher;

import java.io.FileInputStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import restapi.ApimVersions;

public class PublisherKeyManager {

    String endPoint;
    String accessToken;
    ApimVersions version;

    String publisherKeyManagerString = "/key-managers";

    byte[] payloadJson1;
    String payloadString1;

    String resourceParentPath = "./src/test/payloads/";

    public PublisherKeyManager(String accessToken, ApimVersions version){
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

    public Response getAllKeyManagers(){

        Response getAllKeyManagersResponse = RestAssured.given()
        .relaxedHTTPSValidation()
        .auth()
        .oauth2(accessToken)
        .get(endPoint+publisherKeyManagerString); 

        return getAllKeyManagersResponse;
    }



    
}