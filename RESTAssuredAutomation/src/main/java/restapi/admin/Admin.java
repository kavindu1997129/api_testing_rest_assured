package restapi.admin;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import restapi.ApimVersions;
import restapi.ContentTypes;

public class Admin {
	
	public static class ApplicationPolicy_Collection{
		
		String accessToken;
        String endPoint;
        
        String publisherApisString = "/throttling";
        String resourceParenPath = "./src/test/payloads/";
        
    	public ApplicationPolicy_Collection(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            this.endPoint = endPoint;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_4_1");
                }
                
            } catch (Exception e) {
            }

    	}
    	
    	public Response getAllApplicationThrottlingPolicies(){
            Response getAllApplicationThrottlingPoliciesResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/policies/application");

            return getAllApplicationThrottlingPoliciesResponse;
        }
    	
    	public Response addApplicationThrottlingPolicies(String jsonPayloadPath) {
    		
    		byte[] payloadplj1;
            String payloadpls1="";
        	
        	try {
        		payloadplj1 = Files.readAllBytes(Paths.get(resourceParenPath+jsonPayloadPath));
        		payloadpls1 = new String(payloadplj1);

            } catch (Exception e) {
            }
            Response addApplicationThrottlingPoliciesResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .body(payloadpls1)
            .contentType(ContentTypes.APPLICATION_JSON)
            .post(endPoint+publisherApisString+"/policies/application");

            return addApplicationThrottlingPoliciesResponse;
        }
		
	}
	
	public static class ApplicationPolicy_Individual {
		
		String accessToken;
        String endPoint;
        
        String publisherApisString = "/throttling";
        String resourceParenPath = "./src/test/payloads/";
        
    	public ApplicationPolicy_Individual(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            this.endPoint = endPoint;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_4_1");
                }
                
            } catch (Exception e) {
            }

    	}
    	
    	public Response getApplicationThrottlingPolicy(String policyId){
            Response getAllApplicationThrottlingPoliciesResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/policies/application/"+policyId);

            return getAllApplicationThrottlingPoliciesResponse;
        }
    	
    	public Response deleteApplicationThrottlingPolicy(String policyId){
            Response deleteAllApplicationThrottlingPoliciesResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .delete(endPoint+publisherApisString+"/policies/application/"+policyId);

            return deleteAllApplicationThrottlingPoliciesResponse;
        }
    	
    	public Response updateApplicationThrottlingPolicy(String policyId, String jsonPayloadPath){
    		
    		byte[] payloadplj1;
            String payloadpls1="";
        	
        	try {
        		payloadplj1 = Files.readAllBytes(Paths.get(resourceParenPath+jsonPayloadPath));
        		payloadpls1 = new String(payloadplj1);

            } catch (Exception e) {
            }
    		
            Response updateAllApplicationThrottlingPoliciesResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .body(payloadpls1)
            .contentType(ContentTypes.APPLICATION_JSON)
            .put(endPoint+publisherApisString+"/policies/application/"+policyId);

            return updateAllApplicationThrottlingPoliciesResponse;
        }
		
	}
	
	public static class MediationPolicy_Collection{
		
		String accessToken;
        String endPoint;
        
        String publisherApisString = "/policies";
        String resourceParenPath = "./src/test/payloads/";
        
    	public MediationPolicy_Collection(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            this.endPoint = endPoint;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_4_1");
                }
                
            } catch (Exception e) {
            }

    	}
    	
    	public Response getAllGlobalMediationPolicies(){
    		
            Response getAllGlobalMediationPolicyResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .put(endPoint+publisherApisString+"/mediation");

            return getAllGlobalMediationPolicyResponse;
        }
    	
    	public Response addGlobalMediationPolicy(String jsonPayloadPath){
    		
    		byte[] payloadplj1;
            String payloadpls1="";
        	
        	try {
        		payloadplj1 = Files.readAllBytes(Paths.get(resourceParenPath+jsonPayloadPath));
        		payloadpls1 = new String(payloadplj1);

            } catch (Exception e) {
            }
    		
            Response addGlobalMediationPolicyResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .body(payloadpls1)
            .contentType(ContentTypes.APPLICATION_JSON)
            .put(endPoint+publisherApisString+"/mediation");

            return addGlobalMediationPolicyResponse;
        }
		
	}
	
	public static class MediationPolicy_Individual{
		
		String accessToken;
        String endPoint;
        
        String publisherApisString = "/policies";
        String resourceParenPath = "./src/test/payloads/";
        
    	public MediationPolicy_Individual(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_4_1");
                }
                
            } catch (Exception e) {
            }

    	}
		
    	public Response getGlobalMediationPolicy(String mediationPolicyId){
    		
            Response getAllGlobalMediationPolicyResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/mediation/"+mediationPolicyId);

            return getAllGlobalMediationPolicyResponse;
        }
    	
    	public Response deleteGlobalMediationPolicy(String mediationPolicyId){
    		
            Response deleteAllGlobalMediationPolicyResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .delete(endPoint+publisherApisString+"/mediation/"+mediationPolicyId);

            return deleteAllGlobalMediationPolicyResponse;
        }
    	
    	public Response updateGlobalMediationPolicy(String mediationPolicyId, String jsonPayloadPath){
    		
    		byte[] payloadplj1;
            String payloadpls1="";
        	
        	try {
        		payloadplj1 = Files.readAllBytes(Paths.get(resourceParenPath+jsonPayloadPath));
        		payloadpls1 = new String(payloadplj1);

            } catch (Exception e) {
            }
    		
            Response updateGlobalMediationPolicyResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .basePath(payloadpls1)
            .contentType(ContentTypes.APPLICATION_JSON)
            .put(endPoint+publisherApisString+"/mediation/"+mediationPolicyId);

            return updateGlobalMediationPolicyResponse;
        }
    	
	}
	
	public static class SubscriptionPolicy_Collection{
		
		String accessToken;
        String endPoint;
        
        String publisherApisString = "/policies";
        String resourceParenPath = "./src/test/payloads/";
        
    	public SubscriptionPolicy_Collection(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            this.endPoint = endPoint;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_4_1");
                }
                
            } catch (Exception e) {
            }

    	}
    	
    	public Response getAllSubscriptionThrottlingPolicies(){
    		
            Response getAllSubscriptionThrottlingPoliciesResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/policies/subscription");

            return getAllSubscriptionThrottlingPoliciesResponse;
        }
    	
    	public Response addSubscriptionThrottlingPolicy(String jsonPayloadPath){
    		
    		byte[] payloadplj1;
            String payloadpls1="";
        	
        	try {
        		payloadplj1 = Files.readAllBytes(Paths.get(resourceParenPath+jsonPayloadPath));
        		payloadpls1 = new String(payloadplj1);

            } catch (Exception e) {
            }
    		
            Response addSubscriptionThrottlingPolicyResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .basePath(payloadpls1)
            .contentType(ContentTypes.APPLICATION_JSON)
            .post(endPoint+publisherApisString+"/policies/subscription");

            return addSubscriptionThrottlingPolicyResponse;
        }
		
	}
	
	public static class SubscriptionPolicy_Individual{
		
		String accessToken;
        String endPoint;
        
        String publisherApisString = "/policies";
        String resourceParenPath = "./src/test/payloads/";
        
    	public SubscriptionPolicy_Individual(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_4_1");
                }
                
            } catch (Exception e) {
            }

    	}
		
    	public Response getSubscriptionPolicy(String policyId){
    		
            Response getSubscriptionPolicyResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/policies/subscription/"+policyId);

            return getSubscriptionPolicyResponse;
        }
    	
    	public Response deleteSubscriptionPolicy(String policyId){
    		
            Response deleteSubscriptionPolicyResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/policies/subscription/"+policyId);

            return deleteSubscriptionPolicyResponse;
        }
    	
    	public Response updateSubscriptionPolicy(String policyId, String jsonPayloadPath){
    		
    		byte[] payloadplj1;
            String payloadpls1="";
        	
        	try {
        		payloadplj1 = Files.readAllBytes(Paths.get(resourceParenPath+jsonPayloadPath));
        		payloadpls1 = new String(payloadplj1);

            } catch (Exception e) {
            }
    		
            Response updateSubscriptionPolicyResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .basePath(payloadpls1)
            .contentType(ContentTypes.APPLICATION_JSON)
            .put(endPoint+publisherApisString+"/policies/subscription/"+policyId);

            return updateSubscriptionPolicyResponse;
        }
		
	}
	
	public static class CustomRules_Collection{
		
		String accessToken;
        String endPoint;
        
        String publisherApisString = "/policies";
        String resourceParenPath = "./src/test/payloads/";
        
    	public CustomRules_Collection(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_4_1");
                }
                
            } catch (Exception e) {
            }

    	}
    	
    	public Response getAllCustomRules(){
    		
            Response getAllCustomRulesResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/policies/custom");

            return getAllCustomRulesResponse;
        }
    	
    	public Response addCustomRule(String jsonPayloadPath){
    		
    		byte[] payloadplj1;
            String payloadpls1="";
        	
        	try {
        		payloadplj1 = Files.readAllBytes(Paths.get(resourceParenPath+jsonPayloadPath));
        		payloadpls1 = new String(payloadplj1);

            } catch (Exception e) {
            }
    		
            Response addCustomRuleResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .basePath(payloadpls1)
            .contentType(ContentTypes.APPLICATION_JSON)
            .post(endPoint+publisherApisString+"/policies/custom");

            return addCustomRuleResponse;
        }
		
	}
	
	public static class CustomRules_Individual{
		
		String accessToken;
        String endPoint;
        
        String publisherApisString = "/policies";
        String resourceParenPath = "./src/test/payloads/";
        
    	public CustomRules_Individual(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_4_1");
                }
                
            } catch (Exception e) {
            }

    	}
		
    	public Response getCustomRule(String ruleId){
    		
            Response getCustomRuleResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/policies/custom/"+ruleId);

            return getCustomRuleResponse;
        }
    	
    	public Response deleteCustomRule(String ruleId){
    		
            Response deleteCustomRuleResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/policies/custom/"+ruleId);

            return deleteCustomRuleResponse;
        }
    	
    	public Response updateCustomRule(String policyId, String jsonPayloadPath){
    		
    		byte[] payloadplj1;
            String payloadpls1="";
        	
        	try {
        		payloadplj1 = Files.readAllBytes(Paths.get(resourceParenPath+jsonPayloadPath));
        		payloadpls1 = new String(payloadplj1);

            } catch (Exception e) {
            }
    		
            Response updateCustomRuleResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .basePath(payloadpls1)
            .contentType(ContentTypes.APPLICATION_JSON)
            .put(endPoint+publisherApisString+"/policies/custom/"+policyId);

            return updateCustomRuleResponse;
        }
		
	}
	
	public static class AdvancedPolicy_Collection{
		
		String accessToken;
        String endPoint;
        
        String publisherApisString = "/policies";
        String resourceParenPath = "./src/test/payloads/";
        
    	public AdvancedPolicy_Collection(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_4_1");
                }
                
            } catch (Exception e) {
            }

    	}
    	
    	public Response getAllAdvancedThrottlingPolicies(){
    		
            Response getAllAdvancedThrottlingPoliciesResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/policies/advanced");

            return getAllAdvancedThrottlingPoliciesResponse;
        }
    	
    	public Response addAdvancedThrottlingPolicy(String jsonPayloadPath){
    		
    		byte[] payloadplj1;
            String payloadpls1="";
        	
        	try {
        		payloadplj1 = Files.readAllBytes(Paths.get(resourceParenPath+jsonPayloadPath));
        		payloadpls1 = new String(payloadplj1);

            } catch (Exception e) {
            }
    		
            Response addAdvancedThrottlingPolicyResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .basePath(payloadpls1)
            .contentType(ContentTypes.APPLICATION_JSON)
            .post(endPoint+publisherApisString+"/policies/advanced");

            return addAdvancedThrottlingPolicyResponse;
        }
		
	}
	
	public static class AdvancedPolicy_Individual{
		
		String accessToken;
        String endPoint;
        
        String publisherApisString = "/policies";
        String resourceParenPath = "./src/test/payloads/";
        
    	public AdvancedPolicy_Individual(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_4_1");
                }
                
            } catch (Exception e) {
            }

    	}
		
    	public Response getAdvancedThrottlingPolicy(String policyId){
    		
            Response getAdvancedThrottlingPolicyResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/policies/advanced/"+policyId);

            return getAdvancedThrottlingPolicyResponse;
        }
    	
    	public Response deleteAdvancedThrottlingPolicy(String policyId){
    		
            Response deleteAdvancedThrottlingPolicyResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/policies/advanced/"+policyId);

            return deleteAdvancedThrottlingPolicyResponse;
        }
    	
    	public Response updateCustomRule(String policyId, String jsonPayloadPath){
    		
    		byte[] payloadplj1;
            String payloadpls1="";
        	
        	try {
        		payloadplj1 = Files.readAllBytes(Paths.get(resourceParenPath+jsonPayloadPath));
        		payloadpls1 = new String(payloadplj1);

            } catch (Exception e) {
            }
    		
            Response updateCustomRuleResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .basePath(payloadpls1)
            .contentType(ContentTypes.APPLICATION_JSON)
            .put(endPoint+publisherApisString+"/policies/advanced/"+policyId);

            return updateCustomRuleResponse;
        }
		
	}
	
	public static class Blacklist_Collection{
		
		String accessToken;
        String endPoint;
        
        String publisherApisString = "/policies";
        String resourceParenPath = "./src/test/payloads/";
        
    	public Blacklist_Collection(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_4_1");
                }
                
            } catch (Exception e) {
            }

    	}
    	
    	public Response getAllBlockingConditions(){
    		
            Response getAllBlockingConditionsResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/policies/blacklist");

            return getAllBlockingConditionsResponse;
        }
    	
    	public Response addBlockingConditions(String jsonPayloadPath){
    		
    		byte[] payloadplj1;
            String payloadpls1="";
        	
        	try {
        		payloadplj1 = Files.readAllBytes(Paths.get(resourceParenPath+jsonPayloadPath));
        		payloadpls1 = new String(payloadplj1);

            } catch (Exception e) {
            }
    		
            Response addBlockingConditionsResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .basePath(payloadpls1)
            .contentType(ContentTypes.APPLICATION_JSON)
            .post(endPoint+publisherApisString+"/policies/blacklist");

            return addBlockingConditionsResponse;
        }
		
	}
	
	public static class Blacklist_Individual{
		
		String accessToken;
        String endPoint;
        
        String publisherApisString = "/policies";
        String resourceParenPath = "./src/test/payloads/";
        
    	public Blacklist_Individual(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_4_1");
                }
                
            } catch (Exception e) {
            }

    	}
		
    	public Response getCustomRule(String conditionId){
    		
            Response getCustomRuleResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/policies/blacklist/"+conditionId);

            return getCustomRuleResponse;
        }
    	
    	public Response deleteCustomRule(String conditionId){
    		
            Response deleteCustomRuleResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/policies/blacklist/"+conditionId);

            return deleteCustomRuleResponse;
        }
    	
    	public Response updateCustomRule(String conditionId, String jsonPayloadPath){
    		
    		byte[] payloadplj1;
            String payloadpls1="";
        	
        	try {
        		payloadplj1 = Files.readAllBytes(Paths.get(resourceParenPath+jsonPayloadPath));
        		payloadpls1 = new String(payloadplj1);

            } catch (Exception e) {
            }
    		
            Response updateCustomRuleResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .basePath(payloadpls1)
            .contentType(ContentTypes.APPLICATION_JSON)
            .patch(endPoint+publisherApisString+"/policies/blacklist/"+conditionId);

            return updateCustomRuleResponse;
        }
		
	}
	
	public static class Application_Collection{
		
		String accessToken;
        String endPoint;
        
        String publisherApisString = "/applications";
        String resourceParenPath = "./src/test/payloads/";
        
    	public Application_Collection(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_4_1");
                }
                
            } catch (Exception e) {
            }

    	}
    	
    	public Response searchApplications(){
    		
            Response searchApplicationsResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString);

            return searchApplicationsResponse;
        }
		
	}
	
	public static class Applications{
		
		String accessToken;
        String endPoint;
        
        String publisherApisString = "/applications";
        String resourceParenPath = "./src/test/payloads/";
        
    	public Applications(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_4_1");
                }
                
            } catch (Exception e) {
            }

    	}
    	
    	public Response deleteApplication(String applicationId, String owner){
    		
            Response deleteApplicationResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .delete(endPoint+publisherApisString+"/"+applicationId+"/change-owner?owner="+owner);

            return deleteApplicationResponse;
        }
		
	}
	
	public static class Application{
		
		String accessToken;
        String endPoint;
        
        String publisherApisString = "/applications";
        String resourceParenPath = "./src/test/payloads/";
        
    	public Application(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_4_1");
                }
                
            } catch (Exception e) {
            }

    	}
    	
    	public Response changeApplicationOwner(String applicationId, String owner){
    		
            Response changeApplicationOwnerResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .post(endPoint+publisherApisString+"/"+applicationId+"/change-owner?owner="+owner);

            return changeApplicationOwnerResponse;
        }
		
	}
	
	public static class Application_Individual{
		
		String accessToken;
        String endPoint;
        
        String publisherApisString = "/export";
        String resourceParenPath = "./src/test/payloads/";
        
    	public Application_Individual(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_4_1");
                }
                
            } catch (Exception e) {
            }

    	}
    	
    	public Response exportApplication(String appName, String appOwner, boolean withKeys){
    		
            Response exportApplicationResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+"/export/applications?appNAme="+appName+"&owner="+appOwner+"&withKeys="+withKeys);

            return exportApplicationResponse;
        }
    	
    	public Response importApplication(String exportedApplicationZipPath, Boolean preserveOwner, String appOwner, boolean skipSubscription, boolean skipApplicationKey, boolean update){
    		
            Response importApplicationResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .multiPart("file",new File(resourceParenPath+exportedApplicationZipPath))
            .post(endPoint+"/import/applications?preserveOwner="+preserveOwner+"&skipSubscriptions="+skipSubscription+"&appOwner="+appOwner+"&skipApplicationKeys="+skipApplicationKey+"&update="+update);

            return importApplicationResponse;
        }
		
	}
	
	public static class Api_Individual{
		
		String accessToken;
        String endPoint;
        
        String publisherApisString = "/export";
        String resourceParenPath = "./src/test/payloads/";
        
    	public Api_Individual(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_4_1");
                }
                
            } catch (Exception e) {
            }

    	}
    	
    	public Response importApi(String api, boolean preserveOwner, boolean overwrite){
    		
            Response importApiResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .multiPart("file",new File(resourceParenPath+api))
            .post(endPoint+"/import/api?preserveOwner="+preserveOwner+"&overwrite="+overwrite);

            return importApiResponse;
        }
    	
    	public Response exportApi(String name, String version, String providerName, String format, boolean preserveStatus){
    		
            Response exportApiResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .get(endPoint+"/export/api?name="+name+"&version="+version+"&providerName="+providerName+"&format="+format+"&preserveStatus="+preserveStatus);

            return exportApiResponse;
        }
		
	}
	
	public static class Api_Product_Individual{
		
		String accessToken;
        String endPoint;
        
        String publisherApisString = "/export";
        String resourceParenPath = "./src/test/payloads/";
        
    	public Api_Product_Individual(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_4_1");
                }
                
            } catch (Exception e) {
            }

    	}
    	
    	public Response importApiProduct(String exportApiProductPath, boolean preserveProvider, boolean overwriteAPIProduct, boolean overwriteAPIs, boolean importAPIs){
    		
            Response importApiResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .multiPart("file",new File(resourceParenPath+exportApiProductPath))
            .post(endPoint+"/import/api-product?preserveProvider="+preserveProvider+"&overwriteAPIProduct="+overwriteAPIProduct+"&overwriteAPIs="+overwriteAPIs+"&importAPIs="+importAPIs);

            return importApiResponse;
        }
    	
    	public Response exportApiProduct(String name, String version, String providerName, String format, boolean preserveStatus){
    		
            Response exportApiProductResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .get(endPoint+"/export/api-product?name="+name+"&version="+version+"&providerName="+providerName+"&format="+format+"&preserveStatus="+preserveStatus);

            return exportApiProductResponse;
        }
		
	}
	
	public static class LabelCollection{
		
		String accessToken;
        String endPoint;
        
        String publisherApisString = "/labels";
        String resourceParenPath = "./src/test/payloads/";
        
    	public LabelCollection(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_4_1");
                }
                
            } catch (Exception e) {
            }

    	}
    	
    	public Response getAllRegisteredLabels(){
    		
            Response getAllRegisteredLabelsResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .post(endPoint+publisherApisString);

            return getAllRegisteredLabelsResponse;
        }
    	
		
	}
	
	public static class Label{
		
		String accessToken;
        String endPoint;
        
        String publisherApisString = "/labels";
        String resourceParenPath = "./src/test/payloads/";
        
    	public Label(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_4_1");
                }
                
            } catch (Exception e) {
            }

    	}
		
    	public Response addLabel(String jsonPayloadPath){
    		
    		byte[] payloadplj1;
            String payloadpls1="";
        	
        	try {
        		payloadplj1 = Files.readAllBytes(Paths.get(resourceParenPath+jsonPayloadPath));
        		payloadpls1 = new String(payloadplj1);

            } catch (Exception e) {
            }
    		
            Response addLabelResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .body(payloadpls1)
            .post(endPoint+publisherApisString);

            return addLabelResponse;
        }
    	
    	public Response updateLabel(String labelId, String jsonPayloadPath){
    		
    		byte[] payloadplj1;
            String payloadpls1="";
        	
        	try {
        		payloadplj1 = Files.readAllBytes(Paths.get(resourceParenPath+jsonPayloadPath));
        		payloadpls1 = new String(payloadplj1);

            } catch (Exception e) {
            }
    		
            Response updateLabelResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .body(payloadpls1)
            .put(endPoint+publisherApisString+"/"+labelId);

            return updateLabelResponse;
        }
    	
    	public Response deleteLabel(String policyId){
    		
            Response deleteLabelResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/policies/advanced/"+policyId);

            return deleteLabelResponse;
        }
		
	}
	
	public static class BotDetectionData{
		
		String accessToken;
        String endPoint;
        
        String publisherApisString = "/bot-detection-data";
        String resourceParenPath = "./src/test/payloads/";
        
    	public BotDetectionData(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_4_1");
                }
                
            } catch (Exception e) {
            }

    	}
		
    	public Response getAllBotDetectedData(){
    		
            Response getAllBotDetectedDataResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .post(endPoint+publisherApisString);

            return getAllBotDetectedDataResponse;
        }
    	
		
	}
	
	public static class Monetization_Collection{
		
		String accessToken;
        String endPoint;
        
        String publisherApisString = "/monetization";
        String resourceParenPath = "./src/test/payloads/";
        
    	public Monetization_Collection(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_4_1");
                }
                
            } catch (Exception e) {
            }

    	}
		
    	public Response publishUsageRecords(){
    		
            Response publishUsageRecordsResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .post(endPoint+publisherApisString+"/publish-usage");

            return publishUsageRecordsResponse;
        }
    	
    	public Response getStatusOfMonetizationUsageRecords(){
    		
            Response getStatusOfMonetizationUsageRecordsResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/publish-usage/status");

            return getStatusOfMonetizationUsageRecordsResponse;
        }
    	
		
	}
	
	public static class Workflow_Collection{
		
		String accessToken;
        String endPoint;
        
        String publisherApisString = "/workflows";
        String resourceParenPath = "./src/test/payloads/";
        
    	public Workflow_Collection(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_4_1");
                }
                
            } catch (Exception e) {
            }

    	}
		
    	public Response getAllPendingWorkflowProcesses(){
    		
            Response getAllPendingWorkflowProcessesResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString);

            return getAllPendingWorkflowProcessesResponse;
        }
		
	}
	
	public static class Workflow_Individual{
		
		String accessToken;
        String endPoint;
        
        String publisherApisString = "/workflows";
        String resourceParenPath = "./src/test/payloads/";
        
    	public Workflow_Individual(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_4_1");
                }
                
            } catch (Exception e) {
            }

    	}
		
    	public Response getPendingWorkflowDetails(String externalWorkflowRef){
    		
            Response getPendingWorkflowDetailsResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/"+externalWorkflowRef);

            return getPendingWorkflowDetailsResponse;
        }
    	
    	public Response updateWorkflowStatus(String externalWorkflowRef, String workflowReferenceId, String jsonPayloadPath){
    		
    		byte[] payloadplj1;
            String payloadpls1="";
        	
        	try {
        		payloadplj1 = Files.readAllBytes(Paths.get(resourceParenPath+jsonPayloadPath));
        		payloadpls1 = new String(payloadplj1);

            } catch (Exception e) {
            }
    		
            Response updateWorkflowStatusResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .body(payloadpls1)
            .post(endPoint+publisherApisString+"/update-workflow-status?workflowReferenceId="+workflowReferenceId);

            return updateWorkflowStatusResponse;
        }
		
	}
	
	public static class Tenants{
		
		String accessToken;
        String endPoint;
        
        String publisherApisString = "/tenant-info";
        String resourceParenPath = "./src/test/payloads/";
        
    	public Tenants(String accessToken, ApimVersions version) {
    		this.accessToken = accessToken;
            
            FileInputStream input;
    	    Properties properties;

            try {
                String path =  "./src/test/resources/config.properties";
    			properties = new Properties();
    			input = new FileInputStream(path);
    			properties.load(input);
                if(version == ApimVersions.APIM_3_2){
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_3_2");
                }
                else{
                    this.endPoint = properties.getProperty("base_url")+properties.getProperty("admin_url_4_1");
                }
                
            } catch (Exception e) {
            }

    	}
		
    	public Response getTenantIdOfUser(String userName){
    		
            Response getTenantIdOfUserResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+publisherApisString+"/"+userName);

            return getTenantIdOfUserResponse;
        }
    	
    	public Response getCustomUrlInfoOfTenantDomain(String tenantDomain){
    		
            Response getCustomUrlInfoOfTenantDomainResponse  = RestAssured.given()
            .relaxedHTTPSValidation()
            .auth()
            .oauth2(accessToken)
            .contentType(ContentTypes.APPLICATION_JSON)
            .get(endPoint+"/custom-urls/"+tenantDomain);

            return getCustomUrlInfoOfTenantDomainResponse;
        }
		
	}
	
	public static class ApiCategory_Collection{
		
	}
	
	public static class ApiCategory_Individual{
		
	}
	
	public static class Settings{
		
	}
	
	public static class Alerts{
		
	}
	
	public static class AlertSubscriptions{
		
	}	
	public static class BotDetectionAlertSubscriptions{
		
	}
	
	public static class SystemScopes{
		
	}
	
	public static class TenantTheme{
		
	}
	
	public static class KeyManager_Collection{
		
	}
	
	public static class KeyManager_Individual{
		
	}
	
	
	
}
