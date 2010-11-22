package edu.gatech.mfa.core;

import edu.gatech.mfa.extn.qrcodeauth.CryptiUtils;

public class MFAUtils {

	public static String generateRequestId(String username)
	{
		String retVal = CryptiUtils.getMD5Hash(System.currentTimeMillis() + username);
		return retVal;
	}
}
