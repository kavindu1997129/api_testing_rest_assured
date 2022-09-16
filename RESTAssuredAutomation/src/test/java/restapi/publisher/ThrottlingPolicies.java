package restapi.publisher;

import java.io.FileInputStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import restapi.ApimVersions;

public class ThrottlingPolicies {

    String accessToken = "";
    ApimVersions version;
    String endPoint;

    String publisherApisString = "/throttling-policies";
    String resourceParenPath = "./src/test/payloads/";

    public ThrottlingPolicies(String accessToken, ApimVersions version){
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

    public Response getThrottlingPoliciesForGivenType(String policyLevel){
        Response getThrottlingPoliciesForGivenTypeRes = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .get(endPoint+publisherApisString+"/"+policyLevel);

    return getThrottlingPoliciesForGivenTypeRes;
    }

    public Response getDetailsOfPolicy(String policyLevel, String policyName){
        Response getDetailsOfPolicyRes = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .get(endPoint+publisherApisString+"/"+policyLevel.toLowerCase()+"/"+policyName);

    return getDetailsOfPolicyRes;
    }
    
}
