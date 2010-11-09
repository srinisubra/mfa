package edu.gatech.mfa.core;

public interface MFADataSource {

	public boolean checkUser(String username);
	public MFAUserDetail getUser(String username) throws Exception;
	public String getMobileNumber(String username) throws Exception;
	public String emailId(String username) throws Exception;
}
