package edu.gatech.mfa.extn;

import edu.gatech.mfa.extn.otpemail.exception.InvalidNumberOfFactorException;

public abstract class UserCredential {

	private String username;
	private String requestId;
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String toString()
	{
		return username + ":" + requestId;
	}
	
	public abstract void setFactor(int factorNumber,Object factor)throws InvalidNumberOfFactorException;
	public abstract Object getFactor(int factorNumber)throws InvalidNumberOfFactorException;
}
