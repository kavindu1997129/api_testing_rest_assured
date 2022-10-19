package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ataf.actions.BaseTest;
import io.restassured.response.Response;
import restapi.ApimVersions;
import restapi.Authentication;
import restapi.ContentTypes;
import restapi.JsonReadWrite;
import restapi.TenantAdmin;
import restapi.devportal.DevPortal;
import restapi.publisher.Publisher;
import soapapi.remoteuserstore.RemoteUserStore;
import soapapi.tenantmanagemant.TenantManagement;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
  @Parameters({"tenantAdminUser","tenantAdminUserPassword", "apiCreationPayload",
      "createApiOpenApiDefinition","thumbnailImage","apiLifecycleStatusAction"})  
  public void publisherPortal(
          String tenantAdminUser, String tenantAdminUserPassword, String apiCreationPayload,
          String createApiOpenApiDefinition, String thumbnailImage, String apiLifecycleStatusAction
          ) throws InterruptedException {
      
      authenticationObject.setUsername(tenantAdminUser);
      authenticationObject.setUserpassword(tenantAdminUserPassword);
      
      Authentication authentication = new Authentication(authenticationObject);
      String accessToken1 = authentication.getAccessToken();
      
      Publisher.Apis api = new Publisher.Apis(accessToken1, ApimVersions.APIM_3_2);
      
//      Response createApiRes = api.createApi(ContentTypes.APPLICATION_JSON, apiCreationPayload);
//      logger.info("Status Code [CREATE API]: "+createApiRes.statusCode());
       
      Response createApiOpenApiDefinitionRes = api.imporOpenAPIDefinition(createApiOpenApiDefinition, apiCreationPayload);
      logger.info("Status Code [CREATE OPEN API DEFINITION]: "+createApiOpenApiDefinitionRes.statusCode());
      String apiId = createApiOpenApiDefinitionRes.jsonPath().get("id");
      if(apiId != null) JsonReadWrite.addApiToJson(apiId);
      
      Response searchApiRes = api.searchApis();
      logger.info("Status Code [SEARCH API]: "+searchApiRes.statusCode());

      Response uploadApiThumbnailRes = api.uploadThumbnailImage(thumbnailImage, JsonReadWrite.readApiId(0));
      logger.info("Status Code [UPLOAD API THUMBNAIL]: "+uploadApiThumbnailRes.statusCode());

      Response changeApiStatusRes = api.changeApiStatus(JsonReadWrite.readApiId(0), apiLifecycleStatusAction);
      logger.info("Status Code [CHANGE API STATUS]: "+changeApiStatusRes.statusCode());
      
      logger.info("[PUBLISHER PORTAL]: Dev Portal tests were completed");
      
  }
  
  
  
  @Test
  public void DevPortal() {
      
      authenticationObject.setUsername("creator1_Test@test1_tenant.com");
      authenticationObject.setUserpassword("creator1_Test");
      
      
      Authentication authentication = new Authentication(authenticationObject);
      accessToken = authentication.getAccessToken();
      
      DevPortal.UnfiedSearch dSearch = new DevPortal.UnfiedSearch(accessToken, ApimVersions.APIM_3_2);
      
//      Response searchApiByName = dSearch.getApiAndApiDocumentByContent("ABC");
//      logger.info("Status Code [SEARCHED API BY NAME]: "+searchApiByName.jsonPath().prettify());
      
      DevPortal.Applications applications = new DevPortal.Applications(accessToken, ApimVersions.APIM_3_2);
      
      Response searchApplicationRes = applications.searchApplications();
      logger.info("Status Code [SEARCH APPLICATION]: "+searchApplicationRes.statusCode());
      
      Response createNewApplicationRes = applications.createNewApplications("createNewApplication.json");
      logger.info("Status Code [CREATE NEW APPLICATION]: "+createNewApplicationRes.statusCode());
      String appId = createNewApplicationRes.jsonPath().get("applicationId");    
      if(appId != null) JsonReadWrite.addAppToJson(appId);
      
      logger.info("[DEV PORTAL]: Dev Portal tests were completed");
      
      
  }
  
  public void saveRuntimeData(JSONObject jsonDataObject) {
      
      try (FileWriter file = new FileWriter("./src/test/runtimeData/runtime.json")) {
          file.write(jsonDataObject.toJSONString()); 
          file.flush();

      } catch (IOException e) {
          e.printStackTrace();
      }
      
      
      
  }
  
}


