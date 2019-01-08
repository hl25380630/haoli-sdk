package com.haoli.sdk.web.domain;

public class JsonResponse<T> {
	
	private static final String SUCCESS_CODE = "200";
	
	private static final String FAIL_CODE = "500";
	
	private static final String SUCCESS_MSG = "request success";
	
	private static final String FAIL_MSG = "request fail";
	
	private String code;
	
	private String msg;
	
	private T data;
	
	public JsonResponse(T data, String code, String msg) {
		this.data = data;
		this.code = code;
		this.msg = msg;
	}
	
	public static JsonResponse<String> success() {
		return new JsonResponse<String>(null, SUCCESS_CODE, SUCCESS_MSG);
	}
	
	public static JsonResponse<String> fail() {
		return new JsonResponse<String>(null, FAIL_CODE, FAIL_MSG);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
