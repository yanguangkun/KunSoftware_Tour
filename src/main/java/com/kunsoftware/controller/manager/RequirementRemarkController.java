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
import com.kunsoftware.bean.RequirementRemarkRequestBean;
import com.kunsoftware.entity.Requirement;
import com.kunsoftware.entity.RequirementRemark;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.service.RequirementRemarkService;
import com.kunsoftware.service.RequirementService;

@Controller 
@RequestMapping("/manager/requirementremark")
public class RequirementRemarkController {

	private static Logger logger = LoggerFactory.getLogger(RequirementRemarkController.class);	
	
	@Autowired
	private RequirementRemarkService service;
	
	@Autowired
	private RequirementService requirementService;
	
	@RequestMapping("/list")
	public String listRequirementRemark(ModelMap model,Integer requirementId) throws KunSoftwareException {
		 
		logger.info("需求备注单列表");  
		 
		Requirement requirement = requirementService.selectByPrimaryKey(requirementId);
		List<RequirementRemark> list = service.getRequirementRemarkListAll(requirementId); 
		model.addAttribute("retList", list);
		model.addAttribute("requirement", requirement);
		return "manager/requirementremark/requirementremark-list";
	}	 
	
	@RequestMapping("/add")
	public String addRequirementRemark(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加需求备注单");
		 
		return "manager/requirementremark/requirementremark-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addRequirementRemark(RequirementRemarkRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存需求备注单");  
		RequirementRemark entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	} 
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delRequirementRemark(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除需求备注单");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}
}
