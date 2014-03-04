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
import com.kunsoftware.bean.GroundRequestBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Ground;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.GroundService;

@Controller 
@RequestMapping("/manager/ground")
public class GroundController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(GroundController.class);	
	
	@Autowired
	private GroundService service;
	
	@RequestMapping("/list")
	public String listGround(ModelMap model,Integer destination,String type,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("地接列表"); 
		 
		List<Ground> list = service.getGroundListPage(destination,pageInfo); 
		model.addAttribute("retList", list);
		PageUtil.pageInfo(model, pageInfo);
		return "manager/ground/ground-list";
	}
	
	@RequestMapping("/add")
	public String addGround(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加地接");
		 
		return "manager/ground/ground-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addGround(@RequestParam(value = "groundImageFile", required = false) MultipartFile file,
			@RequestParam(value = "qualificationImage", required = false) MultipartFile qualificationImage,
			GroundRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存地接");  
		requestBean.setImageFile(file);
		requestBean.setQualificationImageFile(qualificationImage);
		Ground entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功");
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editGround(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑地接");
		Ground entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/ground/ground-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editGround(@RequestParam(value = "groundImageFile", required = false) MultipartFile file,
			@RequestParam(value = "qualificationImage", required = false) MultipartFile qualificationImage,
			GroundRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存地接"); 
		requestBean.setImageFile(file);
		requestBean.setQualificationImageFile(qualificationImage);
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delGround(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除地接");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}
 

}
