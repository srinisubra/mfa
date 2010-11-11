package edu.gatech.mfa.extn;

import java.util.Date;

import edu.gatech.mfa.extn.otpemail.exception.InvalidNumberOfFactorException;

public abstract class SecurityState {

	private String id;
	private String username;
	private Date requestTime;
	private boolean isAuthenticated = false;
	
	public boolean isAuthenticated() {
		return isAuthenticated;
	}
	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
	
	public abstract Object getFactor(int factorNumber) throws InvalidNumberOfFactorException;
	public abstract void setFactor(int factorNumber,Object value) throws InvalidNumberOfFactorException;
}
