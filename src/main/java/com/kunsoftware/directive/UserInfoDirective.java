package com.kunsoftware.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import com.kunsoftware.util.WebUtil;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class UserInfoDirective implements TemplateDirectiveModel {

	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map params, TemplateModel[] loopVars, 
			TemplateDirectiveBody body) throws TemplateException, IOException { 
		
		StringBuilder str = new StringBuilder();
		str.append(WebUtil.getUserName());
		 
		Writer out = env.getOut();  
		out.write(str.toString());
	}

}
