package quizweb;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
	
	public Encryption() {
		
	}
	
	/*
	 Given a byte[] array, produces a hex String,
	 such as "234a6f". with 2 chars for each byte in the array.
	 (provided code)
	*/
	private static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i=0; i<bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff;  // remove higher bits, sign
			if (val<16) buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}
	
	public String generateHashedPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			byte[] hexcode = md.digest(password.getBytes());
			String hashValue = hexToString(hexcode);
			return hashValue;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "exception";
	}
}
