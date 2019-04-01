package com.haoli.sdk.web.util.microSoftOffice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.aspose.slides.License;
import com.aspose.slides.Presentation;
import com.aspose.slides.SaveFormat;

/**
 * 使用aspose讲ppt文件转换为pdf文件 
 * 目前转换效果较为理想
 */
public class Ppt2Pdf {
	
    public static void main(String[] args) throws Exception {
        String source = "C:\\Users\\10063731\\Desktop\\cip\\test file\\ppt\\多页ppt111.pptx";
        String dest = "C:\\Users\\10063731\\Desktop\\cip\\convertedFile\\多页ppt111.pdf";
        Ppt2Pdf test = new Ppt2Pdf();
        test.ppt2pdf(source, dest);
        
    }
    
    private static InputStream license;
    
    public static boolean getLicense() {
        boolean result = false;
        try {
            license = Ppt2Pdf.class.getClassLoader().getResourceAsStream("license-slide.xml");// license路径
                License aposeLic = new License();
                aposeLic.setLicense(license);
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
    }

    public void ppt2pdf(String source, String dest)throws Exception {
        if (!getLicense()) {
            return;
        }
        long old = System.currentTimeMillis();
        File file = new File(dest);// 输出pdf路径
        Presentation pres = new Presentation(source);//输入pdf路径
        FileOutputStream fileOS = new FileOutputStream(file);
        pres.save(fileOS, SaveFormat.Pdf);
        fileOS.close();
        long now = System.currentTimeMillis();
        System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒\n\n" + "文件保存在:" + file.getPath()); //转化过程耗时
    }


}
