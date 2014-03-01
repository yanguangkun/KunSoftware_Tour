package com.kunsoftware.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.kunsoftware.entity.ValueSet;
import com.kunsoftware.service.ValueSetService;
import com.kunsoftware.util.WebUtil;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class ValueSetWriteDirective implements TemplateDirectiveModel {

	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map params, TemplateModel[] loopVars, 
			TemplateDirectiveBody body) throws TemplateException, IOException {
		 
		String code = ObjectUtils.toString(params.get("code")); 
		String value = ObjectUtils.toString(params.get("value"));
		  
		String str = getResult(code,value);
		 
		Writer out = env.getOut();  
		out.write(str.toString());
		 
	}
	
	public static String getResult(String code,String value) {
		
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(WebUtil.getRequest().getSession().getServletContext());
		ValueSetService service = ctx.getBean(ValueSetService.class);
		List<ValueSet> list = null;
		if("destination".equals(code)) {
			list = service.selectValueSetDestinationList();
		} else if("airline".equals(code)) {
			list = service.selectValueSetAirlineList();
		} else {
			list = service.selectValueSetList(code);
		} 
		
		StringBuilder str = new StringBuilder();
		
		List<String> selectedList = new ArrayList<String>();
		if(StringUtils.isNotBlank(value))
			selectedList.addAll(Arrays.asList(StringUtils.split(value,",")));
		
		String resultStr = null;
		for(ValueSet entity:list) {
			resultStr = WebUtil.write(entity.getName(), entity.getValue(), selectedList);
			if(StringUtils.isEmpty(resultStr)) continue;
			
			if(StringUtils.isNotEmpty(str.toString())) str.append(",");
			str.append(resultStr);
		}
		
		return str.toString();
	}

}