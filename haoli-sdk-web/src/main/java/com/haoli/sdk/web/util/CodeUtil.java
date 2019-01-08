package com.haoli.sdk.web.util;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

/**
 * 编码 解码 工具类
 * @author 李昊
 *
 */
public class CodeUtil {
	
	/**
	 * 用于base64编码
	 * @param text 输入参数为字符串类型
	 */
	public static String base64EncodeStr(String text) throws Exception {
		byte[] textByte = text.getBytes("UTF-8");
		Encoder encoder = Base64.getEncoder();
		String encodedText = encoder.encodeToString(textByte);
		return encodedText;
	}
	
	/**
	 * 用于base64编码
	 * @param text 输入参数为字节类型
	 */
	public static String base64EncodeByte(byte[] textByte) throws Exception {
		Encoder encoder = Base64.getEncoder();
		String encodedText = encoder.encodeToString(textByte);
		return encodedText;
	}
	
	/**
	 * 用于base64解码
	 * @param encodedText 返回字符串
	 */
	public static String base64DecodeToStr(String encodedText) throws Exception {
		Decoder decoder = Base64.getDecoder();
		String text = new String(decoder.decode(encodedText),"UTF-8");
		return text;
	}
	
	/**
	 * 用于base64解码
	 * @param encodedText 返回字节
	 */
	public static byte[] base64DecodeToByte(String encodedText) throws Exception {
		Decoder decoder = Base64.getDecoder();
		byte[] testByte = decoder.decode(encodedText);
		return testByte;
	}

}
