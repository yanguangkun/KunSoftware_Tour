package com.kunsoftware.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kunsoftware.exception.KunSoftwareException;

public class PermissionAnnotationInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = LoggerFactory.getLogger(PermissionAnnotationInterceptor.class);	
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("权限验证");
        HandlerMethod handler2=(HandlerMethod) handler;
        Permission permission = handler2.getMethodAnnotation(Permission.class);

        if(null == permission){
            //没有声明权限,放行
            return true;
        } else {
        	throw new KunSoftwareException("没有权限访问!");
        }
	}

}
