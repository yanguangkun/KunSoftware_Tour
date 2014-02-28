package com.kunsoftware.controller.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kunsoftware.entity.SysUser;
import com.kunsoftware.service.UserService;
import com.kunsoftware.util.WebUtil;

@Controller 
@RequestMapping("/manager")
public class LoginController {

private static Logger logger = LoggerFactory.getLogger(LoginController.class);	
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String userLogin(ModelMap model,HttpServletRequest request) {
		
		String referer = (String)request.getAttribute("referer"); 
		String loginMsg = request.getParameter("loginMsg");
		model.addAttribute("referer", referer);
		model.addAttribute("loginMsg", loginMsg);
		 
		return "manager/login";
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String userLogin(ModelMap model,HttpServletRequest request, HttpServletResponse response,String userName,String password,String referer,String remember) {
		
		logger.info("登录!");
		
		if(StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
			model.addAttribute("loginMsg", "用户名或密码不能为空!"); 
			model.addAttribute("userName", userName); 
			model.addAttribute("referer", referer);
			
			return "manager/login";
		}
		
		SysUser entity = service.selectByUserName(userName);
		if(entity == null) {
			model.addAttribute("loginMsg", "用户不存在!"); 
			model.addAttribute("userName", userName);
			model.addAttribute("referer", referer);
			return "manager/login";
		}
		
		if(!password.equals(entity.getPassword())) {
			model.addAttribute("loginMsg", "用户名或密码错误!"); 
			model.addAttribute("userName", userName); 
			model.addAttribute("referer", referer);
			return "manager/login";
		}  
		HttpSession session = request.getSession();  
		session.setAttribute(WebUtil.User_Info, entity); 
		
		if(StringUtils.isNotEmpty(referer)) {
			referer = StringUtils.replace(referer, request.getContextPath(), "");
			return "redirect:" + referer;
		} else {
			return "redirect:/manager/menu/list";
		} 
	}
	
	@RequestMapping("/logout")
	public String userLogout(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();  
		session.setAttribute(WebUtil.User_Info, null); 
		return "redirect:/manager/login"; 
	}
}
