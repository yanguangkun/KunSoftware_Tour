package com.kunsoftware.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.PhotoAlbumRequestBean;
import com.kunsoftware.entity.PhotoAlbum;
import com.kunsoftware.entity.PhotoAlbumTimeline;
import com.kunsoftware.entity.PhotoAlbumTimelineContent;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.PhotoAlbumMapper;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.util.FileUtil;

@Service
public class PhotoAlbumService {

private static Logger logger = LoggerFactory.getLogger(PhotoAlbumService.class);	
	
	@Autowired
	private PhotoAlbumMapper mapper;
	
	@Autowired
	private PhotoAlbumTimelineService timelineService;	
	
	@Autowired
	private PhotoAlbumTimelineContentService contentService;
	
	public List<PhotoAlbum> getPhotoAlbumListPage(String destination,String keyword,String frontDesk,PageInfo page) {
		 
		logger.info("query");
		return mapper.getPhotoAlbumListPage(destination,keyword,frontDesk,page);
	}
	 
	@Transactional
	public PhotoAlbum insert(PhotoAlbumRequestBean requestBean) throws KunSoftwareException {		 
		
		PhotoAlbum record = new PhotoAlbum();
		BeanUtils.copyProperties(requestBean, record); 
		
		if(requestBean.getImageFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getImageFile());
			record.setImagePath(imagePath);
		}
		
		if(requestBean.getIndexImageFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getIndexImageFile());
			record.setIndexImagePath(imagePath);
		}
		
		mapper.insert(record);
		return record;
	}
	
	public PhotoAlbum selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	public PhotoAlbum selectByIndexRecommend(@Param("indexRecommend") String indexRecommend) {
		
		PhotoAlbum photoAlbum = mapper.selectByIndexRecommend(indexRecommend);
		if(photoAlbum == null) photoAlbum = new PhotoAlbum();
		List<PhotoAlbumTimeline> timelineList = timelineService.getPhotoAlbumTimelineListAll(photoAlbum.getId());
		for(PhotoAlbumTimeline timeline:timelineList) {
			List<PhotoAlbumTimelineContent> contentList = contentService.getPhotoAlbumTimelineContentListAll(timeline.getId());
			timeline.setContentList(contentList);
		}
		photoAlbum.setTimelineList(timelineList);
		return photoAlbum;
	}
	
	@Transactional
	public int updateByPrimaryKey(PhotoAlbumRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		PhotoAlbum record = mapper.selectByPrimaryKey(id); 
		BeanUtils.copyProperties(requestBean, record);
		
		if(requestBean.getImageFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getImageFile());
			record.setImagePath(imagePath);
		}
		
		if(requestBean.getIndexImageFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getIndexImageFile());
			record.setIndexImagePath(imagePath);
		}
		
		return mapper.updateByPrimaryKeySelective(record);
	}
	 
	
	@Transactional
	public int deleteByPrimaryKey(Integer id) throws KunSoftwareException {
		return mapper.deleteByPrimaryKey(id);
	}
	
	@Transactional
	public void deleteByPrimaryKey(Integer[] id) throws KunSoftwareException {

		for(int i = 0;i < id.length;i++) {
			mapper.deleteByPrimaryKey(id[i]);
		} 
	}
}
