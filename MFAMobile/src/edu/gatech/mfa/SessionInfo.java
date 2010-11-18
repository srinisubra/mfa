package edu.gatech.mfa;

public class SessionInfo {
	
	
	public static String username = null;
	public static String passwordHash = null;
	public static String deviceID = null;
	public static Boolean isLoggedIn = false;
	public static Boolean barcodeInvoked = false;
	
	
	
	/**
	 * Function invoked when the login is successful
	 * 
	 * @param username and password's hash
	 * */
	public static void setInfo(String name, String pwdhash){
		username = name;
		passwordHash = pwdhash;
		isLoggedIn = true;
		
		System.out.println("Session Info Updates");
		System.out.println( username + passwordHash + isLoggedIn);
	}
	
	/**
	 * Function invoked when the application goes to background 
	 * (except for when the barcode activity is called from within 
	 * the application)
	 * */
	public static void reset(){
		username = null;
		passwordHash = null;
		isLoggedIn = false;
		System.out.println("Session Info erased");
	}
	

}
