package edu.gatech.mfa.core;

public class MFAUtils {

	public static String generateRequestId(String username)
	{
		// TODO: to implement
		return System.currentTimeMillis() + username;
	}
}
