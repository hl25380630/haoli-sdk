package com.haoli.sdk.web.util.microSoftOffice;

import java.io.FileInputStream;
import java.io.InputStream;

import com.aspose.cells.License;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;


public class Excel2Pdf {
	
    public static void main(String[] args) throws Exception {
        String source = "C:\\Users\\10063731\\Desktop\\cip\\test file\\excel\\CIPPRE环境消息提醒测试.xlsx";
        String dest = "C:\\Users\\10063731\\Desktop\\cip\\convertedFile\\CIPPRE环境消息提醒测试.pdf";
        Excel2Pdf pe = new Excel2Pdf();
        pe.excel2pdf(source, dest);
    }
    
    public static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = Excel2Pdf.class.getClassLoader().getResourceAsStream("license-cell.xml");
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
	
	public void excel2pdf(String source, String dest) throws Exception {
        if (!getLicense()) {
            return;
        }
        Workbook wb = new Workbook(new FileInputStream(source));// 原始excel路径
        WorksheetCollection sheets = wb.getWorksheets();
        int count = sheets.getCount();
        for(int i=0; i<count; i++) {
            Worksheet sheet = sheets.get(i);
            sheet.getPageSetup().setFitToPagesWide(1); 
            sheet.getPageSetup().setFitToPagesTall(0);
        }
        wb.save(dest);
    }

}
