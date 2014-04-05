package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.OrderViewBean;
import com.kunsoftware.bean.OrdersRequestBean;
import com.kunsoftware.entity.Orders;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.OrdersAttachmentMapper;
import com.kunsoftware.mapper.OrdersCashMapper;
import com.kunsoftware.mapper.OrdersDetailMapper;
import com.kunsoftware.mapper.OrdersMapper;
import com.kunsoftware.mapper.OrdersStatusMapper;
import com.kunsoftware.mapper.OrdersTravelListMapper;
import com.kunsoftware.page.PageInfo;

@Service
public class OrdersService {

	private static Logger logger = LoggerFactory.getLogger(OrdersService.class);	
	
	@Autowired
	private OrdersMapper mapper;
	
	@Autowired
	private OrdersDetailMapper ordersDetailMapper;
	
	@Autowired
	private OrdersStatusMapper ordersStatusMapper; 
	
	@Autowired
	private OrdersCashMapper ordersCashMapper;
	
	@Autowired
	private OrdersAttachmentMapper ordersAttachmentMapper;
	
	@Autowired
	private OrdersTravelListMapper ordersTravelMapper;
	
	public List<Orders> getOrdersListPage(OrdersRequestBean bean,PageInfo page) {
		 
		logger.info("query");
		return mapper.getOrdersListPage(bean,page);
	}
	 
	@Transactional
	public Orders insert(OrdersRequestBean requestBean) throws KunSoftwareException {		 
		
		Orders record = new Orders();
		BeanUtils.copyProperties(requestBean, record); 
		 
		mapper.insert(record);
		return record;
	}
	
	public Orders selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(OrdersRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		Orders record = mapper.selectByPrimaryKey(id); 
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
	
	public OrderViewBean getOrdersView(Integer ordersId) {
		  
		OrderViewBean orderViewBean = new OrderViewBean();
	
		orderViewBean.setOrders(mapper.selectByPrimaryKey(ordersId));
		orderViewBean.setOrdersDetail(ordersDetailMapper.getOrdersDetailListAll(ordersId));
		orderViewBean.setOrdersStatus(ordersStatusMapper.getOrdersStatusListAll(ordersId));
		orderViewBean.setOrdersCash(ordersCashMapper.getOrdersCashListAll(ordersId));
		orderViewBean.setOrdersAttachment(ordersAttachmentMapper.getOrdersAttachmentListAll(ordersId));
		orderViewBean.setOrdersTravel(ordersTravelMapper.getOrdersTravelListAll(ordersId));
		
		return orderViewBean;
	}
}