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

import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.bean.CustomizeRequestBean;
import com.kunsoftware.entity.Customize;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.CustomizeService;

@Controller 
@RequestMapping("/manager/customize")
public class CustomizeController {

	private static Logger logger = LoggerFactory.getLogger(CustomizeController.class);	
	
	@Autowired
	private CustomizeService service;
	
	@RequestMapping("/list")
	public String listCustomize(ModelMap model,String arriveDestination,String type,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("定制列表"); 
		 
		List<Customize> list = service.getCustomizeListPage(arriveDestination,type,pageInfo); 
		model.addAttribute("retList", list);
		PageUtil.pageInfo(model, pageInfo);
		return "manager/customize/customize-list";
	}
	
	@RequestMapping("/add")
	public String addCustomize(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加定制");
		 
		return "manager/customize/customize-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addCustomize(@RequestParam(value = "customizeImageFile", required = false) MultipartFile file,CustomizeRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存定制");  
		requestBean.setImageFile(file);
		Customize entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功");
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editCustomize(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑定制");
		Customize entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/customize/customize-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editCustomize(@RequestParam(value = "customizeImageFile", required = false) MultipartFile file,CustomizeRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存定制"); 
		requestBean.setImageFile(file);
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delCustomize(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除定制");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}
}
