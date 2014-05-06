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

import com.kunsoftware.bean.GalleryRequestBean;
import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.Gallery;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.GalleryService;

@Controller 
@RequestMapping("/manager/gallery")
public class GalleryController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(GalleryController.class);	
	
	@Autowired
	private GalleryService service;
	
	@RequestMapping("/list")
	public String listGallery(ModelMap model,String keyword,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("相册列表");  
		 
		List<Gallery> list = service.getGalleryListPage(keyword,pageInfo); 
		model.addAttribute("retList", list);
		PageUtil.pageInfo(model, pageInfo);
		return "manager/gallery/gallery-list";
	}
	
	@RequestMapping("/add")
	public String addGallery(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加相册");
		 
		return "manager/gallery/gallery-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addGallery(@RequestParam(value = "galleryImageFile", required = false) MultipartFile file,GalleryRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存相册"); 
		requestBean.setImageFile(file);
		Gallery entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editGallery(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑相册");
		Gallery entity = service.selectByPrimaryKey(id);
		
		model.addAttribute("entity", entity);
		 
		return "manager/gallery/gallery-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editGallery(@RequestParam(value = "galleryImageFile", required = false) MultipartFile file,GalleryRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存相册"); 
		requestBean.setImageFile(file);
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delGallery(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除相册");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}
	
	@RequestMapping(value="/enable.json")
	@ResponseBody 
	public JsonBean enableDestination(Integer[] id,String enable) throws KunSoftwareException {
		 
		logger.info("激活停用相册"); 
		 
		service.updateEnableByIds(id, enable);
		JsonBean jsonBean = new JsonBean(); 
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}

}
