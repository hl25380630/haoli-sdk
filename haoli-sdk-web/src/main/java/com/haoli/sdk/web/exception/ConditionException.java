package com.haoli.sdk.web.exception;

public class ConditionException extends RuntimeException {
	
	static final long serialVersionUID = -7034897190745766939L;

	private String code;
	
	public ConditionException(String code, String message){
		super(message);
		this.code = code;
	}
	
	public ConditionException(String message){
		super(message);
		this.code = "500";
	}

	public String getCode() {
		return code;
	}
	
}