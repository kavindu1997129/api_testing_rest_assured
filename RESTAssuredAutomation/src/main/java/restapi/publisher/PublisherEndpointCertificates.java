package restapi.publisher;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.TooManyListenersException;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import restapi.ApimVersions;
import restapi.ContentTypes;

public class PublisherEndpointCertificates {
	
	String accessToken="";
    String endPoint="";
    ApimVersions version;

    Response searchApisResponse;
    Response createApiResponse;
    Response uploadThumbnailImageResponse;
    Response getApiDetailsResponse;
    Response createNewApiVersiResponse;
    Response updateApiResponse;
    Response deleteApiResponse;

    byte[] apiCreationPayloadJson;
    String apiCreationPayloadString;

    byte[] createapiproductplj;
    String createapiproductpls;

    byte[] updateApiPayloadJson;
    String updateApiPayloadString;

    byte[] payloadplj1;
    String payloadpls1;

    byte[] payloadplj2;
    String payloadpls2;

    String publisherApisString = "/endpoint-certificates";
    String resourceParenPath = "./src/test/payloads/";

    public PublisherEndpointCertificates(String accessToken, ApimVersions version){
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
                this.endPoint = properties.getProperty("base_url")+"/api/am/publisher/v1";
            }
            else{
                this.endPoint = properties.getProperty("base_url")+"/api/am/publisher/v3";
            }
            
        } catch (Exception e) {
        }
        
    }
    
    public Response getUplodedCertificates(String alias, String endpoint){
    	Response getUplodedCertificatesResponse = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(accessToken)
				.get(endPoint+publisherApisString+"?alias="+alias+"&endpoint="+endpoint);

        return getUplodedCertificatesResponse;
    }
    
    // Many typos in the documentation below to this method, ask or check it again
    
//    public Response uploadNewCertificate(String apiId, String certificate, String alias, String endpoint, String tier){
//	      Response uploadNewCertificateRes  = RestAssured.given()
//	      .relaxedHTTPSValidation()
//	      .auth() 
//	      .oauth2(accessToken)    
//	      .multiPart("certificate", new File(resourceParenPath+certificate))
//	      .multiPart("alias", new File(resourceParenPath+alias))
//	      .multiPart("endpoint", new File(resourceParenPath+endpoint))
//	      .contentType(ContentTypes.MULTIPART_FORMDATA)
//	      .put(endPoint+publisherApisString+"/"+apiId+"/client-certificates");   
//	
//	    
//	      return uploadNewCertificateRes; 
//	}
    
    public Response deleteCertificate(String alias, String endpoint){
    	Response deleteCertificateResponse = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(accessToken)
				.delete(endPoint+publisherApisString+"?alias="+alias+"&endpoint="+endpoint);

        return deleteCertificateResponse;
    }

    public Response getCertificateInformation(String alias, String endpoint){
    	Response getCertificateInformationResponse = RestAssured.given()
				.relaxedHTTPSValidation()
				.auth()
				.oauth2(accessToken)
				.get(endPoint+publisherApisString+"/"+alias);

        return getCertificateInformationResponse;
    }
}
