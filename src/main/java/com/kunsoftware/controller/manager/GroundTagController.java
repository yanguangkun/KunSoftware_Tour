package com.kunsoftware.controller.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kunsoftware.bean.GroundTagRequestBean;
import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.GroundTag;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.GroundTagService;

@Controller 
@RequestMapping("/manager/groundtag")
public class GroundTagController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(GroundTagController.class);	
	
	@Autowired
	private GroundTagService service;
	
	@RequestMapping("/list")
	public String listGroundTag(ModelMap model,Integer destination,Integer groundId,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("标签列表"); 
		 
		List<GroundTag> list = service.getGroundTagListPage(destination,groundId,pageInfo); 
		model.addAttribute("retList", list);
		PageUtil.pageInfo(model, pageInfo);
		return "manager/groundtag/groundtag-list";
	}
	
	@RequestMapping("/add")
	public String addGroundTag(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加标签");
		 
		return "manager/groundtag/groundtag-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addGroundTag(GroundTagRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存标签"); 
		GroundTag entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功");
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editGroundTag(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑标签");
		GroundTag entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/groundtag/groundtag-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editGroundTag(GroundTagRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存标签"); 
		
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delGroundTag(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除标签");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	} 

}
