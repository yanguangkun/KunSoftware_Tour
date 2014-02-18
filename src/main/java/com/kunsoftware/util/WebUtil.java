package com.kunsoftware.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class WebUtil {

	public static String User_Info = "User_Info";
	public static String User_Role = "User_Role";
	public static ApplicationContext context;
	private static Object lockContext = new Object();
	 
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
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
}
