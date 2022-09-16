package restapi.publisher;

import java.io.FileInputStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import restapi.ApimVersions;
import restapi.ContentTypes;

public class PublisherGlobalMediationPolicies {

    String accessToken = "";
    ApimVersions version;
    String endPoint;

    String publisherApisString = "/mediation-policies";
    String resourceParenPath = "./src/test/payloads/";

    public PublisherGlobalMediationPolicies(String accessToken, ApimVersions version){
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

    public Response getGlobalMediationPolicies(){
        Response getGlobalMediationPoliciesRes = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString);

        return getGlobalMediationPoliciesRes;
    }
    
}
