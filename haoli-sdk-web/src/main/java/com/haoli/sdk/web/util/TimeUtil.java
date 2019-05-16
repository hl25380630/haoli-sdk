package com.haoli.sdk.web.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	
	public static void main(String[] args) throws ParseException{
		TimeUtil tu = new TimeUtil();
		String date = tu.timeStamp2Date("1540117525");
		System.out.println(date);
		String start = "2018-09-01 00:00:00";
		String end = "2018-09-13 11:59:00";
		System.out.println(tu.Date2timeStamp(start));
		System.out.println(tu.Date2timeStamp(end));

	}
	
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
	
    public String timeStamp2Date(String timeStamp) {  
        String format = "yyyy-MM-dd HH:mm:ss";  
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        return sdf.format(new Date(Long.valueOf(timeStamp+"000")));  
    } 
    
    public Date timeStampToDate(String timeStamp) {  
        return new Date(Long.valueOf(timeStamp));  
    } 
    
    public String Date2timeStamp(String time) throws ParseException {
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date date = format.parse(time);
        String result = String.valueOf(date.getTime());
        return result.substring(0, result.length()-3);  
    }

}
