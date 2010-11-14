package edu.gatech.mfa.test;

import javax.servlet.http.HttpServletRequest;

import edu.gatech.mfa.extn.RequestParameterExtractor;
import edu.gatech.mfa.extn.TwoFactorUserCredential;
import edu.gatech.mfa.extn.UserCredential;

public class MockParameterExtractor implements RequestParameterExtractor {

	@Override
	public String getUsername(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return request.getParameter("username");
	}

	@Override
	public UserCredential getCredential(HttpServletRequest request)
			throws Exception {
		UserCredential credential = new TwoFactorUserCredential();
		credential.setUsername(request.getParameter("username"));
		credential.setFactor(0, request.getParameter("password"));
		credential.setFactor(1, request.getParameter("token"));
		credential.setRequestId(request.getParameter("requestId"));
		
		return credential;
	}

}
