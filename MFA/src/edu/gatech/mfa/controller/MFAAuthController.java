package edu.gatech.mfa.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import edu.gatech.mfa.core.MFAConfiguration;
import edu.gatech.mfa.extn.MFAExtension;
import edu.gatech.mfa.extn.SecurityToken;
import edu.gatech.mfa.extn.UserCredential;

	public class MFAAuthController implements Controller {
	private MFAConfiguration mfaConfiguration;
	private MFAExtension extension;
	
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
		boolean authStatus = extension.performMFA(credential);
		
		if(authStatus == true)
		{
			HttpSession session = request.getSession();
			session.setAttribute("securityToken", createSecurityToken(credential));
			return mfaConfiguration.getSuccessController().handleRequest(request, response);
		}
		else
		{
			return mfaConfiguration.getFailureController().handleRequest(request, response);
		}
		
	}
	
	public SecurityToken createSecurityToken(UserCredential credential)
	{
		SecurityToken token = new SecurityToken();
		token.setRequestId(credential.getRequestId());
		token.setRequestTime(new Date());
		token.setUsername(credential.getUsername());
		return token;
		
	}

}
