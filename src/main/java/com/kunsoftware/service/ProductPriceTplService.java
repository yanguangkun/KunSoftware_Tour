package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.ProductPriceTplRequestBean;
import com.kunsoftware.entity.ProductPriceTpl;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.ProductPriceTplMapper;

@Service
public class ProductPriceTplService {

	private static Logger logger = LoggerFactory.getLogger(ProductPriceTplService.class);	
	
	@Autowired
	private ProductPriceTplMapper mapper;
	
	public List<ProductPriceTpl> getProductPriceTplListAll(Integer flightId) {
		 
		logger.info("query");
		return mapper.getProductPriceTplListAll(flightId);
	}
	 
	@Transactional
	public ProductPriceTpl insert(ProductPriceTplRequestBean requestBean) throws KunSoftwareException {		 
		
		ProductPriceTpl record = new ProductPriceTpl();
		BeanUtils.copyProperties(requestBean, record);  
		mapper.insert(record);
		return record;
	}
	
	public ProductPriceTpl selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(ProductPriceTplRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		ProductPriceTpl record = mapper.selectByPrimaryKey(id); 
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
