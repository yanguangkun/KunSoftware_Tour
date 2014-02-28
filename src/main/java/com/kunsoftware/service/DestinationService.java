package com.kunsoftware.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.DestinationRequestBean;
import com.kunsoftware.entity.Destination;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.DestinationMapper;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.util.FileUtil;

@Repository 
public class DestinationService {

	private static Logger logger = LoggerFactory.getLogger(DestinationService.class);	
	
	@Autowired
	private DestinationMapper mapper;
	
	public List<Destination> getDestinationListPage(@Param("keyword") String keyword,@Param("page") PageInfo page) {
		 
		logger.info("query");
		return mapper.getDestinationListPage(keyword,page);
	}
	 
	@Transactional
	public Destination insert(DestinationRequestBean requestBean) throws KunSoftwareException {		 
		
		Destination record = new Destination();
		BeanUtils.copyProperties(requestBean, record); 
		
		if(requestBean.getImageFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getImageFile());
			record.setImagePath(imagePath);
		}
		
		mapper.insert(record);
		return record;
	}
	
	public Destination selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(DestinationRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		Destination record = mapper.selectByPrimaryKey(id); 
		BeanUtils.copyProperties(requestBean, record);
		
		if(requestBean.getImageFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getImageFile());
			record.setImagePath(imagePath);
		}
		
		return mapper.updateByPrimaryKeySelective(record);
	}
	
	@Transactional
	public void updateEnableByIds(Integer[] id,String enable) throws KunSoftwareException {
		
		for(int i = 0;i < id.length;i++) {
			Destination record = mapper.selectByPrimaryKey(id[i]); 
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
