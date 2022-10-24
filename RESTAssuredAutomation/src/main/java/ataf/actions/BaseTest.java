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
	protected AuthenticationObject authenticationObject;
	
	
	@BeforeSuite
	@Parameters({"baseURL"})	
	public void initiaization(String baseurlParm) throws URISyntaxException {
		authenticationObject = new AuthenticationObject(); 
        bindBaseURL(baseurlParm);
		
	}

	protected void bindBaseURL(String baseurlParameter) throws URISyntaxException {		
		if(baseurlParameter==null || baseurlParameter.isEmpty() || baseurlParameter.contains(ProgramConstants.TESTNG_PARM_VALUE_NOT_FOUND_MSG)) {
			baseURL = new URI(ProgramConstants.DEFAULT_BASE_URL);

		} else {
			baseURL = new URI(baseurlParameter);
		}

	}
}
