package edu.gatech.mfa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import edu.gatech.mfa.core.InvalidUsernameActionHandler;
import edu.gatech.mfa.core.MFAConfiguration;
import edu.gatech.mfa.core.MFAUtils;
import edu.gatech.mfa.core.VelocityModelAndView;
import edu.gatech.mfa.extn.MFAExtension;

/**
 * Responsible for checking the username and delegating control to proper extension
 * @author ADMIN
 *
 */
public class MFAFrontController extends VelocityController{

	private MFAConfiguration mfaConfiguration;
	private InvalidUsernameActionHandler onFailure;
	private MFAExtension extension;
	
	public InvalidUsernameActionHandler getOnFailure() {
		return onFailure;
	}


	public void setOnFailure(InvalidUsernameActionHandler onFailure) {
		this.onFailure = onFailure;
	}



	private Log log = LogFactory.getLog(getClass());

	public void init() throws Exception
	{
		super.init();
		extension = mfaConfiguration.getExtension();
	}
	
	
	public MFAConfiguration getMfaConfiguration() {
		return mfaConfiguration;
	}

	public void setMfaConfiguration(MFAConfiguration mfaConfiguration) {
		this.mfaConfiguration = mfaConfiguration;
	}

	

	@Override
	protected VelocityModelAndView processRequest(HttpServletRequest request)
			throws Exception {
		String username = extension.getRequestParameterExtractor().getUsername(request);
		log.info("Request received for username=[" + username + "]");
		VelocityModelAndView mav = null;
		if(mfaConfiguration.getDataSource().checkUser(username) == true)
		{
			log.info("User [" + username+"] exists on the system. processing login information ....");
			mav = extension.generateAuthPage(username, mfaConfiguration.getDataSource(), MFAUtils.generateRequestId(username));
		}
		else
		{
			log.info("User [" + username+"] does not exist on the system. Redirecting to error page ...");
			mav = onFailure.handleError(username,  mfaConfiguration.getDataSource());
		}
		return mav;
	}

}
