package edu.gatech.mfa.test;

import edu.gatech.mfa.core.MFADataSource;
import edu.gatech.mfa.core.MFAUserDetail;

public class MockDataSource implements MFADataSource{

	@Override
	public boolean checkUser(String username) {
		if(username.equals("admin"))
			return true;
			else
		return false;
	}

	@Override
	public MFAUserDetail getUser(String username) throws Exception {
		if(username.equals("admin"))
		{
			MFAUserDetail detail = new MFAUserDetail();
			detail.setUsername(username);
			detail.setSalt("12434531a3");
			detail.setCredential("admin");
			return detail;
		}
		else
		return null;
	}

	@Override
	public String getMobileNumber(String username) throws Exception {
		
		return "5712942818"; // change it
	}

	@Override
	public String getEmailId(String username) throws Exception {
		
		return "nadirsaghar@gmail.com";
	}

}
