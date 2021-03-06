package com.junapp.pms.security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.junapp.pms.entity.User;
import com.junapp.pms.persistence.EntityDAO;

import sun.misc.BASE64Encoder;

public class PasswordHashUtils {
	
	public static String hash(String rawPassword, String salt) {
		KeySpec spec = new PBEKeySpec(rawPassword.toCharArray(), salt.getBytes(), 65536, 128);
		SecretKeyFactory keyFactory;
		try {
			keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = keyFactory.generateSecret(spec).getEncoded();
			BASE64Encoder enc = new BASE64Encoder();
			return enc.encode(hash);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException("Password hash failed.");
		}
	}
	
	public static boolean validateUser(String username, String password) {
		String hashedPaswordInput = hash(password, username);
		User userToLogin = EntityDAO.instance.getUserByName(username);
		
		return userToLogin != null ? userToLogin.getPasswordHash().equals(hashedPaswordInput) : false;
	}
}
