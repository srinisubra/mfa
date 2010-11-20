package edu.gatech.mfa.extn.qrcodeauth;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

import sun.org.mozilla.javascript.internal.ObjArray;

import edu.gatech.mfa.core.AuthenticationResult;
import edu.gatech.mfa.core.MFADataSource;
import edu.gatech.mfa.core.MFAUserDetail;
import edu.gatech.mfa.core.VelocityModelAndView;
import edu.gatech.mfa.extn.MFAExtension;
import edu.gatech.mfa.extn.RequestParameterExtractor;
import edu.gatech.mfa.extn.SecurityState;
import edu.gatech.mfa.extn.SecurityToken;
import edu.gatech.mfa.extn.TwoFactorSecurityState;
import edu.gatech.mfa.extn.UserCredential;
import edu.gatech.mfa.extn.otpemail.EmailSender;
import edu.gatech.mfa.extn.otpemail.exception.InvalidNumberOfFactorException;

public class QRCodeAuthExtension implements MFAExtension{

	private String name;
	private String authPageTemplate;
	
	
	private Map<String, SecurityState> requestRegistry;
	private RequestParameterExtractor requestParameterExtractor;
	private EmailSender emailSender;
	private MobileDataExtractor mobileData;
	private Log log = LogFactory.getLog(getClass());
	private String deviceID;
	
	
	
	
	
	public MobileDataExtractor getMobileData() {
		return mobileData;
	}

	public void setMobileData(MobileDataExtractor mobileData) {
		this.mobileData = mobileData;
	}
	
	public String getAuthPageTemplate() {
		return authPageTemplate;
	}
	public void setAuthPageTemplate(String authPageTemplate) {
		this.authPageTemplate = authPageTemplate;
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
	public AuthenticationResult performMFA(UserCredential credential) throws Exception {
		AuthenticationResult result = new AuthenticationResult(false);
		String requestId = credential.getRequestId();
		SecurityState securityState = requestRegistry.remove(requestId);
		if(securityState == null) return result;
		boolean retVal = validateAllFactors(2, credential, securityState);
		if(retVal == true)
		{
			SecurityToken token = createSecurityToken(securityState);
			result.setSecurityToken(token);
		}
		result.setStatus(retVal);
		return result;
	}
	
	public SecurityToken createSecurityToken(SecurityState credential)
	{
		SecurityToken token = new SecurityToken();
		token.setRequestId(credential.getId());
		token.setRequestTime(new Date());
		token.setUsername(credential.getUsername());
		return token;
		
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
		Object[] retVal = createSecurityState(userDetails, requestId);
		SecurityState securityState = (SecurityState) retVal[1];
		log.info("Created securityState for username [" + username + "] = [" + securityState+"]");
		requestRegistry.put(requestId, securityState);
		
		
		Map<String, String> model = createModel(userDetails, requestId, (String) retVal[0]);
		VelocityModelAndView mav = new VelocityModelAndView();
		mav.addAllObjects(model);
		mav.setTemplateName(authPageTemplate);
		return mav;
	}

	
	public Object[] createSecurityState(MFAUserDetail userDetails, String requestId) throws InvalidNumberOfFactorException
	{
		Object[] retVal = new Object[2];
		retVal[0] = Long.toString(System.currentTimeMillis());
		String qrCodeAuthHashKey = GenerateKey.GenerateUniqueID(mobileData.getDeviceID(userDetails.getUsername()), userDetails.getCredential(),(String)retVal[0] );
		SecurityState securityState = new TwoFactorSecurityState();
		securityState.setAuthenticated(false);
		securityState.setFactor(0, userDetails.getCredential());
		securityState.setFactor(1, qrCodeAuthHashKey);
		securityState.setId(requestId);
		securityState.setUsername(userDetails.getUsername());
		retVal[1] =securityState;
		return retVal;
	}
	public Map<String, String> createModel(	MFAUserDetail userDetails, String requestId, String qrToken)  {
		
		Map<String, String> model = new HashMap<String, String>();
		model.put("username", userDetails.getUsername());
		model.put("requestId", requestId);
		model.put("otpID", qrToken);
		model.put("salt", userDetails.getSalt());
		return model;
	}
	@Override
	public RequestParameterExtractor getRequestParameterExtractor() {
		
		return requestParameterExtractor;
	}

}
