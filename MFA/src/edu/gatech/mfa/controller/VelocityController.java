package edu.gatech.mfa.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import edu.gatech.mfa.core.VelocityFactory;
import edu.gatech.mfa.core.VelocityModelAndView;

public abstract class VelocityController implements Controller {

	private VelocityEngine velocityEngine;
	private VelocityFactory velocityFactory;
	
	public void init()throws Exception
	{
		velocityEngine = velocityFactory.getVelocityEngine();
	}
	
	public VelocityFactory getVelocityFactory() {
		return velocityFactory;
	}

	public void setVelocityFactory(VelocityFactory velocityFactory) {
		this.velocityFactory = velocityFactory;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		VelocityModelAndView mav = processRequest(request);
		Template template = velocityEngine.getTemplate(mav.getTemplateName());
		template.merge(new VelocityContext(mav.getModel()), response.getWriter());
		return null;
	}
	
	protected abstract VelocityModelAndView processRequest(HttpServletRequest request) throws Exception;

}
