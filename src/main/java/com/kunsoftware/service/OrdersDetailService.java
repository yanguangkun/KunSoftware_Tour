package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.OrdersDetailRequestBean;
import com.kunsoftware.entity.OrdersDetail;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.OrdersDetailMapper;

@Service
public class OrdersDetailService {

	private static Logger logger = LoggerFactory.getLogger(OrdersDetailService.class);	
	
	@Autowired
	private OrdersDetailMapper mapper;
	
	public List<OrdersDetail> getOrdersDetailListAll(Integer ordersId) {
		 
		logger.info("query");
		return mapper.getOrdersDetailListAll(ordersId);
	}
	 
	@Transactional
	public OrdersDetail insert(OrdersDetailRequestBean requestBean) throws KunSoftwareException {		 
		
		OrdersDetail record = new OrdersDetail();
		BeanUtils.copyProperties(requestBean, record); 		  
		
		mapper.insert(record);
		return record;
	}
	
	public OrdersDetail selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(OrdersDetailRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		OrdersDetail record = mapper.selectByPrimaryKey(id); 
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
