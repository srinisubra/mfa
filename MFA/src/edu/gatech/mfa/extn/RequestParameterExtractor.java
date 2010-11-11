package edu.gatech.mfa.extn;

import javax.servlet.http.HttpServletRequest;

public interface RequestParameterExtractor {

	public String getUsername(HttpServletRequest request) throws Exception;
	public UserCredential getCredential(HttpServletRequest request) throws Exception;
}
