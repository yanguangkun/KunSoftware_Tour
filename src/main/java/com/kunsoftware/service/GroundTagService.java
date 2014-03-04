package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.GroundTagRequestBean;
import com.kunsoftware.entity.GroundTag;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.GroundTagMapper;
import com.kunsoftware.page.PageInfo;

@Service
public class GroundTagService {

private static Logger logger = LoggerFactory.getLogger(GroundTagService.class);	
	
	@Autowired
	private GroundTagMapper mapper;
	
	public List<GroundTag> getGroundTagListPage(Integer destination,Integer groundId,PageInfo page) {
		 
		logger.info("query");
		return mapper.getGroundTagListPage(destination,groundId,page);
	}
	 
	@Transactional
	public GroundTag insert(GroundTagRequestBean requestBean) throws KunSoftwareException {		 
		
		GroundTag record = new GroundTag();
		BeanUtils.copyProperties(requestBean, record);
		 
		mapper.insert(record);
		return record;
	}
	
	public GroundTag selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(GroundTagRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		GroundTag record = mapper.selectByPrimaryKey(id); 
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
