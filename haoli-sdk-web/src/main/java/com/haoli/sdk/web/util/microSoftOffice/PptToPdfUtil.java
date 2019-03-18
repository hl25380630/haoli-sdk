package com.haoli.sdk.web.util.microSoftOffice;

import java.io.File;

public class PptToPdfUtil {
	
    public static void main(String[] args) throws Exception {
        String source = "C:\\Users\\10063731\\Desktop\\cip\\附件2：线上报到操作指南.ppt";
        String pptImgsPath = "C:\\Users\\10063731\\Desktop\\cip\\pptImgs";
        String dest = "C:\\Users\\10063731\\Desktop\\cip\\result.pdf";
        PptToPdfUtil pe = new PptToPdfUtil();
        pe.pptToPdf(source ,pptImgsPath, dest);
    }
	
	public void pptToPdf(String source, String pptImgsPath, String dest) throws Exception {
		PptToImageUtil piu = new PptToImageUtil();
		String imageFolderPath = piu.pptToImage(source, pptImgsPath);
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
