package com.haoli.sdk.web.util.microSoftOffice;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

public class ImgsToPdfUtil {
	
    public static void main(String[] args) throws Exception {
        String imageFolderPath = "C:\\Users\\10063731\\Desktop\\cip\\test file\\pptImg";
        String dest = "C:\\Users\\10063731\\Desktop\\cip\\test file\\pptimg.pdf";
        ImgsToPdfUtil pe = new ImgsToPdfUtil();
        pe.imgsToPdf(imageFolderPath ,dest);
    }

	public void imgsToPdf(String imageFolderPath, String pdfPath) throws Exception{
		FileOutputStream fos = new FileOutputStream(pdfPath);
		Document doc = new Document(null, 0, 0, 0, 0);
		doc.open();
		// 写入PDF文档
		PdfWriter.getInstance(doc, fos);
		// 获取图片文件夹对象
		File[] files = new File(imageFolderPath).listFiles();
		Map<Integer, File> filesMap = new HashMap<Integer, File>();
		for(File file : files) {
			String fileName = file.getName();
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
			Integer pageNo = Integer.valueOf(fileName);
			filesMap.put(pageNo, file);
		}
		// 循环获取图片文件夹内的图片
		for (int i=1; i<=files.length; i++) {
			File file = filesMap.get(i);
			String fileName = file.getName();
			String fileType = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
            String reg = "^(png|jpg|jpeg|gif|tif)$";
            if (!fileType.toLowerCase().matches(reg)){
                continue;
            }
			String imagePath = imageFolderPath + "/" + fileName;
			BufferedImage img = ImageIO.read(new File(imagePath));
			// 根据图片大小设置文档大小
			doc.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
			// 实例化图片
			Image image = Image.getInstance(imagePath);
			// 添加图片到文档
			doc.open();
			doc.add(image);
		}
		doc.close();
	}
	
	public void imgToPdf(String imgPath, String pdfPath) throws Exception{
		FileOutputStream fos = new FileOutputStream(pdfPath);
		Document doc = new Document(null, 0, 0, 0, 0);
		doc.open();
		// 写入PDF文档
		PdfWriter.getInstance(doc, fos);
		// 获取图片文件夹对象
		File file = new File(imgPath);
		// 循环获取图片文件夹内的图片
		String fileName = file.getName();
		String fileType = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
        String reg = "^(png|jpg|jpeg|gif|tif)$";
        if (!fileType.toLowerCase().matches(reg)){
            return;
        }
		BufferedImage img = ImageIO.read(new File(imgPath));
		// 根据图片大小设置文档大小
		doc.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
		// 实例化图片
		Image image = Image.getInstance(imgPath);
		// 添加图片到文档
		doc.open();
		doc.add(image);
		doc.close();
	}
}
