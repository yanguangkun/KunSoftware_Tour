package com.kunsoftware.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kunsoftware.entity.SysUser;

@SuppressWarnings({"rawtypes"})
public class LoginAnnotationInterceptor extends HandlerInterceptorAdapter {

@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    HttpSession session = request.getSession(); 
    String uri = request.getRequestURI();
    String paramStr = getParamStr(request);
    
    SysUser userEntity = (SysUser)session.getAttribute(WebUtil.User_Info);

    if(isNotRole(request)) { 
    	return true;
    }
    
    if(isLogin(request)) {
    	if (null != userEntity) {
    		response.sendRedirect(request.getContextPath() + "/manager/productresource/list");
    		//request.getRequestDispatcher("/manager/productresource/list").forward(request, response);
    		return false;
    	}
		return true;
	}
    
    if(uri.indexOf("manager") != -1) {
    	String referer = uri + paramStr;
        request.setAttribute("referer", referer);
	    if (null == userEntity) {
	    	request.getRequestDispatcher("/manager/login?loginMsg=请登录后再操作!").forward(request, response); 
	        return false;
	    }
    }
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
	 if(uri.indexOf("manager/login") != -1) {
		 return true;
	 }
	 return false;
}

public boolean isNotRole(HttpServletRequest request) {
	 String uri = request.getRequestURI();
	 if(uri.indexOf("manager/cascade") != -1) {
		 return true;
	 }
	 return false;
}

}