package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ataf.actions.BaseTest;
import restapi.TenantAdmin;
import soapapi.remoteuserstore.RemoteUserStore;
import soapapi.tenantmanagemant.TenantManagement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataPopulations extends BaseTest{

  private static Logger logger = LogManager.getLogger(TestClasses.class);
  
    
  @Test
  @Parameters({"authenticateRequest","addRoleRequest", "addUserRequest"})   
  public void remoteUserStore(
          String authenticateRequest, 
          String addRoleRequest, 
          String addUserRequest
          ) throws Exception {
      
      RemoteUserStore rUserStore = new RemoteUserStore(accessToken,baseURL);
      
      TenantAdmin tenantAdmin = new TenantAdmin("test1_admin@test1_tenant.com", "test1_admin");
      rUserStore.addRole(addRoleRequest,tenantAdmin);
      rUserStore.addUser(addUserRequest,tenantAdmin);
      
      logger.info("[USER STORE]: User store related tests were completed");
      
  }
	
  @Test
  @Parameters({"createTenantRequest","deactivateTenantRequest", "retrieveTenantsRequest", "checkDomainAvailabilityRequest", "deleteTenantSoapRequest"})
  public void tenantsManagement(
          String createTenantRequest,
          String deactivateTenantRequest,
          String retrieveTenantsRequest,
          String checkDomainAvailabilityRequest,
          String deleteTenantSoapRequest
          ) throws Exception {
      
      TenantManagement tManager = new TenantManagement(accessToken,baseURL);
      TenantAdmin tenantAdmin = new TenantAdmin("admin", "admin");
      
      tManager.createTenants(createTenantRequest,tenantAdmin);
      tManager.deactivateTenants(deactivateTenantRequest,tenantAdmin);
      tManager.retrieveTenants(retrieveTenantsRequest,tenantAdmin);
      tManager.checkDomainAvailability(checkDomainAvailabilityRequest,tenantAdmin);
      tManager.deleteTenants(deleteTenantSoapRequest,tenantAdmin);
      
      logger.info("[TENANT MANAGMENT]: Tenant management related tests were completed");
      
  }
	 
}
