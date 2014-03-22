package com.kunsoftware.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.RequirementRemarkRequestBean;
import com.kunsoftware.entity.RequirementRemark;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.RequirementRemarkMapper;
import com.kunsoftware.util.WebUtil;

@Service
public class RequirementRemarkService {

	private static Logger logger = LoggerFactory.getLogger(RequirementRemarkService.class);	
	
	@Autowired
	private RequirementRemarkMapper mapper;
	
	public List<RequirementRemark> getRequirementRemarkListAll(Integer requirementId) {
		 
		logger.info("query");
		return mapper.getRequirementRemarkListAll(requirementId);
	}
	 
	public RequirementRemark selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public RequirementRemark insert(RequirementRemarkRequestBean requestBean) throws KunSoftwareException {		 
		
		RequirementRemark record = new RequirementRemark();
		record.setDealDate(new Date());
		record.setUserId(WebUtil.getUserId());
		record.setUserName(WebUtil.getUserName());
		BeanUtils.copyProperties(requestBean, record); 
		 
		mapper.insert(record);
		return record;
	}
	
	@Transactional
	public int updateByPrimaryKey(RequirementRemarkRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		RequirementRemark record = mapper.selectByPrimaryKey(id); 
		BeanUtils.copyProperties(requestBean, record); 
		
		record.setDealDate(new Date());
		record.setUserId(WebUtil.getUserId());
		record.setUserName(WebUtil.getUserName());
		
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
