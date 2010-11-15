package edu.gatech.mfa.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import edu.gatech.mfa.extn.SecurityToken;

public class RestrictedAccessInterceptor extends HandlerInterceptorAdapter {

	public String getRedirectURLInCaseOfRestrictedAccess() {
		return redirectURLInCaseOfRestrictedAccess;
	}
	public void setRedirectURLInCaseOfRestrictedAccess(
			String redirectURLInCaseOfRestrictedAccess) {
		this.redirectURLInCaseOfRestrictedAccess = redirectURLInCaseOfRestrictedAccess;
	}
	private String redirectURLInCaseOfRestrictedAccess;
	private Log log = LogFactory.getLog(getClass());
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		SecurityToken token = (SecurityToken) session.getAttribute("securityToken");
		if(token !=null)
		{
			log.info("Incoming request validated result = passed");
			return true;
		}
		else
		{
			log.warn("Incoming request validated result = denied. Redirecting to [" + redirectURLInCaseOfRestrictedAccess + "]");
			response.sendRedirect(redirectURLInCaseOfRestrictedAccess);
			return false;
		}
		
	}

	
}
