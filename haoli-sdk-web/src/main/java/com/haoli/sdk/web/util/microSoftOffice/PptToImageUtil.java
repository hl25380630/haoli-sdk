package com.haoli.sdk.web.util.microSoftOffice;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

/**
 * ppt转图片工具类
 */
public class PptToImageUtil {
	
	private static final int ZOOM = 8;
	
    public static void main(String[] args) throws Exception {
        String source = "C:\\Users\\10063731\\Desktop\\cip\\附件2：线上报到操作指南.ppt";
        String dest = "C:\\Users\\10063731\\Desktop\\cip\\pptImg";
        PptToImageUtil pe = new PptToImageUtil();
        pe.pptToImage(source ,dest);
    }
	
	public String pptToImage(String source, String dest) throws Exception {
		FileInputStream is = new FileInputStream(new File(source));
		SlideShow ppt = new SlideShow(is);
		Dimension pgsize = ppt.getPageSize();
		Slide[] slide = ppt.getSlides();
		String finalPath = "";
		for (int i = 0; i < slide.length; i++) {
            TextRun[] truns = slide[i].getTextRuns();
            for (int k = 0; k < truns.length; k++) {
                RichTextRun[] rtruns = truns[k].getRichTextRuns();
                for (int l = 0; l < rtruns.length; l++) {
                    rtruns[l].setFontIndex(1);
                    rtruns[l].setFontName("宋体");
                }
            }
            BufferedImage img = new BufferedImage(pgsize.width*ZOOM, pgsize.height*ZOOM, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = img.createGraphics();
            graphics.setPaint(Color.BLUE);
            graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width*ZOOM, pgsize.height*ZOOM));
            graphics.scale(ZOOM, ZOOM);
            slide[i].draw(graphics);
            File sourceFile = new File(source);
            String sourceFileName = sourceFile.getName();
            String sourceFileNameNoSuffix = sourceFileName.substring(0, sourceFileName.lastIndexOf("."));
            finalPath = dest + "/" + sourceFileNameNoSuffix;
            File path = new File(finalPath);
            if (!path.exists()) {
                path.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(path  + "/" + (i + 1) + ".png");
            ImageIO.write(img, "png", out);
            out.close();
        }
        System.out.println("success!!");
		return finalPath;
	}
	
	public String pptxToImage(String source, String dest) throws Exception {
		FileInputStream is = new FileInputStream(new File(source));
        XMLSlideShow xmlSlideShow = new XMLSlideShow(is);
        String finalPath = "";
        is.close();
        // 获取大小
        Dimension pgsize = xmlSlideShow.getPageSize();
        // 获取幻灯片
        XSLFSlide[] slides = xmlSlideShow.getSlides();
        for (int i = 0 ; i < slides.length ; i++) {
            // 解决乱码问题
            XSLFShape[] shapes = slides[i].getShapes();
            for (XSLFShape shape : shapes) {
                if (shape instanceof XSLFTextShape) {
                    XSLFTextShape sh = (XSLFTextShape) shape;
                    List<XSLFTextParagraph> textParagraphs = sh.getTextParagraphs();
                    for (XSLFTextParagraph xslfTextParagraph : textParagraphs) {
                        List<XSLFTextRun> textRuns = xslfTextParagraph.getTextRuns();
                        for (XSLFTextRun xslfTextRun : textRuns) {
                            xslfTextRun.setFontFamily("宋体");
                        }
                    }
                }
            }
            //根据幻灯片大小生成图片
            BufferedImage img = new BufferedImage(pgsize.width*4, pgsize.height*4, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = img.createGraphics();
            graphics.setPaint(Color.white);
            graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width*4, pgsize.height*4));
            graphics.scale(4, 4);
            slides[i].draw(graphics);
            File sourceFile = new File(source);
            String sourceFileName = sourceFile.getName();
            String sourceFileNameNoSuffix = sourceFileName.substring(0, sourceFileName.lastIndexOf("."));
            finalPath = dest + "/" + sourceFileNameNoSuffix;
            File path = new File(finalPath);
            if (!path.exists()) {
                path.mkdirs();
            }
            // 这里设置图片的存放路径和图片的格式(jpeg,png,bmp等等),注意生成文件路径
            FileOutputStream out = new FileOutputStream(path + "/" + (i + 1) + ".jpg");
            ImageIO.write(img, "png", out);
            out.close();
        }
        return finalPath;
	}
	    
}
