package edu.gatech.mfa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import edu.gatech.mfa.core.MFAConfiguration;
import edu.gatech.mfa.core.MFAUtils;
import edu.gatech.mfa.extn.MFAExtension;

/**
 * Responsible for checking the username and delegating control to proper extension
 * @author ADMIN
 *
 */
public class MFAFrontController implements Controller{

	private MFAConfiguration mfaConfiguration;
	private Controller onFailure;
	private MFAExtension extension;

	public void init() throws Exception
	{
		extension = mfaConfiguration.getExtension();
	}
	
	public Controller getOnFailure() {
		return onFailure;
	}

	public void setOnFailure(Controller onFailure) {
		this.onFailure = onFailure;
	}

	public MFAConfiguration getMfaConfiguration() {
		return mfaConfiguration;
	}

	public void setMfaConfiguration(MFAConfiguration mfaConfiguration) {
		this.mfaConfiguration = mfaConfiguration;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String username = extension.getRequestParameterExtractor().getUsername(request);
		ModelAndView mav = null;
		if(mfaConfiguration.getDataSource().checkUser(username) == true)
		{
			mav = extension.generateAuthPage(username, mfaConfiguration.getDataSource(), MFAUtils.generateRequestId(username));
		}
		else
		{
			mav = onFailure.handleRequest(request, response);
		}
		return mav;
	}

}
