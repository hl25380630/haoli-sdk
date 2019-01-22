package com.haoli.sdk.web.util;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSONObject;
import com.haoli.sdk.web.domain.RSAKey;

/**
 * Rsa加密工具类
 * @author 李昊
 */
public class RsaUtil {
	
	public static void main(String[] args) throws Exception {
		String p ="C:\\Users\\10063731\\Desktop\\personal\\development\\rsa\\rsaKey.json";
		String s = FileUtil.readFile(p);
		JSONObject jobj = JSONObject.parseObject(s);
		String privateKey = jobj.getString("RsaPrivateKey");
		String publicKey = jobj.getString("RsaPublicKey");
		String str = RsaUtil.encrypt("Li134679258!", publicKey);
		System.out.println(str);
		String destr = RsaUtil.decrypt(str, privateKey);
		System.out.println(destr);
	}
	

	public static RSAKey genKeyPair() throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		keyPairGen.initialize(1024, new SecureRandom());
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
		String privateKeyString = new String(Base64.encodeBase64(privateKey.getEncoded()));
		return new RSAKey(privateKey, privateKeyString, publicKey, publicKeyString);
	}

	public static String encrypt(String source, String publicKey) throws Exception {
		byte[] decoded = Base64.decodeBase64(publicKey);
		RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA")
				.generatePublic(new X509EncodedKeySpec(decoded));
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(1, pubKey);
		String outStr = Base64.encodeBase64String(cipher.doFinal(source.getBytes("UTF-8")));
		return outStr;
	}

	public static Cipher getCipher(String privateKey) throws Exception {
		byte[] decoded = Base64.decodeBase64(privateKey);
		RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
				.generatePrivate(new PKCS8EncodedKeySpec(decoded));
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(2, priKey);
		return cipher;
	}

	public static String decrypt(String text, String privateKey) throws Exception {
		Cipher cipher = getCipher(privateKey);
		byte[] inputByte = Base64.decodeBase64(text.getBytes("UTF-8"));
		String outStr = new String(cipher.doFinal(inputByte));
		return outStr;
	}
}
