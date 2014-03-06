package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.entity.FlightChedule;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.FlightCheduleMapper;
import com.kunsoftware.page.PageInfo;

@Service
public class FlightCheduleService {

	private static Logger logger = LoggerFactory.getLogger(FlightCheduleService.class);	
	
	@Autowired
	private FlightCheduleMapper mapper;
	
	public List<FlightChedule> getFlightCheduleListPage(String valid,String audit,String status,String startDate,PageInfo page) {
		 
		logger.info("query");
		return mapper.getFlightCheduleListPage(valid, audit, status, startDate,page);
	}	 
	
	public FlightChedule selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
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
