package com.haoli.sdk.web.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 文件工具类
 * @author 李昊
 *
 */
public class FileUtil {
	
	/**
	 * 用于读取文件,返回字符串
	 * @param fileName 文件名，包含文件路径
	 */
	public static String readFile(String fileName) throws Exception  {
		File file = new File(fileName);
		InputStream fileInputStream = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream, "utf-8"));
		StringBuilder fileSb = new StringBuilder();
		String line = null;
	    while ((line = br.readLine()) != null) {  
	    	fileSb.append(line + "\n");  
	    }
	    String fileContent = fileSb.toString();
		br.close();
		return fileContent;
	}
}
