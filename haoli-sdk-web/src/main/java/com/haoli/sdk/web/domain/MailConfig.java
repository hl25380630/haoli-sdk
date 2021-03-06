package com.haoli.sdk.web.domain;

public class MailConfig {
	
	//用户id
	private Long userId;
	
	//邮箱类型 qq 新浪 网易 boe等等
	private String mailType;
	
	//smtp邮箱服务器地址
	private String host;
	
	//smtp端口
	private String port;
	
	//用户名
	private String userName;

	//密码
	private String password;
	
	//发件人邮箱地址
	private String fromEmail;
	
	//发件人名称
	private String fromName;
	
	//是否使用ssl
	private boolean ssl;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMailType() {
		return mailType;
	}

	public void setMailType(String mailType) {
		this.mailType = mailType;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public boolean isSsl() {
		return ssl;
	}

	public void setSsl(boolean ssl) {
		this.ssl = ssl;
	}

}
