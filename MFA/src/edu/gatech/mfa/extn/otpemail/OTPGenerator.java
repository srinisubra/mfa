package edu.gatech.mfa.extn.otpemail;

public class OTPGenerator {

	// TODO : implment this
	public String generateOTP()
	{
		return (new Double(Math.random() * 10000000000d)).toString();
	}
}
