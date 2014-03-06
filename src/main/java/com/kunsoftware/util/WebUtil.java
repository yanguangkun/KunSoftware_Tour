package com.kunsoftware.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kunsoftware.entity.SysUser;

public class WebUtil {

	public static String User_Info = "User_Info";
	public static String User_Role = "User_Role";
	public static ApplicationContext context;
	private static Object lockContext = new Object();
	 
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	public static SysUser getUserEntity() {
		HttpServletRequest request = getRequest();
		SysUser sysUser = (SysUser)request.getSession().getAttribute(User_Info);
		return sysUser;
	}
	
	public static Integer getUserId() {
		SysUser sysUser = getUserEntity();
		return sysUser.getId();
	}
	
	public static String getUserName() {
		SysUser sysUser = getUserEntity();
		return sysUser.getUserName();
	}
	
	public static String getContextPath() {
		return getRequest().getContextPath();
	}

	public static <T> T getBean(String beanName,Class<T> clazz){		
		init();
		return context.getBean(beanName, clazz); 
	}
	
	public static void init() {
		
		if(context == null) {
			synchronized(lockContext) {
				try {
					context = ContextLoader.getCurrentWebApplicationContext();  
					if(context == null)
						context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml"); 
				} catch(Exception e) {
					context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");  
				}
			} 
		}
	}
	
	public static String getLink(String link,String parentLink) {
		
		if(StringUtils.indexOf(link,"http://") == -1 && StringUtils.indexOf(link,"https://") == -1) {
			link = parentLink + link;
		}
		return link;
	}
	
	public static String option(String text,String value,String selectedValue) {
		 
		String selected = value.equals(selectedValue)?"selected":"";
		String s = "<option value=\""+value+"\" "+selected+">"+text+"</option>";
		
		return s;
	}
	
	public static String radio(String name,String id,String text,String value,String selectedValue) {
		 
		String selected = value.equals(selectedValue)?"checked":"";
		String s = "<input type=\"radio\" name=\""+name+"\" "+selected+" id=\""+id+"\" value=\""+value+"\" /> " + text + " ";
		
		return s;
	}
	
	public static String radioTag(String name,String id,String text,String value,String selectedValue) {
		 
		String selected = value.equals(selectedValue)?"checked":"";
		String s = "<input type=\"radio\" class=\"tag\" name=\""+name+"\" "+selected+" id=\""+id+"\" value=\""+value+"\" /> " + text + " ";
		
		return s;
	}
	
	public static String checkbox(String name,String id,String text,String value,List selectedValue) {
		 
		String selected = selectedValue.contains(value)?"checked":"";
		String s = "<input type=\"checkbox\" name=\""+name+"\" "+selected+" id=\""+id+"\" value=\""+value+"\" /> " + text + " ";
		
		return s;
	}
	
	public static String write(String text,String value,List selectedValue) {
		 
		String selected = selectedValue.contains(value)?text:"";
		
		
		return selected;
	}
}
