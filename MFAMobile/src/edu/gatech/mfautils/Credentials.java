package edu.gatech.mfautils;

import edu.gatech.mfa.SessionInfo;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

public class Credentials implements CredentialsInterface {

	private static final String TAG = "MFAMobile";
	
	TelephonyManager telephonyManager;
	Context context;
	 
	
	public Credentials(Context context) {
		// TODO Auto-generated constructor stub
		
		this.context = context;
	}
	
	
	/**
	 * Generate identification based on the device type and device 
	 * IMEI/MEID number
	 * 
	 * NOTE: Change the implmentation of the funtion if the same is fetched
	 * on the server through a specific data source
	 * */
	
	@Override
	public String getDeviceID() {
		// TODO Auto-generated method stub
		
		
		String serviceName = Context.TELEPHONY_SERVICE;
		
		telephonyManager =  (TelephonyManager) context.getSystemService(serviceName);
		
		String devideID = telephonyManager.getDeviceId();
		
		if (TextUtils.isEmpty(devideID)) {
		   return "";
		}
		
		Log.i(TAG, "Device id: successfully returned");
		return devideID;

	}


	@Override
	public String getPasswordHashFromDataSource(String uname) {
		// TODO Auto-generated method stub
		Log.i(TAG, "PasswordHash from datastore: successfully returned");
		
		return CryptiUtils.getMD5Hash("admin");
	}


	@Override
	public String getToken() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setToken(String token) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setDeviceID(String deviceID) {
		// TODO Auto-generated method stub
		SessionInfo.deviceID = deviceID;
	}


	@Override
	public void setPassword(String passwordHash) {
		// TODO Auto-generated method stub
		
	}
	
private String token;


}
