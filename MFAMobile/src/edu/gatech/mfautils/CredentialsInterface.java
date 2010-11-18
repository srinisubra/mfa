package edu.gatech.mfautils;



public interface CredentialsInterface {
	
	public String getDeviceID();

	public String getPasswordHashFromDataSource(String username);
	
	public String getToken();
	
	public void setToken(String token);
	public void setDeviceID(String deviceID);
	public void setPassword(String password);

}
