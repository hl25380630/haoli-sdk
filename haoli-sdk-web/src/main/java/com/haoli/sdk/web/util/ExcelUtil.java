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

/**
 * excel工具类，用于导出excel表，使用的时候需要使用@Autowired注入
 * 不然this.getClass().getResourceAsStream无法生效
 * @author 李昊
 */
public class ExcelUtil {
	
	/**
	 * 用于根据指定的模板导出excel表，可以添加自定义的函数功能，
	 * @param template 导出模板
	 * @param os 输出流
	 * @param varMap 想要导出的数据
	 */
	public void exportExcel(String template, OutputStream os, Map<String, Object> varMap) throws IOException {
		InputStream templateStream = this.getClass().getResourceAsStream(template);
		Context context = new Context(varMap);
		JxlsHelper jxlsHelper = JxlsHelper.getInstance();
		Transformer transformer  = jxlsHelper.createTransformer(templateStream, os);
		JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator)transformer.getTransformationConfig().getExpressionEvaluator();
		Map<String, Object> funcs = new HashMap<String, Object>();
		funcs.put("utils", new ExcelUtil());    //添加自定义功能
        evaluator.getJexlEngine().setFunctions(funcs);
        jxlsHelper.processTemplate(context, transformer);
	}
	
	/**
	 * 用于对excel中日期格式的列的值进行想要的格式变换
	 * @param date 日期
	 * @param fmt 想要format的日期形式
	 */
    public String dateFmt(Date date, String fmt) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat dateFmt = new SimpleDateFormat(fmt);
        return dateFmt.format(date);
    }
    
    /**
     * 用于替换导出的excel表中想要替换的某一列的值
     * @param rawValue 原值
     * @param jsonValueMap 想要替换的值的json合集，值与值之间用逗号隔开，key与value之间用冒号
     */
    public String replace(String rawValue, String jsonValueMap) {
    	String[] ss = jsonValueMap.split(",");
    	Map<String, Object> map = new HashMap<String, Object>();
    	for(String s : ss) {
    		String[] entry = s.split(":");
    		String key = entry[0];
    		String value = entry[1];
    		map.put(key, value);
    	}
    	String value = MapUtil.getString(map, rawValue);
    	return value;
    }
}
