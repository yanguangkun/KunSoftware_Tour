package com.kunsoftware.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.OrdersCashRequestBean;
import com.kunsoftware.entity.OrdersCash;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.OrdersCashMapper;
import com.kunsoftware.util.WebUtil;

@Service
public class OrdersCashService {

	private static Logger logger = LoggerFactory.getLogger(OrdersCashService.class);	
	
	@Autowired
	private OrdersCashMapper mapper;
	
	public List<OrdersCash> getOrdersCashListAll(Integer ordersId) {
		 
		logger.info("query");
		return mapper.getOrdersCashListAll(ordersId);
	}
	 
	@Transactional
	public OrdersCash insert(OrdersCashRequestBean requestBean) throws KunSoftwareException {		 
		
		OrdersCash record = new OrdersCash();
		BeanUtils.copyProperties(requestBean, record); 
		 
		record.setDealDate(new Date());
		record.setUserId(WebUtil.getUserId());
		record.setUserName(WebUtil.getUserName());
		
		mapper.insert(record);
		return record;
	}
	
	public OrdersCash selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(OrdersCashRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		OrdersCash record = mapper.selectByPrimaryKey(id); 
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
