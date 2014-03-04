package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.ProductIntroduceRequestBean;
import com.kunsoftware.entity.ProductIntroduce;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.ProductIntroduceMapper;

@Service
public class ProductIntroduceService {

private static Logger logger = LoggerFactory.getLogger(ProductIntroduceService.class);	
	
	@Autowired
	private ProductIntroduceMapper mapper;
	
	public List<ProductIntroduce> getProductIntroduceListAll(Integer productId) {
		 
		logger.info("query");
		return mapper.getProductIntroduceListAll(productId);
	}
	 
	@Transactional
	public ProductIntroduce insert(ProductIntroduceRequestBean requestBean) throws KunSoftwareException {		 
		
		ProductIntroduce record = new ProductIntroduce();
		BeanUtils.copyProperties(requestBean, record);  
		mapper.insert(record);
		return record;
	}
	
	public ProductIntroduce selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(ProductIntroduceRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		ProductIntroduce record = mapper.selectByPrimaryKey(id); 
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
