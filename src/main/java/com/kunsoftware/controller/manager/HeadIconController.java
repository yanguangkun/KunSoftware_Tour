package com.kunsoftware.controller.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kunsoftware.bean.HeadIconTitleRequestBean;
import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.HeadIconTitle;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.service.HeadIconTitleService;

@Controller 
@RequestMapping("/manager/headicon")
public class HeadIconController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(HeadIconController.class);	
	
	@Autowired
	private HeadIconTitleService service;
	
	@RequestMapping("/list")
	public String listHeadIcon(ModelMap model) throws KunSoftwareException {
		 
		logger.info("头像列表");  
		 
		List<HeadIconTitle> list = service.getHeadIconTitleListAll("1"); 
		model.addAttribute("retList", list);
		return "manager/headicon/headicon-list";
	}
	
	@RequestMapping("/add")
	public String addHeadIcon(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加头像");
		 
		return "manager/headicon/headicon-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addHeadIcon(@RequestParam(value = "headIconImageFile", required = false) MultipartFile file,HeadIconTitleRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存头像"); 
		requestBean.setType("1");
		requestBean.setImageFile(file);
		HeadIconTitle entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editHeadIcon(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑头像");
		HeadIconTitle entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/headicon/headicon-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editHeadIcon(@RequestParam(value = "headIconImageFile", required = false) MultipartFile file,HeadIconTitleRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存头像");
		requestBean.setType("1");
		requestBean.setImageFile(file);
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delHeadIcon(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除头像");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}

}
