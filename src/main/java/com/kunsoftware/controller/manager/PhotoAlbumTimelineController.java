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
import com.kunsoftware.bean.PhotoAlbumTimelineRequestBean;
import com.kunsoftware.controller.BaseController;
import com.kunsoftware.entity.PhotoAlbum;
import com.kunsoftware.entity.PhotoAlbumTimeline;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.service.PhotoAlbumService;
import com.kunsoftware.service.PhotoAlbumTimelineService;

@Controller 
@RequestMapping("/manager/photoalbumtimeline")
public class PhotoAlbumTimelineController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(PhotoAlbumTimelineController.class);	
	
	@Autowired
	private PhotoAlbumTimelineService service;
	
	@Autowired
	private PhotoAlbumService photoAlbumService;
	
	@RequestMapping("/list")
	public String listPhotoAlbumTimeline(ModelMap model,Integer photoAlbumId) throws KunSoftwareException {
		 
		logger.info("时间轴列表");
		
		PhotoAlbum photoAlbum = photoAlbumService.selectByPrimaryKey(photoAlbumId);
		 
		List<PhotoAlbumTimeline> list = service.getPhotoAlbumTimelineListAll(photoAlbumId); 
		model.addAttribute("retList", list);
		model.addAttribute("photoAlbum", photoAlbum);
		return "manager/photoalbumtimeline/photoalbumtimeline-list";
	}
	
	@RequestMapping("/add")
	public String addPhotoAlbumTimeline(ModelMap model) throws KunSoftwareException {
		 
		logger.info("添加时间轴");
		 
		return "manager/photoalbumtimeline/photoalbumtimeline-add";
	}
	
	@RequestMapping(value="/add.json")
	@ResponseBody 
	public JsonBean addPhotoAlbumTimeline(PhotoAlbumTimelineRequestBean requestBean) throws KunSoftwareException {
		 
		logger.info("保存时间轴");  
		PhotoAlbumTimeline entity = service.insert(requestBean);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.put("id", entity.getId());
		jsonBean.setMessage("操作成功"); 		 
		return jsonBean;
	}
	
	@RequestMapping("/edit")
	public String editPhotoAlbumTimeline(ModelMap model,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑时间轴");
		PhotoAlbumTimeline entity = service.selectByPrimaryKey(id);
		model.addAttribute("entity", entity);  
		 
		return "manager/photoalbumtimeline/photoalbumtimeline-edit";
	}
	
	@RequestMapping(value="/edit.json") 
	@ResponseBody 
	public JsonBean editPhotoAlbumTimeline(PhotoAlbumTimelineRequestBean requestBean,Integer id) throws KunSoftwareException {
		 
		logger.info("编辑保存时间轴");  
		service.updateByPrimaryKey(requestBean,id);		
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 	 
		return jsonBean;
	}
	
	@RequestMapping(value="/del.json")
	@ResponseBody 
	public JsonBean delPhotoAlbumTimeline(Integer[] id) throws KunSoftwareException {
		 
		logger.info("删除时间轴");
		service.deleteByPrimaryKey(id);
		JsonBean jsonBean = new JsonBean();
		jsonBean.setMessage("操作成功"); 
		return jsonBean;
	}

}
