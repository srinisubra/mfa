package com.profile.app;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import edu.gatech.mfa.controller.VelocityController;
import edu.gatech.mfa.core.VelocityModelAndView;

public class LogoutController extends VelocityController {

	private String templateName;
	private Log log = LogFactory.getLog(getClass());
	
	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	@Override
	protected VelocityModelAndView processRequest(HttpServletRequest request)
			throws Exception {
		
		request.getSession().removeAttribute("securityToken");
		log.info("removing securityToken and logging out");
		VelocityModelAndView mav = new VelocityModelAndView();
		mav.setTemplateName(templateName);
		return mav;
	}

}
