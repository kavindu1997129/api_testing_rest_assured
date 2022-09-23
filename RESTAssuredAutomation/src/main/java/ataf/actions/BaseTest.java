package ataf.actions;
import java.net.URI;
import java.net.URISyntaxException;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import ataf.constants.ProgramConstants;
import restapi.Authentication;
import restapi.AuthenticationObject;
import restapi.ContentTypes;
import restapi.GrantTypes;
import restapi.Scopes; 


public class BaseTest {

	protected URI baseURL;
	protected String accessToken;
	protected int apiCount;
	
	
	@BeforeSuite
	@Parameters({"baseURL","apiCount"})	
	public void initiaization(String baseurlParm, int apiCount) {
		AuthenticationObject authenticationObject = new AuthenticationObject(); 
        authenticationObject.setUsername("admin");
        authenticationObject.setUserpassword("admin");
        authenticationObject.setEndpoint("https://localhost:9443/client-registration/v0.17/register");
        authenticationObject.setTokenUrl("https://localhost:8243/token"); 
        authenticationObject.setPayloadPath("./src/test/payloads/payload.json");
        authenticationObject.setScopes(Scopes.API_PUBLISH, Scopes.API_CREATE, Scopes.API_VIEW, Scopes.API_IMPORT_EXPORT, Scopes.API_MANAGE, Scopes.DOCUMENT_MANAGE);
        authenticationObject.setContentType(ContentTypes.APPLICATION_JSON);
        authenticationObject.setGrantType(GrantTypes.PASSSWORD);

        Authentication authentication = new Authentication(authenticationObject);
        accessToken = authentication.getAccessToken();
		
        this.apiCount = apiCount;
		
	}

	protected void bindBaseURL(String baseurlParameter) throws URISyntaxException {		
		if(baseurlParameter==null || baseurlParameter.isEmpty() || baseurlParameter.contains(ProgramConstants.TESTNG_PARM_VALUE_NOT_FOUND_MSG)) {
			baseURL = new URI(ProgramConstants.DEFAULT_BASE_URL);

		} else {
			baseURL = new URI(baseurlParameter);
		}

	}
}
