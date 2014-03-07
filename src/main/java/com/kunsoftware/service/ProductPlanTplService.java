package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.ProductPlanTplRequestBean;
import com.kunsoftware.entity.FlightChedule;
import com.kunsoftware.entity.FlightChedulePlan;
import com.kunsoftware.entity.ProductPlanTpl;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.FlightCheduleMapper;
import com.kunsoftware.mapper.FlightChedulePlanMapper;
import com.kunsoftware.mapper.ProductPlanTplMapper;

@Service
public class ProductPlanTplService {

	private static Logger logger = LoggerFactory.getLogger(ProductPlanTplService.class);	
	
	@Autowired
	private ProductPlanTplMapper mapper;
	
	@Autowired
	private FlightCheduleMapper flightCheduleMapper;
	
	@Autowired
	private FlightChedulePlanMapper flightChedulePlanMapper;
	
	public List<ProductPlanTpl> getProductPlanTplListAll(Integer flightId) {
		 
		logger.info("query");
		return mapper.getProductPlanTplListAll(flightId);
	}
	 
	@Transactional
	public ProductPlanTpl insert(ProductPlanTplRequestBean requestBean) throws KunSoftwareException {		 
		
		ProductPlanTpl record = new ProductPlanTpl();
		BeanUtils.copyProperties(requestBean, record);  
		mapper.insert(record);
		return record;
	}
	
	public ProductPlanTpl selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(ProductPlanTplRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		ProductPlanTpl record = mapper.selectByPrimaryKey(id); 
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
	
	@Transactional
	public void createFlightChedulePlan(Integer id) throws KunSoftwareException {

		ProductPlanTpl productPlanTpl = mapper.selectByPrimaryKey(id); 			
		createFlightChedulePlan(productPlanTpl); 
	} 
	
	@Transactional
	public void createFlightChedulePlan(ProductPlanTpl productPlanTpl) throws KunSoftwareException {

		Integer productResourceId = productPlanTpl.getProductResourceId(); 
		Integer productPlanTplId = productPlanTpl.getId();
		Integer flightCheduleId;	
		
		FlightChedulePlan flightChedulePlan = null;
	    List<FlightChedule> list = flightCheduleMapper.selectAuditFlightChedule(productResourceId);
	    
	    Integer id = null;
	    for(FlightChedule flightChedule:list) {
	    	flightCheduleId = flightChedule.getId();
	    	flightChedulePlan = flightChedulePlanMapper.selectByFlightCheduleId(flightCheduleId, productPlanTplId);
	    	if(flightChedulePlan == null) {
	    		flightChedulePlan = new FlightChedulePlan();
	    		id = null;
	    	} else {
	    		id = flightChedulePlan.getId();
	    	}
	    	
	    	BeanUtils.copyProperties(productPlanTpl, flightChedulePlan);
	    	flightChedulePlan.setId(id);
	    	flightChedulePlan.setFlightCheduleId(flightCheduleId);
	    	flightChedulePlan.setProductPlanTplId(productPlanTplId);
	    	
	    	if(flightChedulePlan.getId() == null) {
	    		flightChedulePlanMapper.insert(flightChedulePlan);
			} else {
				flightChedulePlanMapper.updateByPrimaryKeySelective(flightChedulePlan);
			}
	    }
	}
}
