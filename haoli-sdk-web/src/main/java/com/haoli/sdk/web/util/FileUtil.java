package com.haoli.sdk.web.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.web.multipart.MultipartFile;

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
	
	/**
	 * 在指定路径位置创建上传的文件
	 */
	public void createFile(String filePath, MultipartFile uploadFile) throws Exception {
		File file = new File(filePath);
		//如果文件路径不存在，则先创建文件路径
		if(!file.exists()) {
          	String parentPath = file.getParent();
        	new File(parentPath).mkdirs();
		}
    	uploadFile.transferTo(file);
	}
}
