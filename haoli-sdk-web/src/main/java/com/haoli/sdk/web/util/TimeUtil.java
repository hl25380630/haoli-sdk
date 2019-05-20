package com.haoli.sdk.web.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

	public String getCurrentTimestamp() {
		Date date = new Date();
	    String timestamp = String.valueOf(date.getTime()/1000);  
	    return timestamp;
	}
	
	public String getCurrentTime() {
		 Date d = new Date();  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        String dateNowStr = sdf.format(d);  
        return dateNowStr;
	}
	
	public static String dateToTime(Date date) {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
       String dateNowStr = sdf.format(date);  
       return dateNowStr;
	}
	
    public Date timeStampToDate(String timeStamp) {  
        return new Date(Long.valueOf(timeStamp));  
    } 
    
    public static String timeStampToSimpleDate(String timeStamp) throws ParseException {  
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");  
    	Date date = sdf.parse(timeStamp);
    	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    	String s = sdf2.format(date);
        return s;  
    } 
    
    public static String timeStamp10ToSimpleDateFormat(String timeStamp) {
        String format = "yyyy-MM-dd HH:mm:ss";  
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        if(timeStamp == null) {
        	return null;
        }
        return sdf.format(new Date(Long.valueOf(timeStamp+"000")));  
    } 
    
    public static Date timeStamp10ToDate(String timeStamp) { 
    	if(timeStamp == null) {
    		return null;
    	}
        return new Date(Long.valueOf(timeStamp+"000"));  
    } 
    
    /**
     * 讲sqlite的时间戳转换为正常的java Date类
     */
    public static Date sqliteTimeStampToDate(String sqliteTimeStamp) {
    	if(sqliteTimeStamp == null || "".equals(sqliteTimeStamp) || "0".equals(sqliteTimeStamp)) {
    		return null;
    	}
    	Long  sqliteTimeStampLong = Long.valueOf(sqliteTimeStamp);
    	String unixTimeStamp10digits = String.valueOf(sqliteTimeStampLong/1000000 - 11644473600L);
    	return new Date(Long.valueOf(unixTimeStamp10digits+"000"));  
    }

    
}
