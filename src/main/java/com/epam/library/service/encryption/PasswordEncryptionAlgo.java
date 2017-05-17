package com.epam.library.service.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.epam.library.service.exception.ServiceException;

public class PasswordEncryptionAlgo {

	private static final String ALGORITHM = "SHA";
	private static final String APPEND_ZERO = "0";

	/*
	 * Used encryption for saving password in database by creating another
	 * simple web application, Now using same hashing algo to encrypt the
	 * password entered
	 */
	public static byte[] encryptedPassword(byte[] plainText) throws ServiceException {
		try {
			MessageDigest md = null;

			md = MessageDigest.getInstance(ALGORITHM);

			md.reset();
			md.update(plainText);
			byte[] encodedPassword = md.digest();
			return encodedPassword;
		} catch (NoSuchAlgorithmException nse) {
			throw new ServiceException(nse);
		}

	}

	public static StringBuilder createSb(byte[] encryptedPassword) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < encryptedPassword.length; i++) {
			if ((encryptedPassword[i] & 0xff) < 0x10) {
				sb.append(APPEND_ZERO);
			}

			sb.append(Long.toString(encryptedPassword[i] & 0xff, 16));
		}
		return sb;
	}
}
