package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ataf.actions.BaseTest;
import io.restassured.response.Response;
import restapi.ApimVersions;
import restapi.Authentication;
import restapi.ContentTypes;
import restapi.TenantAdmin;
import restapi.devportal.DevPortal;
import restapi.publisher.Publisher;
import soapapi.remoteuserstore.RemoteUserStore;
import soapapi.tenantmanagemant.TenantManagement;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataPopulations extends BaseTest{
    
  String accessToken;
  private static Logger logger = LogManager.getLogger(DataPopulations.class);
  
    
//  @Test
//  @Parameters({"authenticateRequest","addRoleRequest", "addUserRequest"})   
//  public void remoteUserStore(
//          String authenticateRequest, 
//          String addRoleRequest, 
//          String addUserRequest
//          ) throws Exception {
//      
//      authenticationObject.setUsername("admin");
//      authenticationObject.setUserpassword("admin");
//      Authentication authentication = new Authentication(authenticationObject);
//      accessToken = authentication.getAccessToken();
//      
//      RemoteUserStore rUserStore = new RemoteUserStore(accessToken,baseURL);
//      
//      TenantAdmin tenantAdmin = new TenantAdmin("test1_admin@test1_tenant.com", "test1_admin");
//      rUserStore.addRole(addRoleRequest,tenantAdmin);
//      rUserStore.addUser(addUserRequest,tenantAdmin);
//      
//      logger.info("[USER STORE]: User store related tests were completed");
//      
//  }
	
//  @Test
//  @Parameters({"createTenantRequest","deactivateTenantRequest", "retrieveTenantsRequest", "checkDomainAvailabilityRequest", "deleteTenantSoapRequest"})
//  public void tenantsManagement(
//          String createTenantRequest,
//          String deactivateTenantRequest,
//          String retrieveTenantsRequest,
//          String checkDomainAvailabilityRequest,
//          String deleteTenantSoapRequest
//          ) throws Exception {
//      
//      authenticationObject.setUsername("admin");
//      authenticationObject.setUserpassword("admin");
//      Authentication authentication = new Authentication(authenticationObject);
//      accessToken = authentication.getAccessToken();
//      
//      TenantManagement tManager = new TenantManagement(accessToken,baseURL);
//      TenantAdmin tenantAdmin = new TenantAdmin("admin", "admin");
//      
//      tManager.createTenants(createTenantRequest,tenantAdmin);
//      tManager.deactivateTenants(deactivateTenantRequest,tenantAdmin);
//      tManager.retrieveTenants(retrieveTenantsRequest,tenantAdmin);
//      tManager.checkDomainAvailability(checkDomainAvailabilityRequest,tenantAdmin);
//      tManager.deleteTenants(deleteTenantSoapRequest,tenantAdmin);
//      
//      logger.info("[TENANT MANAGMENT]: Tenant management related tests were completed");
//      
//  }
  
  @Test
  public void publisherPortal() throws InterruptedException {
      
      authenticationObject.setUsername("creator1_Test@test1_tenant.com");
      authenticationObject.setUserpassword("creator1_Test");
      
//      authenticationObject.setUsername("admin");
//      authenticationObject.setUserpassword("admin");
      Authentication authentication = new Authentication(authenticationObject);
      String accessToken1 = authentication.getAccessToken();
      
      Publisher.Apis api = new Publisher.Apis(accessToken1, ApimVersions.APIM_3_2);
      
      Response createApiRes = api.createApi(ContentTypes.APPLICATION_JSON, "apicretion_payload.json");
      logger.info("Status Code [CREATE API]: "+createApiRes.statusCode());
      
//      Response createApiOpenApiDefinitionRes = api.imporOpenAPIDefinition("createApiOpenApiDefinition.json", "apicretion_payload.json");
//      logger.info("Status Code [CREATE OPEN API DEFINITION]: "+createApiOpenApiDefinitionRes.statusCode());
      
      Response searchApiRes = api.searchApis();
      logger.info("Status Code [SEARCH API]: "+searchApiRes.statusCode());
      
      String apiId = searchApiRes.jsonPath().get("list[-2]['id']");
      logger.info("[SEARCHED API ID]: "+apiId);

      Response uploadApiThumbnailRes = api.uploadThumbnailImage("thumbnail2.jpg", apiId);
      logger.info("Status Code [UPLOAD API THUMBNAIL]: "+uploadApiThumbnailRes.statusCode());

      Response changeApiStatusRes = api.changeApiStatus(apiId, "Publish");
      logger.info("Status Code [CHANGE API STATUS]: "+changeApiStatusRes.statusCode());
      
      logger.info("[PUBLISHER PORTAL]: Dev Portal tests were completed");
      
  }
  
//  @Test
//  public void DevPortal() {
//      
//      authenticationObject.setUsername("creator1_Test@test1_tenant.com");
//      authenticationObject.setUserpassword("creator1_Test");
//      
////      authenticationObject.setUsername("admin");
////      authenticationObject.setUserpassword("admin");
//      
//      Authentication authentication = new Authentication(authenticationObject);
//      accessToken = authentication.getAccessToken();
//      
//      DevPortal.UnfiedSearch dSearch = new DevPortal.UnfiedSearch(accessToken, ApimVersions.APIM_3_2);
//      
//      Response searchApiByName = dSearch.getApiAndApiDocumentByContent("ABC");
////      String searchApiByNameRes = searchApiByName.jsonPath().prettify();
//      logger.info("Status Code [SEARCHED API BY NAME]: "+searchApiByName.jsonPath().prettify());
//      
//      DevPortal.Applications applications = new DevPortal.Applications(accessToken, ApimVersions.APIM_3_2);
//      
//      Response searchApplicationRes = applications.searchApplications();
//      logger.info("Status Code [SEARCH APPLICATION]: "+searchApplicationRes.statusCode());
//      
//      Response createNewApplicationRes = applications.createNewApplications("createNewApplication.json");
//      logger.info("Status Code [CREATE NEW APPLICATION]: "+createNewApplicationRes.statusCode());
//      
//      logger.info("[DEV PORTAL]: Dev Portal tests were completed");
//      
//      
//  }
	 
}
