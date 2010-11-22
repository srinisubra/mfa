package edu.gatech.mfautils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.http.auth.Credentials;

public class CryptiUtils {

	
	public static final String getMD5Hash(String pass){
		try {
			MessageDigest m = MessageDigest.getInstance("SHA-1");
			byte[] data = pass.getBytes(); 
			m.update(data,0,data.length);
			BigInteger i = new BigInteger(1,m.digest());
			System.out.println(i.toString(16));
			return i.toString(16);
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
	}
	
}
