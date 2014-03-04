package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.ProductResourceRequestBean;
import com.kunsoftware.entity.ProductResource;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.ProductResourceMapper;
import com.kunsoftware.page.PageInfo;

@Service
public class ProductResourceService {

	private static Logger logger = LoggerFactory.getLogger(ProductResourceService.class);	
	
	@Autowired
	private ProductResourceMapper mapper;
	
	public List<ProductResource> getProductResourceListPage(ProductResourceRequestBean bean,PageInfo page) {
		 
		logger.info("query");
		return mapper.getProductResourceListPage(bean,page);
	}
	 
	@Transactional
	public ProductResource insert(ProductResourceRequestBean requestBean) throws KunSoftwareException {		 
		
		ProductResource record = new ProductResource();
		BeanUtils.copyProperties(requestBean, record);
		
		mapper.insert(record);
		return record;
	}
	
	public ProductResource selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(ProductResourceRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		ProductResource record = mapper.selectByPrimaryKey(id); 
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
