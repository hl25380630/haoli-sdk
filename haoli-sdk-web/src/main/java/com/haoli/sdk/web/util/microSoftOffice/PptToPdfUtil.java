package com.haoli.sdk.web.util.microSoftOffice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.aspose.slides.Presentation;
import com.aspose.slides.SaveFormat;
import com.aspose.words.License;

/**
 * 使用apache poi现将ppt转为图片再将图片转为pdf
 * 目前存在转换后内容丢失的情况，不能实现完美转换。
 */
public class PptToPdfUtil {
	
	private static InputStream license;
	
    public static void main(String[] args) throws Exception {
        String source = "C:\\Users\\10063731\\Desktop\\cip\\test file\\ppt\\多页ppt111.pptx";
        String dest = "C:\\Users\\10063731\\Desktop\\cip\\convertedFile\\多页ppt111.pdf";
        PptToPdfUtil test = new PptToPdfUtil();
    }
    
	public void pptToPdf(String source, String pptImgsPath, String dest) throws Exception {
		PptToImageUtil piu = new PptToImageUtil();
		String imageFolderPath = piu.pptToImage(source, pptImgsPath);
		ImgsToPdfUtil ipu = new ImgsToPdfUtil();
		ipu.imgsToPdf(imageFolderPath, dest);
		File imgFolderPath = new File(pptImgsPath);
		this.deleteAll(imgFolderPath);

	}
	
	public void pptxToPdf(String source, String pptImgsPath, String dest) throws Exception {
		PptToImageUtil piu = new PptToImageUtil();
		String imageFolderPath = piu.pptxToImage(source, pptImgsPath);
		ImgsToPdfUtil ipu = new ImgsToPdfUtil();
		ipu.imgsToPdf(imageFolderPath, dest);
		File imgFolderPath = new File(pptImgsPath);
		this.deleteAll(imgFolderPath);

	}
	
	
	public void deleteAll(File dir) {
		if (dir.isFile()) {
			dir.delete();
			return;
		} else {
			File[] files = dir.listFiles();
			for (File file : files) {
				deleteAll(file);
			}
		}
		dir.delete();
	}

}
