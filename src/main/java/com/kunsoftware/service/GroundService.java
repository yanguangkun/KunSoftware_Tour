package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.GroundRequestBean;
import com.kunsoftware.entity.Ground;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.GroundMapper;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.util.FileUtil;

@Service
public class GroundService {

	private static Logger logger = LoggerFactory.getLogger(GroundService.class);	
	
	@Autowired
	private GroundMapper mapper;
	
	public List<Ground> getGroundListPage(Integer destination,PageInfo page) {
		 
		logger.info("query");
		return mapper.getGroundListPage(destination,page);
	}
	 
	@Transactional
	public Ground insert(GroundRequestBean requestBean) throws KunSoftwareException {		 
		
		Ground record = new Ground();
		BeanUtils.copyProperties(requestBean, record);
		
		if(requestBean.getImageFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getImageFile());
			record.setImagePath(imagePath);
		}
		
		if(requestBean.getQualificationImageFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getQualificationImageFile());
			record.setQualificationImagePath(imagePath);
		}
		
		mapper.insert(record);
		return record;
	}
	
	public Ground selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(GroundRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		Ground record = mapper.selectByPrimaryKey(id); 
		BeanUtils.copyProperties(requestBean, record);	
		if(requestBean.getImageFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getImageFile());
			record.setImagePath(imagePath);
		}
		
		if(requestBean.getQualificationImageFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getQualificationImageFile());
			record.setQualificationImagePath(imagePath);
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
