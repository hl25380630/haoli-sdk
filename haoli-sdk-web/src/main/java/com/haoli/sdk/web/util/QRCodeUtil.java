package com.haoli.sdk.web.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码工具类
 * @author 李昊
 *
 */
public class QRCodeUtil {
	
	public static final int WIDTH = 300;// 默认二维码宽度
	public static final int HEIGHT = 300;// 默认二维码高度
	public static final String format = "png";// 默认二维码文件格式
	public static final Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();// 二维码参数

	static {
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");// 字符编码
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.MARGIN, 2);// 二维码与图片边距
	}

	/**
	 * @description：功能描述 	将二维码写出到输出流中
	 * @param content	二维码内容
	 * @param stream	输出流
	 * @param width	二维码宽
	 * @param height	二维码高
	 * @see： 需要参见的其它内容
	 */
	public static void writeToStream(String content, OutputStream stream,
			Integer width, Integer height) throws WriterException, IOException {
		if(width==null){
			width=WIDTH;
		}
		if(height==null){
			height=HEIGHT;
		}
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
				BarcodeFormat.QR_CODE, width, height, hints);
		MatrixToImageWriter.writeToStream(bitMatrix, format, stream);
	}

	/**
	 * @description：功能描述 	将二维码写出到文件中
	 * @param content	二维码内容
	 * @param path	文件地址
	 * @param width	二维码宽
	 * @param height	二维码高
	 */
	public static void writeToFile(String content, String path, Integer width,
			Integer height) throws WriterException, IOException {
		if(width==null){
			width=WIDTH;
		}
		if(height==null){
			height=HEIGHT;
		}
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
				BarcodeFormat.QR_CODE, width, height, hints);
		MatrixToImageWriter.writeToPath(bitMatrix, format,
				new File(path).toPath());
	}
	
	/**
	 * @description：功能描述  将生成的二维码图片准换成base64字符串
	 * @param content 二维码内容
	 * @param width 图片宽度
	 * @param height 图片高度
	 */
	public static String writeToBase64String(String content,Integer width,
			Integer height) throws WriterException, IOException{
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		writeToStream(content, out, width, height);
		return Base64.encodeBase64String(out.toByteArray());
	}
		
}
