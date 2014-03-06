package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.ProductPlanTplRequestBean;
import com.kunsoftware.entity.ProductPlanTpl;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.ProductPlanTplMapper;

@Service
public class ProductPlanTplService {

	private static Logger logger = LoggerFactory.getLogger(ProductPlanTplService.class);	
	
	@Autowired
	private ProductPlanTplMapper mapper;
	
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
}
