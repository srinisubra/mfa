package edu.gatech.mfautils;


public class GenerateKey{
	
	public static int UNIQUE_ID_LENGHT = 8;
	
	/**
	 * Key generation algorithm
	 * */
	public static String GenerateUniqueID(String deviceID, String passworddHash, String scanResult) {
		// TODO Auto-generated constructor stub
		
		String appendedHash =  CryptiUtils.getMD5Hash(passworddHash+deviceID+scanResult);
		
		return appendedHash.substring(appendedHash.length()-UNIQUE_ID_LENGHT);
		
	}

}
