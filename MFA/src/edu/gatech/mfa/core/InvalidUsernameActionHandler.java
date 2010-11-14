package edu.gatech.mfa.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InvalidUsernameActionHandler {

	private String viewTemplate;
	private Log log = LogFactory.getLog(getClass());
	
	public VelocityModelAndView handleError(String username,MFADataSource dataSource) 
	{
		VelocityModelAndView retVal = new VelocityModelAndView();
		retVal.setTemplateName(viewTemplate);
		retVal.addObject("username", username);
		log.warn("Username [" + username + "] not found on the server");
		return retVal;
	}

	public String getViewTemplate() {
		return viewTemplate;
	}

	public void setViewTemplate(String viewTemplate) {
		this.viewTemplate = viewTemplate;
	}
}
