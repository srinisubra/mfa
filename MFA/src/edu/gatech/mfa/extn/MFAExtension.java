package edu.gatech.mfa.extn;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import edu.gatech.mfa.core.MFADataSource;

public interface MFAExtension {
	public String getName();
	public ModelAndView generateAuthPage(String username,MFADataSource dataSource,String requestId) throws Exception;
	public ModelAndView generateAuthPage(String username,MFADataSource dataSource,HttpSession session) throws Exception;
	public boolean performMFA(UserCredential credential) throws Exception;
	public RequestParameterExtractor getRequestParameterExtractor() ;
}
