package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.PhotoAlbumTimelineContentRequestBean;
import com.kunsoftware.entity.PhotoAlbumTimelineContent;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.PhotoAlbumTimelineContentMapper;
import com.kunsoftware.util.FileUtil;

@Service
public class PhotoAlbumTimelineContentService {

private static Logger logger = LoggerFactory.getLogger(PhotoAlbumTimelineContentService.class);	
	
	@Autowired
	private PhotoAlbumTimelineContentMapper mapper;
	
	public List<PhotoAlbumTimelineContent> getPhotoAlbumTimelineContentListAll(Integer photoAlbumTimelineId) {
		 
		logger.info("query");
		return mapper.getPhotoAlbumTimelineContentListAll(photoAlbumTimelineId);
	}
	 
	@Transactional
	public PhotoAlbumTimelineContent insert(PhotoAlbumTimelineContentRequestBean requestBean) throws KunSoftwareException {		 
		
		PhotoAlbumTimelineContent record = new PhotoAlbumTimelineContent();
		BeanUtils.copyProperties(requestBean, record); 
		 
		if(requestBean.getImageFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getImageFile());
			record.setImagePath(imagePath);
		}
		
		mapper.insert(record);
		return record;
	}
	
	public PhotoAlbumTimelineContent selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(PhotoAlbumTimelineContentRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		PhotoAlbumTimelineContent record = mapper.selectByPrimaryKey(id); 
		BeanUtils.copyProperties(requestBean, record);
		 
		if(requestBean.getImageFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getImageFile());
			record.setImagePath(imagePath);
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
