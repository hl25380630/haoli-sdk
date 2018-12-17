package com.haoli.sdk.web.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class HttpUtil {
	
	public static final String CONTENT_TYPE_JSON = "application/json";
	
	public static final String REQUEST_METHOD_POST = "POST";
	
	public static final Integer CONNECT_TIME_OUT = 120000;
	
	public static final Integer READ_TIME_OUT = 60000;
	
	public static void main(String[] args) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pageSize", 5);
		params.put("pageNo", 1);
		params.put("cityCode", 131);
		String url = "https://ssctest.boe.com/v1/mall-api/store/pageList";
		HttpResponse res = HttpUtil.postJson(url, params);
		System.out.println(res);
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
		con.setRequestProperty("Accept", CONTENT_TYPE_JSON);
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
		public String toString(){
			return "statusCode:" + this.statusCode + ",body:" + this.body;
		}
		
	}

}
