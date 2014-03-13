package com.kunsoftware.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.OrdersStatusRequestBean;
import com.kunsoftware.entity.OrdersStatus;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.OrdersStatusMapper;
import com.kunsoftware.util.WebUtil;

@Service
public class OrdersStatusService {

	private static Logger logger = LoggerFactory.getLogger(OrdersStatusService.class);	
	
	@Autowired
	private OrdersStatusMapper mapper;
	
	public List<OrdersStatus> getOrdersStatusListAll(Integer ordersId) {
		 
		logger.info("query");
		return mapper.getOrdersStatusListAll(ordersId);
	}
	 
	@Transactional
	public OrdersStatus insert(OrdersStatusRequestBean requestBean) throws KunSoftwareException {		 
		
		OrdersStatus record = new OrdersStatus();
		BeanUtils.copyProperties(requestBean, record); 
		 
		record.setDealDate(new Date());
		record.setUserId(WebUtil.getUserId());
		record.setUserName(WebUtil.getUserName());
		
		mapper.insert(record);
		return record;
	}
	
	public OrdersStatus selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(OrdersStatusRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		OrdersStatus record = mapper.selectByPrimaryKey(id); 
		BeanUtils.copyProperties(requestBean, record); 
		
		record.setDealDate(new Date());
		record.setUserId(WebUtil.getUserId());
		record.setUserName(WebUtil.getUserName());
		
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
