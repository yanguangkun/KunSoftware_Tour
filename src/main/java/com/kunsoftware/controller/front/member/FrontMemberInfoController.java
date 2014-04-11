package com.kunsoftware.controller.front.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.bean.MemberRequestBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Member;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.service.MemberService;
import com.kunsoftware.service.ValueSetService;
import com.kunsoftware.util.WebUtil;

@Controller 
@RequestMapping("/m")
public class FrontMemberInfoController extends BaseController{

	private static Logger logger = LoggerFactory.getLogger(FrontMemberInfoController.class);
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private ValueSetService valueSetService;
	
	@RequestMapping("/member/edit")
	public String editMember(ModelMap model) throws KunSoftwareException {
		 
		logger.info("编辑会员");
		
		Member entity = service.selectByPrimaryKey(WebUtil.getMemberId());
		model.addAttribute("entity", entity);  
		model.addAttribute("destinationList", valueSetService.selectValueSetDestinationList());
		 
		return "front/m/member-edit";
	}
	
	@RequestMapping(value="/member/edit.json") 
	@ResponseBody 
	public JsonBean editMember(MemberRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存会员"); 
		 
		service.updateByPrimaryKey(requestBean,WebUtil.getMemberId());		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping("/member/password")
	public String editPassword(ModelMap model) throws KunSoftwareException {
		 
		logger.info("编辑会员密码"); 
		model.addAttribute("destinationList", valueSetService.selectValueSetDestinationList());
		 
		return "front/m/member-password";
	}
	
	@RequestMapping(value="/member/password.json") 
	@ResponseBody 
	public JsonBean editPassword(String password,String newpassword,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存会员密码"); 
		JsonBean jsonBean = new JsonBean();
		
		Member entity = service.selectByPrimaryKey(WebUtil.getMemberId());
		if(!entity.getPassword().equals(password)) {
			jsonBean.setMessage("原始密码不正确!"); 
		} else {
			MemberRequestBean requestBean = new MemberRequestBean();
			requestBean.setPassword(newpassword);
			service.updateByPrimaryKey(requestBean,WebUtil.getMemberId());		
			 
			jsonBean.setMessage("操作成功"); 
		}
		
			 
		return jsonBean;
	}
}
