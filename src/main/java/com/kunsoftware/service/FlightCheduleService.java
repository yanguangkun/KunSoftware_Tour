package com.kunsoftware.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.entity.FlightChedule;
import com.kunsoftware.entity.ProductResource;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.FlightCheduleMapper;
import com.kunsoftware.mapper.ProductResourceMapper;
import com.kunsoftware.page.PageInfo;

@Service
public class FlightCheduleService {

	private static Logger logger = LoggerFactory.getLogger(FlightCheduleService.class);	
	
	@Autowired
	private FlightCheduleMapper mapper;
	
	@Autowired
	private ProductResourceMapper productResourceMapper;
	
	public List<FlightChedule> getFlightCheduleListPage(Integer productResourceId,String valid,String audit,String status,String startDate,PageInfo page) {
		 
		logger.info("query");
		
		ProductResource productResource = productResourceMapper.selectByPrimaryKey(productResourceId);
		List<FlightChedule> list = mapper.getFlightCheduleListPage(productResourceId,valid, audit, status, startDate,page);
		for(FlightChedule flightChedule:list) {
			if("1".equals(productResource.getCombo())) {
				flightChedule.setCount(mapper.selectPlanCount(flightChedule.getId()));
			} else {
				flightChedule.setCount(mapper.selectPriceCount(flightChedule.getId()));
			}
		}
		
		return list;
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
	
	public List getFlightCheduleListAll(Integer productResourceId) {
		return mapper.getFlightCheduleListAll(productResourceId);
	}
	
	public FlightChedule selectByResource(Integer productResourceId,String startDate) {
		return mapper.selectByResource(productResourceId, startDate);
	}
}
