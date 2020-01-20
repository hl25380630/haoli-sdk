package com.haoli.sdk.web.domain;

public class UserClient {
	
	private String clientType;
	
	private String clientId;
	
	public UserClient() {
		
	}
	
    public UserClient(String clientType, String clientId) {
		this.clientType = clientType;
		this.clientId = clientId;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

}
