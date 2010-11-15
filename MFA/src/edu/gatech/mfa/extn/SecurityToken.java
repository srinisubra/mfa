package edu.gatech.mfa.extn;

import java.util.Date;

public class SecurityToken {
	
	private String tokenId;
	private String username;
	private Date requestTime;
	private String requestId;
	
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
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
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String toString()
	{
		return username + ":" + requestId + ":" + requestTime + ":" + tokenId;
	}
}
