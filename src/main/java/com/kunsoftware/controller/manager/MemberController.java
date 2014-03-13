package com.kunsoftware.controller.manager;

import java.util.List;

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

@Controller 
@RequestMapping("/manager/member")
public class MemberController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(MemberController.class);	
	
	@Autowired
	private MemberService service;	 
	
	@RequestMapping("/list")
	public String listMember(ModelMap model) throws KunSoftwareException {
		 
		logger.info("会员列表");  
		 
		List<Member> list = service.getMemberListAll(); 
		model.addAttribute("retList", list); 
		return "manager/member/member-list";
	}
	
	@RequestMapping("/add")
	public String addMember(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加会员");
		 
		return "manager/member/member-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addMember(MemberRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存会员"); 

		Member entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editMember(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑会员");
		Member entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/member/member-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editMember(MemberRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存会员"); 
		 
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delMember(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除会员");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}


}
