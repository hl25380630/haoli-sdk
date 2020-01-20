package com.haoli.sdk.web.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Base64;

public class AesSupport {

	private static final String KEY_ALGORITHM = "AES";

	private static final String SECRET = "yuki2020";

	private Cipher decryptCipher;

	private Cipher encryptCipher;
	
	public AesSupport() throws Exception{
		decryptCipher = Cipher.getInstance(KEY_ALGORITHM);
		decryptCipher.init(Cipher.DECRYPT_MODE, getSecretKey(SECRET));
		encryptCipher = Cipher.getInstance(KEY_ALGORITHM);
		encryptCipher.init(Cipher.ENCRYPT_MODE, getSecretKey(SECRET));
	}

	private static SecretKey getSecretKey(String password) throws Exception {
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(password.getBytes());
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		kg.init(128, random);
		SecretKey secretKey = kg.generateKey();
		return secretKey;
	}

	public String decrypt(String content) throws Exception {
		byte[] source = Base64.decodeBase64(content);
		byte[] result = decryptCipher.doFinal(source);
		return new String(result, "utf-8");
	}
	
	public String encrypt(String content) throws Exception {
		byte[] byteContent = content.getBytes("utf-8");
		byte[] result = encryptCipher.doFinal(byteContent);
		String text = Base64.encodeBase64String(result);
		return text;
	}



}
