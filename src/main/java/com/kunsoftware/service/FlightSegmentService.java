package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.FlightSegmentRequestBean;
import com.kunsoftware.entity.FlightSegment;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.FlightSegmentMapper;

@Service
public class FlightSegmentService {

	private static Logger logger = LoggerFactory.getLogger(FlightSegmentService.class);	
	
	@Autowired
	private FlightSegmentMapper mapper;
	
	public List<FlightSegment> getFlightSegmentListAll(Integer flightId) {
		 
		logger.info("query");
		return mapper.getFlightSegmentListAll(flightId);
	}
	 
	@Transactional
	public FlightSegment insert(FlightSegmentRequestBean requestBean) throws KunSoftwareException {		 
		
		FlightSegment record = new FlightSegment();
		BeanUtils.copyProperties(requestBean, record);  
		mapper.insert(record);
		return record;
	}
	
	public FlightSegment selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(FlightSegmentRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		FlightSegment record = mapper.selectByPrimaryKey(id); 
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
