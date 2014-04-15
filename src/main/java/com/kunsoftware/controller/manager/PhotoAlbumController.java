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

import com.kunsoftware.bean.PhotoAlbumRequestBean;
import com.kunsoftware.bean.JsonBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.PhotoAlbum;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.PhotoAlbumService;

@Controller 
@RequestMapping("/manager/photoalbum")
public class PhotoAlbumController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(PhotoAlbumController.class);	
	
	@Autowired
	private PhotoAlbumService service;
	
	@RequestMapping("/list")
	public String listPhotoAlbum(ModelMap model,String destination,String keyword,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("相册游记列表");  
		 
		List<PhotoAlbum> list = service.getPhotoAlbumListPage(destination,keyword,null,pageInfo); 
		model.addAttribute("retList", list);
		PageUtil.pageInfo(model, pageInfo);
		return "manager/photoalbum/photoalbum-list";
	}
	
	@RequestMapping("/add")
	public String addPhotoAlbum(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加相册游记");
		 
		return "manager/photoalbum/photoalbum-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addPhotoAlbum(@RequestParam(value = "photoalbumImageFile", required = false) MultipartFile file,
			@RequestParam(value = "photoalbumIndexImageFile", required = false) MultipartFile indexFile,PhotoAlbumRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存相册游记"); 
		requestBean.setImageFile(file);
		requestBean.setIndexImageFile(indexFile);
		PhotoAlbum entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editPhotoAlbum(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑相册游记");
		PhotoAlbum entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/photoalbum/photoalbum-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editPhotoAlbum(@RequestParam(value = "photoalbumImageFile", required = false) MultipartFile file,
			@RequestParam(value = "photoalbumIndexImageFile", required = false) MultipartFile indexFile,PhotoAlbumRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存相册游记"); 
		requestBean.setImageFile(file);
		requestBean.setIndexImageFile(indexFile);
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delPhotoAlbum(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除相册游记");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	} 

}
