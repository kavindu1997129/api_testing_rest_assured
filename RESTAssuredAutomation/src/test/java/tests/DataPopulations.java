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
	
//	@Test
//	public void createTenants() throws Exception {
//	         
//	        Response response=RestAssured.given()
//	                .relaxedHTTPSValidation()
//	                .auth()
//	                .basic("admin", "admin")
//	                .header("SOAPAction","urn:addTenant")
//	                .contentType("text/xml; charset=UTF-8;")
//	                .body(getXMLPayload("request.xml"))
//	                .when()
//	                .post("https://localhost:9443/services/TenantMgtAdminService.TenantMgtAdminServiceHttpsSoap11Endpoint");
//	         
//	        XmlPath jsXpath= new XmlPath(response.asString());//Converting string into xml path to assert
//	        String rate=jsXpath.getString("GetConversionRateResult");
//	        System.out.println("rate returned is: " +  rate);
//	}
//	
	
	
//	@Test
//	public void deactvateTenants() throws Exception {
//	         
//	        Response response=RestAssured.given()
//	                .relaxedHTTPSValidation()
//	                .auth()
//	                .basic("admin", "admin")
//	                .header("SOAPAction","urn:deactivateTenant")
//	                .contentType("application/soap+xml; charset=UTF-8;")
//	                .body(getXMLPayload("deactivateTenantSoapRequest.xml"))
//	                .when()
//	                .post("https://localhost:9443/services/TenantMgtAdminService.TenantMgtAdminServiceHttpsSoap12Endpoint");
//	         
//	        XmlPath jsXpath= new XmlPath(response.asString());//Converting string into xml path to assert
//	        String rate=jsXpath.getString("GetConversionRateResult");
//	        System.out.println("[DEACTIVATE TENENT RESPONSE]: " +  rate);
//	}
	
  @Test
  public void tenantsManagement() throws Exception {
      
//      RemoteUserStore userManager = new RemoteUserStore();
//      userManager.authenticate("authenticateRequest.xml");
      
      TenantManagement tManager = new TenantManagement(accessToken,baseURL.toString());
      tManager.retrieveTenants("retrieveTenantsRequest.xml");
      
  }
  
//	@Test
//  public void retrieveTenants() throws Exception {
//           
//          Response response=RestAssured.given()
//                  .relaxedHTTPSValidation()
//                  .auth()
//                  .basic("admin", "admin")
//                  .header("SOAPAction","urn:retrieveTenants")
//                  .contentType("text/xml; charset=UTF-8;")
//                  .body(getXMLPayload("retrieveTenantsRequest.xml"))
//                  .when()
//                  .post("https://kavindudi:9443/services/TenantMgtAdminService.TenantMgtAdminServiceHttpsSoap11Endpoint");
//           
//          XmlPath jsXpath= new XmlPath(response.asString());//Converting string into xml path to assert
//          String rate=jsXpath.getString("GetConversionRateResult");
//          System.out.println("[RETRIVE TENENT RESPONSE]: " +  rate);
//  }
//	
	
	
//	   @Test
//	   public void checkDomainAvailability() throws Exception {
//	            
//	           Response response=RestAssured.given()
//	                   .relaxedHTTPSValidation()
//	                   .auth()
//	                   .basic("admin", "admin")
//	                   .header("SOAPAction","urn:checkDomainAvailabilityRequest.xml")
//	                   .contentType("text/xml; charset=UTF-8;")
//	                   .body(getXMLPayload("checkDomainAvailabilityReques.xml"))
//	                   .when()
//	                   .post("https://kavindudi:9443/services/TenantMgtAdminService.TenantMgtAdminServiceHttpsSoap12Endpoint");
//	            
//	           XmlPath jsXpath= new XmlPath(response.asString());//Converting string into xml path to assert
//	           String rate=jsXpath.getString("GetConversionRateResult");
//	           System.out.println("[CHECK DOMAIN AVAILABILITY]: " +  rate);
//	   }
	
	
//	@Test
//	public void deleteTenants() throws Exception {
//	         
//	        Response response=RestAssured.given()
//	                .relaxedHTTPSValidation()
//	                .auth()
//	                .basic("admin", "admin")
//	                .header("SOAPAction","urn:deleteTenant")
//	                .contentType("application/soap+xml; charset=UTF-8;")
//	                .body(getXMLPayload("deleteTenantSoapRequest.xml"))
//	                .when()
//	                .post("https://kavindudi:9443/services/TenantMgtAdminService.TenantMgtAdminServiceHttpsSoap12Endpoint?wsdl");
//	         
//	        XmlPath jsXpath= new XmlPath(response.asString());//Converting string into xml path to assert
//	        String rate=jsXpath.getString("GetConversionRateResult");
//	        System.out.println("[DELETE TENENT RESPONSE]: " +  rate);
//	}
//	
//	@Test
//	public void createLifecycle() throws Exception {
//	         
//	        Response response=RestAssured.given()
//	                .relaxedHTTPSValidation()
//	                .auth()
//	                .basic("admin", "admin")
//	                .header("SOAPAction","urn:createLifecycle")
//	                .contentType("text/xml; charset=UTF-8;")
//	                .body(getXMLPayload("createLifecycleRequest.xml"))
//	                .when()
//	                .post("https://localhost:9443/services/LifeCycleManagementService.LifeCycleManagementServiceHttpsSoap11Endpoint");
//	         
//	        XmlPath jsXpath= new XmlPath(response.asString());//Converting string into xml path to assert
//	        String rate=jsXpath.getString("GetConversionRateResult");
//	        System.out.println("[CREATE LIFECYCLE]: " +  rate);
//	}
//	
//	@Test
//	public void changeLifecycle() throws Exception {
//	         
//	        Response response=RestAssured.given()
//	                .relaxedHTTPSValidation()
//	                .auth()
//	                .basic("admin", "admin")
//	                .header("SOAPAction","urn:updateLifecycle")
//	                .contentType("text/xml; charset=UTF-8;")
//	                .body(getXMLPayload("changeLifecycleRequest.xml"))
//	                .when()
//	                .post("https://kavindudi:9443/services/LifeCycleManagementService.LifeCycleManagementServiceHttpsSoap11Endpoint");
//	         
//	        XmlPath jsXpath= new XmlPath(response.asString());//Converting string into xml path to assert
//	        String rate=jsXpath.getString("GetConversionRateResult");
//	        System.out.println("[CHANGE LIFECYCLE]: " +  rate);
//	}
	
