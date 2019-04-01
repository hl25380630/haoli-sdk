package com.haoli.sdk.web.util.microSoftOffice;

import java.io.File;

public class PptToPdfUtil {
	
	
//	public void ppt2Pdf(String filePath, String savePath){
//		Presentation ppt = new Presentation(filePath);
//		ppt.save(savePath,SaveFormat.Pdf);
//	}
	
	
    public static void main(String[] args) throws Exception {
        String source = "C:\\Users\\10063731\\Desktop\\cip\\test file\\ppt\\多页ppt111.pptx";
        String pptImgsPath = "C:\\Users\\10063731\\Desktop\\cip\\pptImgs";
        String dest = "C:\\Users\\10063731\\Desktop\\cip\\convertedFile\\多页ppt111.pdf";
        PptToPdfUtil pe = new PptToPdfUtil();
//        pe.pptToPdf(source ,pptImgsPath, dest);
//        pe.ppt2Pdf(source, dest);
        
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
