package edu.gatech.mfa.junit;

import java.io.FileInputStream;
import java.util.Properties;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import edu.gatech.mfa.controller.MFAAuthController;
import edu.gatech.mfa.controller.MFAFrontController;
import edu.gatech.mfa.extn.otpemail.OTPEmailMFAExtension;

public class TestFrontController extends TestCase {

	private ApplicationContext appContext;
	
	public TestFrontController()
	{
		appContext = new ClassPathXmlApplicationContext("hw-servlet.xml");
	}
//	public void testSimpleRequest() throws Exception
//	{
//		MockHttpServletRequest request = new MockHttpServletRequest();
//		MockHttpServletResponse response = new MockHttpServletResponse();
//		request.setParameter("username", "admin");
//		MFAFrontController controller = (MFAFrontController) appContext.getBean("frontController");
//		controller.handleRequest(request, response);
//		
//	}
	
	public void testEndToEnd() throws Exception
	{
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setParameter("username", "admin");
		MFAFrontController controller = (MFAFrontController) appContext.getBean("frontController");
		controller.handleRequest(request, response);
		
		// add more params to request
		request.setParameter("password", "admin");
		Properties props = new Properties();
		props.load(new FileInputStream("test_input.props"));
		request.setParameter("requestId", props.getProperty("requestId"));
		request.setParameter("token", props.getProperty("token"));
		
		MFAAuthController authController = (MFAAuthController) appContext.getBean("authController");
		authController.handleRequest(request, response);
		
	}
	
	
	
}
