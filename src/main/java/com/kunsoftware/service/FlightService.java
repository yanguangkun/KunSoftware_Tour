package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.FlightRequestBean;
import com.kunsoftware.entity.Flight;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.FlightMapper;
import com.kunsoftware.page.PageInfo;

@Service
public class FlightService {

	private static Logger logger = LoggerFactory.getLogger(FlightService.class);	
	
	@Autowired
	private FlightMapper mapper;
	
	public List<Flight> getFlightListPage(String enable,PageInfo page) {
		 
		logger.info("query");
		return mapper.getFlightListPage(enable,page);
	}
	 
	@Transactional
	public Flight insert(FlightRequestBean requestBean) throws KunSoftwareException {		 
		
		Flight record = new Flight();
		BeanUtils.copyProperties(requestBean, record);
		
		mapper.insert(record);
		return record;
	}
	
	public Flight selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(FlightRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		Flight record = mapper.selectByPrimaryKey(id); 
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
