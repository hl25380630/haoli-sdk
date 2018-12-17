package com.haoli.sdk.web.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * http 请求工具类，基于java原生包实现
 * @author 李昊
 */
public class HttpUtil {
	
	public static final String CONTENT_TYPE_JSON = "application/json";
	
	public static final String CONTENT_TYPE_URL_ENCODED = "application/x-www-form-urlencoded";
	
	public static final String REQUEST_METHOD_POST = "POST";
	
	public static final String REQUEST_METHOD_GET = "GET";
	
	public static final Integer CONNECT_TIME_OUT = 120000;
	
	public static final Integer READ_TIME_OUT = 60000;
	
	/**
	 * http get请求
	 * @param url 请求链接
	 * @param params 请求参数
	 */
	public static HttpResponse get(String url, Map<String, Object> params) throws Exception {
		StringBuilder sb = new StringBuilder(url);
		if(params != null && !params.isEmpty()) {
			sb.append("?");
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				if(entry.getValue() == null) {
					continue;
				}
				String value = URLEncoder.encode(String.valueOf(entry.getValue()), "UTF-8");
				sb.append(String.valueOf(entry.getKey())).append("=").append(value).append("&");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		String getUrl = sb.toString();
		URL urlObj = new URL(getUrl);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setDoInput(true);
		con.setRequestMethod(REQUEST_METHOD_GET); 
		con.setConnectTimeout(CONNECT_TIME_OUT);
		con.setReadTimeout(READ_TIME_OUT);
		con.connect();
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
		StringBuilder responseSb = new StringBuilder();  
	    String response = null;
	    int responseCode = con.getResponseCode();
	    if(responseCode >=200 && responseCode <300) {
		    String line = null;
		    while ((line = br.readLine()) != null) {  
		    	responseSb.append(line + "\n");  
		    }
		    response = responseSb.toString();
			br.close();
	    }else {
			response = responseSb.toString();
	    }
	    br.close();
	    con.disconnect();
	    HttpResponse httpResponse = new HttpResponse(responseCode,response);
		return httpResponse;
	}
	
	/**
	 * http post请求，请求参数形式为json
	 * @param url 请求链接地址
	 * @param params 请求参数
	 */
	public static HttpResponse postJson(String url, Map<String, Object> params) throws Exception {
		URL urlObj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", CONTENT_TYPE_JSON);
		con.setRequestMethod(REQUEST_METHOD_POST); 
		con.setConnectTimeout(CONNECT_TIME_OUT);
		con.setReadTimeout(READ_TIME_OUT);
		String json = JSONObject.toJSONString(params);
		con.connect();
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.getOutputStream());
		outputStreamWriter.write(json);
		outputStreamWriter.flush();
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
		StringBuilder sb = new StringBuilder();  
	    String response = null;
	    int responseCode = con.getResponseCode();
	    if(responseCode >=200 && responseCode <300) {
		    String line = null;
		    while ((line = br.readLine()) != null) {  
		        sb.append(line + "\n");  
		    }
		    response = sb.toString();
			br.close();
	    }else {
			response = sb.toString();
	    }
	    outputStreamWriter.close();
	    br.close();
	    con.disconnect();
	    HttpResponse httpResponse = new HttpResponse(responseCode,response);
		return httpResponse;
	}
	
	
	/**
	 * http post请求，请求参数形式为url-encoded
	 * @param url 请求链接地址
	 * @param params 请求参数
	 */
	public static HttpResponse postUrlEncoded(String url, Map<String, Object> params) throws Exception {
		URL urlObj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", CONTENT_TYPE_URL_ENCODED);
		con.setRequestMethod(REQUEST_METHOD_POST); 
		con.setConnectTimeout(CONNECT_TIME_OUT);
		con.setReadTimeout(READ_TIME_OUT);
		con.connect();
		StringBuilder postParamBuilder = new StringBuilder();
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(con.getOutputStream());
		if(params != null && !params.isEmpty()) {
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				if(entry.getValue() == null) {
					continue;
				}
				String value = URLEncoder.encode(String.valueOf(entry.getValue()), "UTF-8");
				postParamBuilder.append(String.valueOf(entry.getKey())).append("=").append(value).append("&");
			}
			postParamBuilder.deleteCharAt(postParamBuilder.length() - 1);
		}
		String postParam = postParamBuilder.toString();
		outputStreamWriter.write(postParam);
		outputStreamWriter.flush();
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
		StringBuilder sb = new StringBuilder();  
	    String response = null;
	    int responseCode = con.getResponseCode();
	    if(responseCode >=200 && responseCode <300) {
		    String line = null;
		    while ((line = br.readLine()) != null) {  
		        sb.append(line + "\n");  
		    }
		    response = sb.toString();
			br.close();
	    }else {
			response = sb.toString();
	    }
	    outputStreamWriter.close();
	    br.close();
	    con.disconnect();
	    HttpResponse httpResponse = new HttpResponse(responseCode,response);
		return httpResponse;
	}
	
	
	public static class HttpResponse {
		
		private int statusCode;
		
		private String body;
		
		public HttpResponse(int statusCode, String body){
			this.statusCode = statusCode;
			this.body = body;
		}

		public int getStatusCode() {
			return statusCode;
		}

		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}
		
		public boolean isSuccess(){
			if(this.statusCode >= 200 && this.statusCode < 300) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public String toString() {
			return "{\n\tstatusCode:" + statusCode + ",\n\tbody:" + body + "}";
		}
		
	}

}
