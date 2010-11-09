package edu.gatech.mfa.extn;

import org.springframework.web.servlet.ModelAndView;

import edu.gatech.mfa.core.MFADataSource;

public interface MFAExtension {
	public String getName();
	public ModelAndView generateAuthPage(String username,MFADataSource dataSource) throws Exception;
	public boolean performMFA(UserCredential credential) throws Exception;
}
