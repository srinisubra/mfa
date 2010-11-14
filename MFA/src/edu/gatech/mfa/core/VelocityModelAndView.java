package edu.gatech.mfa.core;

import org.springframework.web.servlet.ModelAndView;

public class VelocityModelAndView extends ModelAndView{

	private String templateName;
	
	
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
}
