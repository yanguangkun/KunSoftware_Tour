package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.AirlineRequestBean;
import com.kunsoftware.entity.Airline;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.AirlineMapper;
import com.kunsoftware.util.FileUtil;

@Service
public class AirlineService {

	private static Logger logger = LoggerFactory.getLogger(AirlineService.class);	
	
	@Autowired
	private AirlineMapper mapper;
	
	public List<Airline> getAirlineListAll() {
		 
		logger.info("query");
		return mapper.getAirlineListAll();
	}
	 
	@Transactional
	public Airline insert(AirlineRequestBean requestBean) throws KunSoftwareException {		 
		
		Airline record = new Airline();
		BeanUtils.copyProperties(requestBean, record); 
		
		if(requestBean.getImageFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getImageFile());
			record.setImagePath(imagePath);
		}
		
		mapper.insert(record);
		return record;
	}
	
	public Airline selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(AirlineRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		Airline record = mapper.selectByPrimaryKey(id); 
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
