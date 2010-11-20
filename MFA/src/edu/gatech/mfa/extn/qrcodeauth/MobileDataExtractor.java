package edu.gatech.mfa.extn.qrcodeauth;

public interface MobileDataExtractor {
	
	public String getDeviceID(String userName);
	
	public String getDeviceOS(String userName);
	
	public String getSIMID(String userName);
	
	public String getDeviceType(String userName);
	

}
