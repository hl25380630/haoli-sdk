package com.haoli.sdk.web.domain;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RSAKey {
	
	private RSAPrivateKey privateKey;
	
	private String privateKeyString;
	
	private RSAPublicKey publicKey;
	
	public String publicKeyString;

	public RSAKey() {
	}

	public RSAKey(RSAPrivateKey privateKey, String privateKeyString, RSAPublicKey publicKey, String publicKeyString) {
		this.privateKey = privateKey;
		this.privateKeyString = privateKeyString;
		this.publicKey = publicKey;
		this.publicKeyString = publicKeyString;
	}

	public RSAPrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(RSAPrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	public String getPrivateKeyString() {
		return privateKeyString;
	}

	public void setPrivateKeyString(String privateKeyString) {
		this.privateKeyString = privateKeyString;
	}

	public RSAPublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(RSAPublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public String getPublicKeyString() {
		return publicKeyString;
	}

	public void setPublicKeyString(String publicKeyString) {
		this.publicKeyString = publicKeyString;
	}

	
}