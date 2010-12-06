package com.profile.app;

import javax.servlet.http.HttpServletRequest;

import edu.gatech.mfa.controller.VelocityController;
import edu.gatech.mfa.core.VelocityModelAndView;

public class UnsuccessfulLoginController extends VelocityController {

	public String getTemplateFile() {
		return templateFile;
	}

	public void setTemplateFile(String templateFile) {
		this.templateFile = templateFile;
	}

	private String templateFile;
	
	@Override
	protected VelocityModelAndView processRequest(HttpServletRequest request)
			throws Exception {
		VelocityModelAndView mav = new VelocityModelAndView();
		mav.setTemplateName(templateFile);
		return mav;
	}

}
