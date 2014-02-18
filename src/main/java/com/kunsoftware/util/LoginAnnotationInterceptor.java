package com.kunsoftware.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@SuppressWarnings({"unused","rawtypes"})
public class LoginAnnotationInterceptor extends HandlerInterceptorAdapter {

@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

	if(isLogin(request)) return true;
    HttpSession session = request.getSession(); 
    String uri = request.getRequestURI();
    String paramStr = getParamStr(request);
    String referer = uri + paramStr;
    request.setAttribute("referer", referer);
    return true;
}

public String getParamStr(HttpServletRequest request) {
	
	String paramStr = "";
	Enumeration enumeration = request.getParameterNames();
	String paramName = "";
	String paramValue = "";
	while(enumeration.hasMoreElements()) {
		paramName = (String)enumeration.nextElement();;
		paramValue = request.getParameter(paramName);
		if(StringUtils.isEmpty(paramStr)) {
			paramStr += "?";
		} else {
			paramStr += "&";
		}
		paramStr += paramName + "=" + paramValue;
	}
	return paramStr;
}

public boolean isLogin(HttpServletRequest request) {
	 String uri = request.getRequestURI();
	 if(uri.indexOf("user/login") != -1) {
		 return true;
	 }
	 return false;
}
}