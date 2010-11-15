package edu.gatech.mfa.extn.phonefactor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.phonefactor.pfsdk.PFAuth;
import net.phonefactor.pfsdk.PFAuthResult;
import net.phonefactor.pfsdk.PFException;
import net.phonefactor.pfsdk.TimeoutException;

public class PhoneFactorAuthenticator {

	private String certificatePath;
	private String privateKey;
	private String defaultCountryCode;
	private String defaultUser;
	private PFAuth phoneFactorAuth ;
	private Log log = LogFactory.getLog(getClass());
	
	public void init() throws Exception
	{
		if(defaultCountryCode == null) defaultCountryCode = "1";
		if(defaultUser == null) defaultUser = "user";
		phoneFactorAuth = new PFAuth();
		phoneFactorAuth.initialize(certificatePath, privateKey);
		log.info("Successfully initialized phoneFactor authentication system");
	}
	public String getCertificatePath() {
		return certificatePath;
	}
	public void setCertificatePath(String certificatePath) {
		this.certificatePath = certificatePath;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public String getDefaultCountryCode() {
		return defaultCountryCode;
	}
	public void setDefaultCountryCode(String defaultCountryCode) {
		this.defaultCountryCode = defaultCountryCode;
	}
	public String getDefaultUser() {
		return defaultUser;
	}
	public void setDefaultUser(String defaultUser) {
		this.defaultUser = defaultUser;
	}
	
	public boolean authenticate(String phoneNumber)
	{
		PFAuthResult r = null;
		
		try
		{
			r = phoneFactorAuth.authenticate(defaultUser, defaultCountryCode, phoneNumber, null, null, null);
		}
		catch (net.phonefactor.pfsdk.SecurityException e)
		{
			log.error("BAD AUTH -- Security issue!");
			return false;
		}
		catch (TimeoutException e)
		{
			
			log.error("BAD AUTH -- Server timeout");
			return false;
		}
		catch (PFException e)
		{
			log.error("BAD AUTH -- PFAuth failed with a PFException");
			return false;
		}

		
		if (r.getAuthenticated())
		{
			log.info("PhoneFactorAuthentication successfull on phone number [" + phoneNumber + "]");
			return true;
		}
		else
		{
			
			log.info("PhoneFactorAuthentication failed on phone number [" + phoneNumber + "]");
			log.info("Call Status [" + phoneNumber + "] : " + r.getCallStatusString());
			
			switch(r.getCallStatus())
			{
				case PFAuthResult.CALL_STATUS_USER_HUNG_UP:
					System.out.println("User hung up on phone number [" + phoneNumber + "]");
					break;
			 
				case PFAuthResult.CALL_STATUS_PHONE_BUSY:
					System.out.println("I have detected that the phone was busy on phone number [" + phoneNumber + "]");
					break;
				case 3:
					log.info("ALERT: Possible attack failed authentication on phone number [" + phoneNumber + "]");
					break;
						
				}

			if (r.getMessageErrorId() != 0)
			{
				log.error("Message Error ID number [" + phoneNumber + "] " + r.getMessageErrorId());

				String messageError = r.getMessageError();
				
				if (messageError != null)
					log.error("Message Error number [" + phoneNumber + "] " + messageError);
			}
			return false;
		}
		
	}
}
