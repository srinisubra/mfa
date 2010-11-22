package edu.gatech.mfa.extn.otpemail;

import edu.gatech.mfa.extn.qrcodeauth.CryptiUtils;

public class OTPGenerator {

	
	public String generateOTP()
	{
		String randomNumber = (new Double(Math.random() * 10000000000d)).toString();
		String retVal = CryptiUtils.getMD5Hash(randomNumber);
		return retVal.substring(0, 10);
	}
}
