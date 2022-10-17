package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ataf.actions.BaseTest;
import restapi.ApimVersions;
import restapi.ContentTypes;
import restapi.devportal.DevPortal;
import restapi.publisher.Publisher;
import soapapi.remoteuserstore.RemoteUserStore;
import soapapi.tenantmanagemant.TenantManagement;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import io.restassured.internal.util.IOUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.net.TcpSocketManager;

public class DataPopulations extends BaseTest{

	private static Logger logger = LogManager.getLogger(TestClasses.class);
	
//	@Test
//	public void dataGeneration() {
//		System.out.println(this.apiCount);
//		for(int i=1 ; i<=apiCount ; i++) {
//			Publisher.Apis  pApi = new Publisher.Apis(accessToken, ApimVersions.APIM_3_2);
//			Response createApiResponse  = pApi.createApiParseJSON(getPayload(i));
//	        JsonPath jsonPathEvaluator = createApiResponse.jsonPath();
//			String apiID = jsonPathEvaluator.get("id");
////	        System.out.println("API " + i + " : "+ createApiResponse.statusCode() + " | " + apiID);
//	        
//	        logger.info("[API " +i+ " CREATED]: API ID ==>> "+apiID);
//	        logger.info("Status Code [CREATE API " +i+ "]: "+createApiResponse.statusCode());
//		}
//	}
	
  @Test
  public void remoteUserStore() throws Exception {
      
      RemoteUserStore rUserStore = new RemoteUserStore(accessToken,baseURL);
      
      rUserStore.authenticate("authenticateRequest.xml");
      rUserStore.addRole("addRoleRequest_Creator.xml");
      rUserStore.addUser("addUserRequest_Publisher.xml");
      
  }
	
  @Test
  public void tenantsManagement() throws Exception {
      
      TenantManagement tManager = new TenantManagement(accessToken,baseURL);
      
      tManager.createTenants("request.xml");
      tManager.deactivateTenants("deactivateTenantSoapRequest.xml");
      tManager.retrieveTenants("retrieveTenantsRequest.xml");
      tManager.checkDomainAvailability("checkDomainAvailabilityReques.xml");
      tManager.deleteTenants("deleteTenantSoapRequest.xml");
      
  }

//	
//	@Test
//    public void revokeApplicationData() throws Exception {
//             
//            Response response=RestAssured.given()
//                    .relaxedHTTPSValidation()
//                    .auth()
//                    .basic("admin", "admin")
//                    .header("SOAPAction","urn:getAllOAuthApplicationData")
//                    .contentType("text/xml; charset=UTF-8;")
//                    .body(getXMLPayload("revokeAuthApplicationRequest.xml"))
//                    .when()
//                    .post("https://kavindudi:9443/services/OAuthAdminService.OAuthAdminServiceHttpsSoap11Endpoint?wsdl");
//             
//            XmlPath jsXpath= new XmlPath(response.asString());//Converting string into xml path to assert
//            String rate=jsXpath.getString("GetConversionRateResult");
//            System.out.println("[REVOKE APPLICATION DATA]: " +  rate);
//    }
//	
//		private String getXMLPayload(String tenantXmlFileName){
//			
//	         byte[] payloadplj1;
//	         String payloadpls1="";
//       	
//	         try {
//	       		payloadplj1 = Files.readAllBytes(Paths.get("./src/test/payloads/"+tenantXmlFileName));
//	       		payloadpls1 = new String(payloadplj1);
//	
//	           } catch (Exception e) {
//	           }
//	         
//	         return payloadpls1;
//			  
//		}
//	
//	
//	  static JSONObject getPayload(int apiIndex) {
//	
//			  byte[] payloadJson1;
//			  String payloadString1;
//			  String payload="";
//			  JSONObject jsonObject = new JSONObject();
//			
//			  try {
//				  JSONParser parser = new JSONParser();
//				  Object obj = parser.parse(new FileReader("./src/test/payloads/apicretion_payload.json"));
//			      jsonObject = (JSONObject) obj;
//			      jsonObject.put("name","PizzaShackAPI_"+String.valueOf(apiIndex));
//			      jsonObject.put("context", "pizza_"+String.valueOf(apiIndex));
//			      payload = jsonObject.toString();
//			  } catch (Exception e) {
//				  
//			  }
//				  return jsonObject;
//	  
//	}
	  
	//-----------------------------------------------------------------------------------------------------
	  
//	@Test
//	public void devPortalDataPopulation() {
//		DevPortal.Apis devApis = new DevPortal.Apis(accessToken, ApimVersions.APIM_3_2);
//		Response searchApiRes = devApis.searchApis();
//		
//		System.out.println(searchApiRes.jsonPath().prettify());
//	}
}
