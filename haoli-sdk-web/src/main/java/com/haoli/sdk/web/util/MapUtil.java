package com.haoli.sdk.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * map工具类
 * @author 李昊
 *
 */
public class MapUtil {

	public static Integer getInteger(Map<String, Object> map, String key) {
		if(map == null) {
			return null;
		}
		Object obj = map.get(key);
		if(obj == null) {
			return null;
		} else if(obj instanceof Integer) {
			return (Integer)obj;
		} else {
			return Integer.valueOf(getString(map, key));
		}
	}
	
	public static Long getLong(Map<String, Object> map, String key) {
		if(map == null) {
			return null;
		}
		Object obj = map.get(key);
		if(obj == null) {
			return null;
		} else if(obj instanceof Long) {
			return (Long)obj;
		} else {
			return Long.valueOf(getString(map, key));
		}
	}
	
	public static String getString(Map<String, Object> map, String key) {
		if(map == null) {
			return null;
		}
		Object obj = map.get(key);
		if(obj == null) {
			return null;
		} else if(obj instanceof String) {
			return (String)obj;
		} else {
			return String.valueOf(obj);
		}
	}
	
	public static Date getDate(Map<String, Object> map, String key) throws Exception {
		String str = getString(map, key);
		if(str == null || "".equals(str.trim())) {
			return null;
		}
		if(str.length() == 10) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse(str);
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.parse(str);
		}
	}
	
}
