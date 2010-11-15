package edu.gatech.mfa.core;

import edu.gatech.mfa.extn.SecurityToken;

public class AuthenticationResult {

	private boolean status;
	private SecurityToken securityToken;
	
	public AuthenticationResult()
	{
		
	}
	public AuthenticationResult(boolean status)
	{
		this.status = status;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public SecurityToken getSecurityToken() {
		return securityToken;
	}
	public void setSecurityToken(SecurityToken securityToken) {
		this.securityToken = securityToken;
	}
}
