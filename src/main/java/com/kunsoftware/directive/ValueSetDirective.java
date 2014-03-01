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

/**
 * 值集.
 * 
 * <@ValueSet name="country" code="country" value="1" />
 */
public class ValueSetDirective implements TemplateDirectiveModel {

	/** 下拉框 */
	public static String type0 = "0";
	
	/** 复选框 */
	public static String type1 = "1";
	
	/** 单选框 */
	public static String type2 = "2";
	
	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		 
		String code = ObjectUtils.toString(params.get("code")); 
		String value = ObjectUtils.toString(params.get("value"));
		String name = ObjectUtils.toString(params.get("name"));
		String id = ObjectUtils.toString(params.get("id"));
		if(StringUtils.isNotEmpty(name) && StringUtils.isEmpty(id)) {
			id = name;
		}
		 
		String str = getResult(code,value,name,id);
		
		Writer out = env.getOut();  
		out.write(str);
	}
	
	public static String getResult(String code,String value,String name,String id) {
		
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
		for(ValueSet entity:list) {
			
			if(type0.equals(entity.getType())) {
			str.append(WebUtil.option(entity.getName(), entity.getValue(), value));
			} else if(type1.equals(entity.getType())) {
				List<String> selectedList = new ArrayList<String>();
				if(StringUtils.isNotBlank(value))
					selectedList.addAll(Arrays.asList(StringUtils.split(value,",")));
				str.append(WebUtil.checkbox(name,id,entity.getName(), entity.getValue(), selectedList));
			} else if(type2.equals(entity.getType())) {
				str.append(WebUtil.radio(name,id,entity.getName(), entity.getValue(), value));
			}
		}
		
		return str.toString();
	}

}
