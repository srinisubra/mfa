package edu.gatech.mfa.junit;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import edu.gatech.mfa.controller.MFAFrontController;

public class TestFrontController extends TestCase {

	private ApplicationContext appContext;
	
	public TestFrontController()
	{
		appContext = new ClassPathXmlApplicationContext("hw-servlet.xml");
	}
	public void testSimpleRequest() throws Exception
	{
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setParameter("username", "admin");
		MFAFrontController controller = (MFAFrontController) appContext.getBean("frontController");
		controller.handleRequest(request, response);
		
	}
}
