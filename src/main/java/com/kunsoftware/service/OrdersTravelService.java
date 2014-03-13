package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.OrdersTravelRequestBean;
import com.kunsoftware.entity.OrdersTravelList;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.OrdersTravelListMapper;
import com.kunsoftware.util.FileUtil;

@Service
public class OrdersTravelService {

private static Logger logger = LoggerFactory.getLogger(OrdersTravelService.class);	
	
	@Autowired
	private OrdersTravelListMapper mapper;
	
	public List<OrdersTravelList> getOrdersTravelListAll(Integer ordersId) {
		 
		logger.info("query");
		return mapper.getOrdersTravelListAll(ordersId);
	}
	 
	@Transactional
	public OrdersTravelList insert(OrdersTravelRequestBean requestBean) throws KunSoftwareException {		 
		
		OrdersTravelList record = new OrdersTravelList();
		BeanUtils.copyProperties(requestBean, record); 
		
		if(requestBean.getFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getFile());
			record.setImagePath(imagePath);
		}
		 
		mapper.insert(record);
		return record;
	}
	
	public OrdersTravelList selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(OrdersTravelRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		OrdersTravelList record = mapper.selectByPrimaryKey(id); 
		BeanUtils.copyProperties(requestBean, record);
		
		if(requestBean.getFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getFile());
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
