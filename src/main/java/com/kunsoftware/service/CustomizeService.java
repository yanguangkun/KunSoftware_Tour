package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.CustomizeRequestBean;
import com.kunsoftware.entity.Customize;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.CustomizeMapper;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.util.FileUtil;

@Service
public class CustomizeService {

private static Logger logger = LoggerFactory.getLogger(CustomizeService.class);	
	
	@Autowired
	private CustomizeMapper mapper;
	
	public List<Customize> getCustomizeListPage(String destination,String keyword,PageInfo page) {
		 
		logger.info("query");
		return mapper.getCustomizeListPage(destination,keyword,page);
	}
	
	public List<Customize> getFrontCustomizeListPage(String destination,PageInfo page) {
		 
		logger.info("query");
		return mapper.getFrontCustomizeListPage(destination,page);
	}
	 
	@Transactional
	public Customize insert(CustomizeRequestBean requestBean) throws KunSoftwareException {		 
		
		Customize record = new Customize();
		BeanUtils.copyProperties(requestBean, record);
		if(requestBean.getImageFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getImageFile());
			record.setImagePath(imagePath);
		}
		mapper.insert(record);
		return record;
	}
	
	public Customize selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(CustomizeRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		Customize record = mapper.selectByPrimaryKey(id); 
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
