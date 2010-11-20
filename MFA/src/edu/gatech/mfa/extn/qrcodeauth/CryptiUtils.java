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
			BigInteger i = new BigInteger(1,m.digest());
			return String.format("%1$032X", i);
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
	}
	
	
}
