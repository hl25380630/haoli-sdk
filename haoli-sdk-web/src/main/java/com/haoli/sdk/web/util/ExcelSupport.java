package com.haoli.sdk.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jxls.common.Context;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;
import org.springframework.stereotype.Component;

@Component
public class ExcelSupport {
	
	public void exportExcel(String template, OutputStream os, Map<String, Object> varMap) throws IOException {
		InputStream templateStream = this.getClass().getResourceAsStream(template);
		Context context = new Context(varMap);
		JxlsHelper jxlsHelper = JxlsHelper.getInstance();
		Transformer transformer  = jxlsHelper.createTransformer(templateStream, os);
		JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator)transformer.getTransformationConfig().getExpressionEvaluator();
		Map<String, Object> funcs = new HashMap<String, Object>();
		funcs.put("utils", new ExcelSupport());    //添加自定义功能
        evaluator.getJexlEngine().setFunctions(funcs);
        jxlsHelper.processTemplate(context, transformer);
	}
	
    public String dateFmt(Date date, String fmt) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat dateFmt = new SimpleDateFormat(fmt);
        return dateFmt.format(date);
    }
    

}