//	@Test
//	public void authenticate() throws Exception {
//	         
//	        Response response=RestAssured.given()
//	                .relaxedHTTPSValidation()
//	                .auth()
//	                .basic("admin", "admin")
//	                .header("SOAPAction","urn:authenticate")
//	                .contentType("text/xml; charset=UTF-8;")
//	                .body(getXMLPayload("authenticateRequest.xml"))
//	                .when()
//	                .post("https://localhost:9443/services/RemoteUserStoreManagerService?wsdl");
//	         
//	        XmlPath jsXpath= new XmlPath(response.asString());//Converting string into xml path to assert
//	        String rate=jsXpath.getString("GetConversionRateResult");
//	        System.out.println("[ADD ROLE 2]: " +  rate);
//	}
//	
//	@Test
//	public void addRole() throws Exception {
//	         
//	        Response response=RestAssured.given()
//	                .relaxedHTTPSValidation()
//	                .auth()
//	                .basic("admin", "admin")
//	                .header("SOAPAction","urn:addRole")
//	                .contentType("text/xml; charset=UTF-8;")
//	                .body(getXMLPayload("addRoleRequest_Creator.xml"))
//	                .when()
//	                .post("https://localhost:9443/services/RemoteUserStoreManagerService?wsdl");
//	         
//	        XmlPath jsXpath= new XmlPath(response.asString());//Converting string into xml path to assert
//	        String rate=jsXpath.getString("GetConversionRateResult");
//	        System.out.println("[ADD ROLE]: " +  rate);
//	}
//	
//	@Test
//	public void addUser() throws Exception {
//	         
//	        Response response=RestAssured.given()
//	                .relaxedHTTPSValidation()
//	                .auth()
//	                .basic("admin", "admin")
//	                .header("SOAPAction","urn:addUser")
//	                .contentType("text/xml; charset=UTF-8;")
//	                .body(getXMLPayload("addUserRequest_Publisher.xml"))
//	                .when()
//	                .post("https://localhost:9443/services/RemoteUserStoreManagerService?wsdl");
//	         
//	        XmlPath jsXpath= new XmlPath(response.asString());//Converting string into xml path to assert
//	        String rate=jsXpath.getString("GetConversionRateResult");
//	        System.out.println("[ADD USER]: " +  rate);
//	}
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
		private String getXMLPayload(String tenantXmlFileName){
			
	         byte[] payloadplj1;
	         String payloadpls1="";
       	
	         try {
	       		payloadplj1 = Files.readAllBytes(Paths.get("./src/test/payloads/"+tenantXmlFileName));
	       		payloadpls1 = new String(payloadplj1);
	
	           } catch (Exception e) {
	           }
	         
	         return payloadpls1;
			  
		}
	
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
