package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.HeadIconTitleRequestBean;
import com.kunsoftware.entity.HeadIconTitle;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.HeadIconTitleMapper;
import com.kunsoftware.util.FileUtil;

@Service
public class HeadIconTitleService {

private static Logger logger = LoggerFactory.getLogger(HeadIconTitleService.class);	
	
	@Autowired
	private HeadIconTitleMapper mapper;
	
	public List<HeadIconTitle> getHeadIconTitleListAll(String type) {
		 
		logger.info("query");
		return mapper.getHeadIconTitleListAll(type);
	}
	 
	@Transactional
	public HeadIconTitle insert(HeadIconTitleRequestBean requestBean) throws KunSoftwareException {		 
		
		HeadIconTitle record = new HeadIconTitle();
		BeanUtils.copyProperties(requestBean, record); 
		
		if(requestBean.getImageFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getImageFile());
			record.setName(imagePath);
		}
		
		mapper.insert(record);
		return record;
	}
	
	public HeadIconTitle selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(HeadIconTitleRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		HeadIconTitle record = mapper.selectByPrimaryKey(id); 
		BeanUtils.copyProperties(requestBean, record);
		
		if(requestBean.getImageFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getImageFile());
			record.setName(imagePath);
		}
		
		return mapper.updateByPrimaryKeySelective(record);
	}
	
	@Transactional
	public void updateEnableByIds(Integer[] id,String enable) throws KunSoftwareException {
		
		for(int i = 0;i < id.length;i++) {
			HeadIconTitle record = mapper.selectByPrimaryKey(id[i]); 
			record.setEnable(enable);
			mapper.updateByPrimaryKeySelective(record);
		}  
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
