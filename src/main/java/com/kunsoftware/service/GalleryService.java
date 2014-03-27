package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.GalleryRequestBean;
import com.kunsoftware.entity.Gallery;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.GalleryMapper;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.util.FileUtil;

@Service
public class GalleryService {

private static Logger logger = LoggerFactory.getLogger(GalleryService.class);	
	
	@Autowired
	private GalleryMapper mapper;
	
	public List<Gallery> getGalleryListPage(String keyword,PageInfo page) {
		 
		logger.info("query");
		return mapper.getGalleryListPage(keyword,page);
	}
	
	public List<Gallery> getGalleryListAll(String type) {
		 
		return mapper.getGalleryListAll(type);
	}
	 
	@Transactional
	public Gallery insert(GalleryRequestBean requestBean) throws KunSoftwareException {
		 
		Gallery record = new Gallery();
		BeanUtils.copyProperties(requestBean, record); 
		
		if(requestBean.getImageFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getImageFile());
			record.setImagePath(imagePath);
		}
		
		mapper.insert(record); 
		
		 
		return record;
	}
	
	public Gallery selectByPrimaryKey(Integer id) {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(GalleryRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		Gallery record = mapper.selectByPrimaryKey(id); 
		BeanUtils.copyProperties(requestBean, record);
		if(requestBean.getImageFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getImageFile());
			record.setImagePath(imagePath);
		}
		
		return mapper.updateByPrimaryKeySelective(record);
	}
	
	@Transactional
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	@Transactional
	public void deleteByPrimaryKey(Integer[] id) throws KunSoftwareException {

		for(int i = 0;i < id.length;i++) {
			mapper.deleteByPrimaryKey(id[i]);
		} 
	}
	
	@Transactional
	public void updateEnableByIds(Integer[] id,String enable) throws KunSoftwareException {
		
		for(int i = 0;i < id.length;i++) {
			Gallery record = mapper.selectByPrimaryKey(id[i]); 
			record.setEnable(enable);
			mapper.updateByPrimaryKeySelective(record);
		}  
	}
}
