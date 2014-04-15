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
import com.kunsoftware.bean.PhotoAlbumTimelineContentRequestBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.PhotoAlbum;
import com.kunsoftware.entity.PhotoAlbumTimeline;
import com.kunsoftware.entity.PhotoAlbumTimelineContent;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.service.PhotoAlbumService;
import com.kunsoftware.service.PhotoAlbumTimelineContentService;
import com.kunsoftware.service.PhotoAlbumTimelineService;

@Controller 
@RequestMapping("/manager/photoalbumtimelinecontent")
public class PhotoAlbumTimelineContentController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(PhotoAlbumTimelineContentController.class);	
	
	@Autowired
	private PhotoAlbumTimelineContentService service;
	
	@Autowired
	private PhotoAlbumTimelineService photoAlbumTimelineService;
	
	@Autowired
	private PhotoAlbumService photoAlbumService;
	
	@RequestMapping("/list")
	public String listPhotoAlbumTimelineContent(ModelMap model,Integer photoAlbumTimelineId) throws KunSoftwareException {
		 
		logger.info("时间轴内容列表");
		
		PhotoAlbumTimeline photoAlbumTimeline = photoAlbumTimelineService.selectByPrimaryKey(photoAlbumTimelineId);
		PhotoAlbum photoAlbum = photoAlbumService.selectByPrimaryKey(photoAlbumTimeline.getPhotoAlbumId());
		 
		List<PhotoAlbumTimelineContent> list = service.getPhotoAlbumTimelineContentListAll(photoAlbumTimelineId); 
		model.addAttribute("retList", list);
		model.addAttribute("photoAlbumTimeline", photoAlbumTimeline);
		model.addAttribute("photoAlbum", photoAlbum);
		return "manager/photoalbumtimelinecontent/photoalbumtimelinecontent-list";
	}
	
	@RequestMapping("/add")
	public String addPhotoAlbumTimelineContent(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加时间轴内容");
		 
		return "manager/photoalbumtimelinecontent/photoalbumtimelinecontent-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addPhotoAlbumTimelineContent(@RequestParam(value = "photoalbumImageFile", required = false) MultipartFile file,PhotoAlbumTimelineContentRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存时间轴内容");  
		requestBean.setImageFile(file);
		PhotoAlbumTimelineContent entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editPhotoAlbumTimelineContent(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑时间轴内容");
		PhotoAlbumTimelineContent entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/photoalbumtimelinecontent/photoalbumtimelinecontent-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editPhotoAlbumTimelineContent(@RequestParam(value = "photoalbumImageFile", required = false) MultipartFile file,PhotoAlbumTimelineContentRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存时间轴内容"); 
		requestBean.setImageFile(file);
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delPhotoAlbumTimelineContent(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除时间轴内容");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}


}
