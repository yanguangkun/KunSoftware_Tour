package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.FlightChedulePriceRequestBean;
import com.kunsoftware.entity.FlightChedulePlan;
import com.kunsoftware.entity.FlightChedulePrice;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.FlightChedulePriceMapper;

@Service
public class FlightChedulePriceService {

	private static Logger logger = LoggerFactory.getLogger(FlightChedulePriceService.class);	
	
	@Autowired
	private FlightChedulePriceMapper mapper;
	
	public List<FlightChedulePrice> getFlightChedulePriceListAll(Integer flightId) {
		 
		logger.info("query");
		return mapper.getFlightChedulePriceListAll(flightId);
	}
	 
	@Transactional
	public FlightChedulePrice insert(FlightChedulePriceRequestBean requestBean) throws KunSoftwareException {		 
		
		FlightChedulePrice record = new FlightChedulePrice();
		BeanUtils.copyProperties(requestBean, record);  
		mapper.insert(record);
		return record;
	}
	
	public FlightChedulePrice selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(FlightChedulePriceRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		FlightChedulePrice record = mapper.selectByPrimaryKey(id); 
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
	
	public FlightChedulePrice selectByFlightCheduleId(Integer flightCheduleId,Integer productPlanTplId) {
		return mapper.selectByFlightCheduleId(flightCheduleId, productPlanTplId);
	}
}
