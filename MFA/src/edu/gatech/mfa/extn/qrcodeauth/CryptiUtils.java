package edu.gatech.mfa.extn.qrcodeauth;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptiUtils {

	
	public static final String getMD5Hash(String pass){
		try {
			MessageDigest m = MessageDigest.getInstance("SHA-1");
			byte[] data = pass.getBytes(); 
			m.update(data,0,data.length);
			String retVal = prependZeroes(m.digest());
			return retVal;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
	}
	
	public static  String prependZeroes(byte[] bytes)
	{
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
		    String hex=Integer.toHexString(0xff & bytes[i]);
		    if(hex.length()==1) sb.append('0');
		    sb.append(hex);
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		System.out.println(getMD5Hash("amay_nadir798325"));
	}
	
}
