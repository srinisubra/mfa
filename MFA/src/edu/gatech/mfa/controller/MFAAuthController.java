package edu.gatech.mfa.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import edu.gatech.mfa.core.AuthenticationResult;
import edu.gatech.mfa.core.MFAConfiguration;
import edu.gatech.mfa.extn.MFAExtension;
import edu.gatech.mfa.extn.SecurityToken;
import edu.gatech.mfa.extn.UserCredential;

	public class MFAAuthController implements Controller {
	private MFAConfiguration mfaConfiguration;
	private MFAExtension extension;
	private Log log = LogFactory.getLog(getClass());
	
	public MFAConfiguration getMfaConfiguration() {
		return mfaConfiguration;
	}

	public void setMfaConfiguration(MFAConfiguration mfaConfiguration) {
		this.mfaConfiguration = mfaConfiguration;
	}
	
	public void init() throws Exception
	{
		extension = mfaConfiguration.getExtension();
	}
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserCredential credential = extension.getRequestParameterExtractor().getCredential(request);
		log.info("Validating credential =[" + credential + "]");
		AuthenticationResult authResult = extension.performMFA(credential);
		
		if(authResult.isStatus() == true)
		{
			log.info("MFA Successful for [" + credential + "]");
			HttpSession session = request.getSession();
			
			log.info("Configuring session with securityToken [" + authResult.getSecurityToken() + "]");
			session.setAttribute("securityToken",authResult.getSecurityToken());
			
			return mfaConfiguration.getSuccessController().handleRequest(request, response);
		}
		else
		{
			log.info("MFA Failed. Redirecting to error page ...");
			return mfaConfiguration.getFailureController().handleRequest(request, response);
		}
		
	}
	
	

}
