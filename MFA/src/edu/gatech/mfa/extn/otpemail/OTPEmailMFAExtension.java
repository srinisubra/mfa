package edu.gatech.mfa.extn.otpemail;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

import edu.gatech.mfa.core.MFADataSource;
import edu.gatech.mfa.core.MFAUserDetail;
import edu.gatech.mfa.core.VelocityModelAndView;
import edu.gatech.mfa.extn.MFAExtension;
import edu.gatech.mfa.extn.RequestParameterExtractor;
import edu.gatech.mfa.extn.SecurityState;
import edu.gatech.mfa.extn.TwoFactorSecurityState;
import edu.gatech.mfa.extn.UserCredential;
import edu.gatech.mfa.extn.otpemail.exception.InvalidNumberOfFactorException;

public class OTPEmailMFAExtension implements MFAExtension {

	private String name;
	private String authPageTemplate;
	private OTPGenerator generator;
	private Map<String, SecurityState> requestRegistry;
	private RequestParameterExtractor requestParameterExtractor;
	private EmailSender emailSender;
	private Log log = LogFactory.getLog(getClass());
	
	
	public EmailSender getEmailSender() {
		return emailSender;
	}
	public void setEmailSender(EmailSender emailSender) {
		this.emailSender = emailSender;
	}
	public String getAuthPageTemplate() {
		return authPageTemplate;
	}
	public void setAuthPageTemplate(String authPageTemplate) {
		this.authPageTemplate = authPageTemplate;
	}
	public OTPGenerator getGenerator() {
		return generator;
	}
	public void setGenerator(OTPGenerator generator) {
		this.generator = generator;
	}
	public Map<String, SecurityState> getRequestRegistry() {
		return requestRegistry;
	}
	public void setRequestRegistry(Map<String, SecurityState> requestRegistry) {
		this.requestRegistry = requestRegistry;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRequestParameterExtractor(
			RequestParameterExtractor requestParameterExtractor) {
		this.requestParameterExtractor = requestParameterExtractor;
	}
	public void init() throws Exception
	{
		requestRegistry = new HashMap<String, SecurityState>();
	}
	@Override
	public String getName() {

		return name;
	}

	@Override
	public boolean performMFA(UserCredential credential) throws Exception {
		String requestId = credential.getRequestId();
		SecurityState securityState = requestRegistry.remove(requestId);
		if(securityState == null) return false;
		boolean retVal = validateAllFactors(2, credential, securityState);
		
		return retVal;
	}
	
	public boolean validateAllFactors(int count,UserCredential credential,SecurityState securityState) throws InvalidNumberOfFactorException
	{
		boolean retVal = true;
		
		for(int i=0;i<count;i++)
		{
			retVal = retVal & credential.getFactor(i).equals(securityState.getFactor(i));
		}
		return retVal;
	}

	@Override
	public ModelAndView generateAuthPage(String username,
			MFADataSource dataSource, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VelocityModelAndView generateAuthPage(String username,
			MFADataSource dataSource, String requestId) throws Exception {
		MFAUserDetail userDetails = dataSource.getUser(username);
		SecurityState securityState = createSecurityState(userDetails, requestId);
		requestRegistry.put(requestId, securityState);
		
		sendOTP(dataSource, securityState);
		
		Map<String, String> model = createModel(userDetails, requestId);
		VelocityModelAndView mav = new VelocityModelAndView();
		mav.addAllObjects(model);
		mav.setTemplateName(authPageTemplate);
		return mav;
	}

	public void sendOTP(MFADataSource dataSource,SecurityState securityState) throws Exception
	{
		String emailId = dataSource.getEmailId(securityState.getUsername());
		String otp = (String) securityState.getFactor(1); // otp
		emailSender.sendMail(securityState.getUsername(),emailId, otp);
	}
	public SecurityState createSecurityState(MFAUserDetail userDetails, String requestId) throws InvalidNumberOfFactorException
	{
		SecurityState securityState = new TwoFactorSecurityState();
		securityState.setAuthenticated(false);
		securityState.setFactor(0, userDetails.getCredential());
		securityState.setFactor(1, generator.generateOTP());
		securityState.setId(requestId);
		securityState.setUsername(userDetails.getUsername());
		return securityState;
	}
	public Map<String, String> createModel(	MFAUserDetail userDetails, String requestId)  {
		
		Map<String, String> model = new HashMap<String, String>();
		model.put("username", userDetails.getUsername());
		model.put("requestId", requestId);
		model.put("salt", userDetails.getSalt());
		return model;
	}
	@Override
	public RequestParameterExtractor getRequestParameterExtractor() {
		
		return requestParameterExtractor;
	}

}
