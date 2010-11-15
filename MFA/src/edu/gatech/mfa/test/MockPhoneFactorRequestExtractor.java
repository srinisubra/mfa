package edu.gatech.mfa.test;

import javax.servlet.http.HttpServletRequest;

import edu.gatech.mfa.extn.TwoFactorUserCredential;
import edu.gatech.mfa.extn.UserCredential;

public class MockPhoneFactorRequestExtractor extends MockParameterExtractor {

	@Override
	public UserCredential getCredential(HttpServletRequest request)
			throws Exception {
		UserCredential credential = new TwoFactorUserCredential();
		credential.setUsername(request.getParameter("username"));
		credential.setFactor(0, request.getParameter("password"));
		credential.setRequestId(request.getParameter("requestId"));
		
		return credential;
	}
}
