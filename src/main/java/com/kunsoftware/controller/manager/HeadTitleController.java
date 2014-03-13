package com.kunsoftware.controller.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kunsoftware.bean.HeadIconTitleRequestBean;
import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.HeadIconTitle;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.service.HeadIconTitleService;

@Controller 
@RequestMapping("/manager/headtitle")
public class HeadTitleController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(HeadTitleController.class);	
	
	@Autowired
	private HeadIconTitleService service;
	
	@RequestMapping("/list")
	public String listHeadTitle(ModelMap model) throws KunSoftwareException {
		 
		logger.info("头衔列表");  
		 
		List<HeadIconTitle> list = service.getHeadIconTitleListAll("2"); 
		model.addAttribute("retList", list);
		return "manager/headtitle/headtitle-list";
	}
	
	@RequestMapping("/add")
	public String addHeadTitle(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加头衔");
		 
		return "manager/headtitle/headtitle-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addHeadTitle(HeadIconTitleRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存头衔"); 
		requestBean.setType("2");
		HeadIconTitle entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editHeadTitle(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑头衔");
		HeadIconTitle entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/headtitle/headtitle-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editHeadTitle(HeadIconTitleRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存头衔");
		requestBean.setType("2");
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delHeadTitle(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除头衔");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}

}
