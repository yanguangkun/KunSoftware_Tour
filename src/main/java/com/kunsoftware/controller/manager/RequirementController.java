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
import com.kunsoftware.bean.RequirementRequestBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Requirement;
import com.kunsoftware.entity.RequirementRemark;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.RequirementRemarkService;
import com.kunsoftware.service.RequirementService;

@Controller 
@RequestMapping("/manager/requirement")
public class RequirementController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(RequirementController.class);	
	
	@Autowired
	private RequirementService service;
	
	@Autowired
	private RequirementRemarkService requirementRemarkService;
	
	@RequestMapping("/list")
	public String listRequirement(ModelMap model,String status,String arriveRequirement,String linkman,String mobile,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("需求单列表");  
		 
		List<Requirement> list = service.getRequirementListPage(status,arriveRequirement,linkman,mobile,pageInfo); 
		model.addAttribute("retList", list);
		PageUtil.pageInfo(model, pageInfo);
		return "manager/requirement/requirement-list";
	}
	
	@RequestMapping("/add")
	public String addRequirement(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加需求单");
		 
		return "manager/requirement/requirement-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addRequirement(RequirementRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存需求单");  
		Requirement entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editRequirement(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑需求单");
		Requirement entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/requirement/requirement-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editRequirement(RequirementRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存需求单");  
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delRequirement(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除需求单");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}

	@RequestMapping(value="/status.json")
	@ResponseBody 
	public JsonBean statusRequirement(Integer[] id,String status) throws KunSoftwareException {
		 
		logger.info("状态需求单"); 
		 
		service.updateStatusByIds(id, status);
		JsonBean jsonBean = new JsonBean(); 
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}

	@RequestMapping("/view")
	public String viewRequirement(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("查看需求单");
		List<RequirementRemark> list = requirementRemarkService.getRequirementRemarkListAll(id);
		Requirement entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		model.addAttribute("retList", list);
		 
		return "manager/requirement/requirement-view";
	}
}
