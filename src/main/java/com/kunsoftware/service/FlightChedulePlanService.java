package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.FlightChedulePlanRequestBean;
import com.kunsoftware.entity.FlightChedulePlan;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.FlightChedulePlanMapper;

@Service
public class FlightChedulePlanService {

	private static Logger logger = LoggerFactory.getLogger(FlightChedulePlanService.class);	
	
	@Autowired
	private FlightChedulePlanMapper mapper;
	
	public List<FlightChedulePlan> getFlightChedulePlanListAll(Integer flightCheduleId) {
		 
		logger.info("query");
		return mapper.getFlightChedulePlanListAll(flightCheduleId);
	}
	 
	@Transactional
	public FlightChedulePlan insert(FlightChedulePlanRequestBean requestBean) throws KunSoftwareException {		 
		
		FlightChedulePlan record = new FlightChedulePlan();
		BeanUtils.copyProperties(requestBean, record);  
		mapper.insert(record);
		return record;
	}
	
	public FlightChedulePlan selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(FlightChedulePlanRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		FlightChedulePlan record = mapper.selectByPrimaryKey(id); 
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
	
	public FlightChedulePlan selectByFlightCheduleId(Integer flightCheduleId,Integer productPlanTplId) {
		return mapper.selectByFlightCheduleId(flightCheduleId, productPlanTplId);
	}
	
}
