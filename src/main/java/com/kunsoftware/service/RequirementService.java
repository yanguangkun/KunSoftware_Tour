package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.RequirementRequestBean;
import com.kunsoftware.entity.Requirement;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.RequirementMapper;
import com.kunsoftware.page.PageInfo;

@Service
public class RequirementService {

private static Logger logger = LoggerFactory.getLogger(RequirementService.class);	
	
	@Autowired
	private RequirementMapper mapper;
	
	public List<Requirement> getRequirementListPage(String status,String arriveRequirement,String linkman,String mobile,PageInfo page) {
		 
		logger.info("query");
		return mapper.getRequirementListPage(status,arriveRequirement,linkman,mobile,page);
	}
	 
	@Transactional
	public Requirement insert(RequirementRequestBean requestBean) throws KunSoftwareException {		 
		
		Requirement record = new Requirement();
		BeanUtils.copyProperties(requestBean, record); 
		 
		mapper.insert(record);
		return record;
	}
	
	public Requirement selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(RequirementRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		Requirement record = mapper.selectByPrimaryKey(id); 
		BeanUtils.copyProperties(requestBean, record);
		 
		return mapper.updateByPrimaryKeySelective(record);
	}
	
	@Transactional
	public void updateStatusByIds(Integer[] id,String status) throws KunSoftwareException {
		
		for(int i = 0;i < id.length;i++) {
			Requirement record = mapper.selectByPrimaryKey(id[i]); 
			record.setStatus(status);
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
