package com.kunsoftware.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.kunsoftware.entity.SysMenu;
import com.kunsoftware.service.MenuService;
import com.kunsoftware.util.WebUtil;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class MenuDirective implements TemplateDirectiveModel {

	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map params, TemplateModel[] loopVars, 
			TemplateDirectiveBody body) throws TemplateException, IOException { 
		
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(WebUtil.getRequest().getSession().getServletContext());
		MenuService service = ctx.getBean(MenuService.class);
		
		StringBuilder str = new StringBuilder();
		StringBuilder strChild = null;
		
		String currentUri = WebUtil.getRequest().getRequestURI();
		String active = null;
		boolean isActive = false;
		List<SysMenu> root = service.getMenuListRoot();
		List<SysMenu> child = null;
		
		for(SysMenu rootMenu:root) {
			child = service.getMenuListByTree(rootMenu.getMenuEname()); 
			strChild = new StringBuilder();
			active = ""; 
			if(!child.isEmpty()) {
				
				strChild.append("<ul class=\"dropdown-menu\">");
				for(SysMenu childMenu:child) {
					if(!isActive && currentUri.equals(WebUtil.getContextPath() + childMenu.getMenuUrl())) {
						active = "active";
						isActive = true;
					}
					strChild.append("<li><a href=\""+WebUtil.getContextPath()+ childMenu.getMenuUrl() +"\">"+childMenu.getMenuCname()+"</a></li>");
					if("1".equals(childMenu.getMenuDivider())) {
						strChild.append("<li class=\"divider\"></li>");
					}
				}
				strChild.append("</ul>");
			}  
			 
			if(!isActive ) { 
				active = getActive(rootMenu.getMenuEname(),currentUri);
			}
			
			str.append("<ul class=\"nav navbar-nav\">");
			str.append("<li class=\"dropdown "+active+"\"> <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">"+rootMenu.getMenuCname()+" <b class=\"caret\"></b></a>");
			str.append(strChild);
			str.append("</li></ul>");
		}
		 
		Writer out = env.getOut();  
		out.write(str.toString());
	} 
	
	public static Map<String,String> urlMap = new HashMap<String,String>();
	
	static {
		urlMap.put("/manager/country/add", "base_data");
		urlMap.put("/manager/country/edit", "base_data");
		urlMap.put("/manager/city/add", "base_data");
		urlMap.put("/manager/city/edit", "base_data");
		urlMap.put("/manager/destination/add", "base_data");
		urlMap.put("/manager/destination/edit", "base_data");
		urlMap.put("/manager/airline/add", "base_data");
		urlMap.put("/manager/airline/edit", "base_data");
		urlMap.put("/manager/product/add", "base_data");
		urlMap.put("/manager/product/edit", "base_data");
		urlMap.put("/manager/productintroduce/list", "base_data");
		urlMap.put("/manager/productintroduce/add", "base_data");
		urlMap.put("/manager/productintroduce/edit", "base_data");
		urlMap.put("/manager/ground/add", "base_data");
		urlMap.put("/manager/ground/edit", "base_data");
		urlMap.put("/manager/groundtag/add", "base_data");
		urlMap.put("/manager/groundtag/edit", "base_data");
		urlMap.put("/manager/gallery/add", "base_data");
		urlMap.put("/manager/gallery/edit", "base_data");
		
		urlMap.put("/manager/gallery/add", "ziyuan_manager");
		urlMap.put("/manager/gallery/edit", "ziyuan_manager");
		urlMap.put("/manager/flightsegment/list", "ziyuan_manager");
		urlMap.put("/manager/flightsegment/add", "ziyuan_manager");
		urlMap.put("/manager/flightsegment/edit", "ziyuan_manager");
		urlMap.put("/manager/productresource/add", "ziyuan_manager");
		urlMap.put("/manager/productresource/edit", "ziyuan_manager");		
		urlMap.put("/manager/productpricetpl/list", "ziyuan_manager");
		urlMap.put("/manager/productpricetpl/add", "ziyuan_manager");
		urlMap.put("/manager/productpricetpl/edit", "ziyuan_manager");
		urlMap.put("/manager/productplantpl/list", "ziyuan_manager");
		urlMap.put("/manager/productplantpl/add", "ziyuan_manager");
		urlMap.put("/manager/productplantpl/edit", "ziyuan_manager");
		urlMap.put("/manager/flightchedule/list", "ziyuan_manager");
		urlMap.put("/manager/flightchedule/add", "ziyuan_manager");
		urlMap.put("/manager/flightchedule/edit", "ziyuan_manager");
		urlMap.put("/manager/flightcheduleplan/list", "ziyuan_manager");
		urlMap.put("/manager/flightcheduleplan/add", "ziyuan_manager");
		urlMap.put("/manager/flightcheduleplan/edit", "ziyuan_manager");
		urlMap.put("/manager/flightcheduleprice/list", "ziyuan_manager");
		urlMap.put("/manager/flightcheduleprice/add", "ziyuan_manager");
		urlMap.put("/manager/flightcheduleprice/edit", "ziyuan_manager");
		
	}
	
	public String getActive(String menuEname,String currentUri) {
		
		currentUri = StringUtils.replace(currentUri, WebUtil.getContextPath(), "");
		if(menuEname.equals(urlMap.get(currentUri))) {
			return "active";
		}
		return "";
	}
	 
}
