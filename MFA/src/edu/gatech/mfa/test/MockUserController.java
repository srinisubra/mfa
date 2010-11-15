package edu.gatech.mfa.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import edu.gatech.mfa.extn.SecurityToken;

public class MockUserController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		SecurityToken token = (SecurityToken) session.getAttribute("securityToken");
		response.getWriter().write("This is restricted content for " + token.getUsername());
		return null;
	}

}
