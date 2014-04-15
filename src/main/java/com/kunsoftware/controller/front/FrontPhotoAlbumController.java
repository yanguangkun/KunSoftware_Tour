package com.kunsoftware.controller.front;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kunsoftware.controller.manager.PhotoAlbumController;
import com.kunsoftware.entity.PhotoAlbum;
import com.kunsoftware.entity.PhotoAlbumTimeline;
import com.kunsoftware.entity.PhotoAlbumTimelineContent;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.page.PageUtil;
import com.kunsoftware.service.PhotoAlbumService;
import com.kunsoftware.service.PhotoAlbumTimelineContentService;
import com.kunsoftware.service.PhotoAlbumTimelineService;
import com.kunsoftware.service.ValueSetService;

@Controller 
@RequestMapping("/photoalbum")
public class FrontPhotoAlbumController {

private static Logger logger = LoggerFactory.getLogger(PhotoAlbumController.class);	
	
	@Autowired
	private PhotoAlbumService service;
	
	@Autowired
	private PhotoAlbumTimelineService timelineService;
	
	@Autowired
	private PhotoAlbumTimelineContentService contentService;
	
	@Autowired
	private ValueSetService valueSetService;
	
	@RequestMapping("/list")
	public String listPhotoAlbum(ModelMap model,String destination,PageInfo pageInfo) throws KunSoftwareException {
		 
		logger.info("相册游记列表");  
		pageInfo.setPageSize(9);
		List<PhotoAlbum> list = service.getPhotoAlbumListPage(destination,null,"1",pageInfo); 
		for(PhotoAlbum photoAlbum:list) {
			List<PhotoAlbumTimeline> timelineList = timelineService.getPhotoAlbumTimelineListAll(photoAlbum.getId());
			for(PhotoAlbumTimeline timeline:timelineList) {
				List<PhotoAlbumTimelineContent> contentList = contentService.getPhotoAlbumTimelineContentListAll(timeline.getId());
				timeline.setContentList(contentList);
			}
			photoAlbum.setTimelineList(timelineList);
		} 
		
		model.addAttribute("retList", list);
		model.addAttribute("destinationList", valueSetService.selectValueSetDestinationList());
		PageUtil.frontPageInfo(model, pageInfo);
		return "front/photoalbum-list";
	}
}
