package tests;

import java.io.FileReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ataf.actions.BaseTest;
import groovyjarjarantlr4.runtime.Parser;
import io.restassured.RestAssured;
import restapi.ApimVersions;
import restapi.Authentication;
import restapi.AuthenticationObject;
import restapi.ContentTypes;
import restapi.GrantTypes;
import restapi.Scopes;
import restapi.publisher.PublisherApis;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateAPIs extends BaseTest{

	private static Logger logger = LogManager.getLogger(TestClasses.class);
	
	@Test
	public void dataGeneration() {
		System.out.println(this.apiCount);
		for(int i=1 ; i<=apiCount ; i++) {
			PublisherApis  pApi = new PublisherApis(accessToken, ApimVersions.APIM_3_2);
			Response createApiResponse  = pApi.createApiParseJSON(getPayload(i));
	        JsonPath jsonPathEvaluator = createApiResponse.jsonPath();
			String apiID = jsonPathEvaluator.get("id");
//	        System.out.println("API " + i + " : "+ createApiResponse.statusCode() + " | " + apiID);
	        
	        logger.info("[API " +i+ " CREATED]: API ID ==>> "+apiID);
	        logger.info("Status Code [CREATE API " +i+ "]: "+createApiResponse.statusCode());
		}
	}
	
	
  static JSONObject getPayload(int apiIndex) {

  byte[] payloadJson1;
  String payloadString1;
  String payload="";
  JSONObject jsonObject = new JSONObject();

  try {
	  JSONParser parser = new JSONParser();
	  Object obj = parser.parse(new FileReader("./src/test/payloads/apicretion_payload.json"));
      jsonObject = (JSONObject) obj;
      jsonObject.put("name","PizzaShackAPI_"+String.valueOf(apiIndex));
      jsonObject.put("context", "pizza_"+String.valueOf(apiIndex));
      payload = jsonObject.toString();
  } catch (Exception e) {
	  
  }
	  return jsonObject;
	  
	}
}
