package com.kunsoftware.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.ProductRequestBean;
import com.kunsoftware.bean.ProductResourceRequestBean;
import com.kunsoftware.entity.Product;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.DestinationMapper;
import com.kunsoftware.mapper.ProductMapper;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.util.FileUtil;

@Service
public class ProductService {

	private static Logger logger = LoggerFactory.getLogger(ProductService.class);	
	
	@Autowired
	private ProductMapper mapper;
	
	@Autowired
	private DestinationMapper destinationMapper;
	
	public List<Product> getProductListPage(@Param("arriveProduct") String arriveProduct,
   		 @Param("type") String type,
   		 @Param("page") PageInfo page) {
		 
		logger.info("query");
		return mapper.getProductListPage(arriveProduct,type,page);
	}
	
	public List getProductResourceListPage(ProductResourceRequestBean requestBean,PageInfo page) {
		
		logger.info("query");
		return mapper.getProductResourceListPage(requestBean,page);
	}
	 
	@Transactional
	public Product insert(ProductRequestBean requestBean) throws KunSoftwareException {		 
		
		Product record = new Product();
		BeanUtils.copyProperties(requestBean, record);
		
		if(requestBean.getImageFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getImageFile());
			record.setImagePath(imagePath);
		}
		 
		mapper.insert(record);
		return record;
	}
	
	public Product selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(ProductRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		Product record = mapper.selectByPrimaryKey(id); 
		BeanUtils.copyProperties(requestBean, record);	
		
		if(requestBean.getImageFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getImageFile());
			record.setImagePath(imagePath);
		}
		
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
