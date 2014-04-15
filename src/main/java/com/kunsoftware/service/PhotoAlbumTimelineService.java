package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.PhotoAlbumTimelineRequestBean;
import com.kunsoftware.entity.PhotoAlbumTimeline;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.PhotoAlbumTimelineMapper;

@Service
public class PhotoAlbumTimelineService {

	private static Logger logger = LoggerFactory.getLogger(PhotoAlbumTimelineService.class);	
	
	@Autowired
	private PhotoAlbumTimelineMapper mapper;
	
	public List<PhotoAlbumTimeline> getPhotoAlbumTimelineListAll(Integer photoAlbumId) {
		 
		logger.info("query");
		return mapper.getPhotoAlbumTimelineListAll(photoAlbumId);
	}
	 
	@Transactional
	public PhotoAlbumTimeline insert(PhotoAlbumTimelineRequestBean requestBean) throws KunSoftwareException {		 
		
		PhotoAlbumTimeline record = new PhotoAlbumTimeline();
		BeanUtils.copyProperties(requestBean, record); 
		 
		mapper.insert(record);
		return record;
	}
	
	public PhotoAlbumTimeline selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(PhotoAlbumTimelineRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		PhotoAlbumTimeline record = mapper.selectByPrimaryKey(id); 
		BeanUtils.copyProperties(requestBean, record);
		 
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
