package com.haoli.sdk.web.util;

import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class VelocityUtil implements InitializingBean {

	private VelocityEngine velocityEngine;
	
	public String getText(String templateName, Map<String, Object> params){
		StringWriter writer = new StringWriter();
		VelocityContext velocityContext = new VelocityContext(params);
	    velocityEngine.mergeTemplate(templateName, "UTF-8", velocityContext, writer);
	    String text = writer.toString();
	    return text;
	}

	
	@Override
	public void afterPropertiesSet() throws Exception {
		Properties properties = new Properties();
		properties.setProperty("resource.loader", "class");
		properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		properties.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
        properties.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        properties.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
		velocityEngine = new VelocityEngine(properties);
		velocityEngine.init();
	}

}
