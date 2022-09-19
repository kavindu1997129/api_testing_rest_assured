package ataf.actions;
import java.net.URI;
import java.net.URISyntaxException;

import ataf.constants.ProgramConstants;

public class BaseTest {

	protected URI baseURL;

	protected void bindBaseURL(String baseurlParameter) throws URISyntaxException {		
		if(baseurlParameter==null || baseurlParameter.isEmpty() || baseurlParameter.contains(ProgramConstants.TESTNG_PARM_VALUE_NOT_FOUND_MSG)) {
			baseURL = new URI(ProgramConstants.DEFAULT_BASE_URL);

		} else {
			baseURL = new URI(baseurlParameter);
		}

	}
}
