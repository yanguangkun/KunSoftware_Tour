package com.kunsoftware.controller.front;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.bean.MemberRequestBean;
import com.kunsoftware.entity.Member;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.service.MemberService;
import com.kunsoftware.service.ValueSetService;
import com.kunsoftware.util.WebUtil;

@Controller
public class FrontLoginController {

	private static Logger logger = LoggerFactory.getLogger(FrontLoginController.class);	
	
	@Autowired
	private MemberService service;
	
	@Autowired 
	private ValueSetService valueSetService;
	
	@RequestMapping(value = "/m/login", method = RequestMethod.GET)
	public String login(ModelMap model,HttpServletRequest request) {
		
		String referer = (String)request.getAttribute("referer"); 
		String loginMsg = request.getParameter("loginMsg");
		model.addAttribute("referer", referer);
		model.addAttribute("loginMsg", loginMsg);
		model.addAttribute("destinationList", valueSetService.selectValueSetDestinationList());
		return "front/login";
	}
	
	@RequestMapping(value = "/m/login",method = RequestMethod.POST)
	public String userLogin(ModelMap model,HttpServletRequest request, HttpServletResponse response,String userName,String password,String referer,String remember) {
		
		logger.info("登录!");
		model.addAttribute("destinationList", valueSetService.selectValueSetDestinationList());
		if(StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
			model.addAttribute("loginMsg", "用户名或密码不能为空!"); 
			model.addAttribute("userName", userName); 
			model.addAttribute("referer", referer);
			
			return "front/login";
		}
		
		Member entity = service.selectByUserName(userName);
		if(entity == null) {
			entity = service.selectByEmail(userName);
		}
		
		if(entity == null) {
			model.addAttribute("loginMsg", "用户不存在!"); 
			model.addAttribute("userName", userName);
			model.addAttribute("referer", referer);
			return "front/login";
		}
		
		if(!password.equals(entity.getPassword())) {
			model.addAttribute("loginMsg", "用户名或密码错误!"); 
			model.addAttribute("userName", userName); 
			model.addAttribute("referer", referer);
			return "front/login";
		}  
		HttpSession session = request.getSession();  
		session.setAttribute(WebUtil.Member_Info, entity); 
		
		if(StringUtils.isNotEmpty(referer)) {
			referer = StringUtils.replace(referer, request.getContextPath(), "");
			return "redirect:" + referer;
		} else {
			return "redirect:/index";
		} 
	}
	
	@RequestMapping(value = "/reg", method = RequestMethod.GET)
	public String reg(ModelMap model) {
		
		model.addAttribute("destinationList", valueSetService.selectValueSetDestinationList());
		return "front/reg";
	}
	
	@RequestMapping("/m/logout")
	public String userLogout(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();  
		session.setAttribute(WebUtil.Member_Info, null); 
		return "redirect:/index"; 
	}

	@RequestMapping(value="/reg.json")
	@ResponseBody 
	public JsonBean addMember(MemberRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存会员"); 
		JsonBean jsonBean = new JsonBean();
		if(service.selectByUserName(requestBean.getUserName()) != null) {			 
			jsonBean.setMessage("账号已经存在,请不要重复注册!"); 	
			return jsonBean;
		}		
		 
		if(service.selectByEmail(requestBean.getEmail()) != null) {			 
			jsonBean.setMessage("电子邮箱已经存在,请不要重复注册!"); 	
			return jsonBean;
		}
		
		Member entity = service.insert(requestBean);	 
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
}
