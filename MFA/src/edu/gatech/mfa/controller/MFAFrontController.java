package edu.gatech.mfa.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import edu.gatech.mfa.core.MFAConfiguration;

/**
 * Responsible for checking the username and delegating control to proper extension
 * @author ADMIN
 *
 */
public class MFAFrontController implements Controller{

	private MFAConfiguration mfaConfiguration;
	private Controller onFailure;
	
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
		
		return null;
	}

}
