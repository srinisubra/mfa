package edu.gatech.mfa.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class MockSuccessController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse response) throws Exception {
		response.getWriter().write("Yay!! Successfully logged in ");
		return null;
	}

}
