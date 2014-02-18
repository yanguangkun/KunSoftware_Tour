package com.kunsoftware.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.kunsoftware.bean.JsonBean;

public class ExceptionResolver extends SimpleMappingExceptionResolver {

	protected ModelAndView doResolveException(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) {
		 
		String viewName = determineViewName(ex, request);
		if (viewName != null) {// JSP格式返回
			if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request
					.getHeader("X-Requested-With")!= null && request
					.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
				// 如果不是异步请求
				// Apply HTTP status code for error views, if specified.
				// Only apply it if we're processing a top-level request.
				Integer statusCode = determineStatusCode(request, viewName);
				if (statusCode != null) {
					applyStatusCodeIfPossible(request, response, statusCode);
				}
				return getModelAndView(viewName, ex, request);
			} else {// JSON格式返回
				try {
					response.setCharacterEncoding("utf-8");
					PrintWriter writer = response.getWriter();
					JsonBean jsonBean = new JsonBean();
					jsonBean.setSuccess("0");
					jsonBean.setMessage(ex.getMessage());
					String jsonStr = JSONObject.valueToString(jsonBean);
					writer.write(jsonStr);
					writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null; 
			}
		} else {
			return null;
		}
	}

}
