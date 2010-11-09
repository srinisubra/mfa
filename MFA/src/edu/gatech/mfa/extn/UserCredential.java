package edu.gatech.mfa.extn;

public abstract class UserCredential {

	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public abstract void setFactor(int factorNumber,String factor);
	public abstract Object getFactor(int factorNumber);
}
