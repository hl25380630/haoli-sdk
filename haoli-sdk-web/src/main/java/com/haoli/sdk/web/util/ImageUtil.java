package com.haoli.sdk.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;

public class ImageUtil {
	
	/**
	 * 图片转base64
	 */
	public String imgToBase64(String imgPath) throws Exception {
		File file = new File(imgPath);
		InputStream is = new FileInputStream(file);
		byte[] buffer = new byte[is.available()];
		is.read(buffer);
		is.close();
		String str = Base64.encodeBase64String(buffer);
		return str;
	}

}
