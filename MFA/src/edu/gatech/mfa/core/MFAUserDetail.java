package edu.gatech.mfa.core;

public class MFAUserDetail {
	private String username;
	private String credential;
	private String salt;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCredential() {
		return credential;
	}
	public void setCredential(String credential) {
		this.credential = credential;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
}
