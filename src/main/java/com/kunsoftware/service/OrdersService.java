package com.kunsoftware.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.BuyBean;
import com.kunsoftware.bean.OrderViewBean;
import com.kunsoftware.bean.OrdersRequestBean;
import com.kunsoftware.entity.FlightChedule;
import com.kunsoftware.entity.Orders;
import com.kunsoftware.entity.OrdersDetail;
import com.kunsoftware.entity.OrdersTravelList;
import com.kunsoftware.entity.ProductResource;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.OrdersAttachmentMapper;
import com.kunsoftware.mapper.OrdersCashMapper;
import com.kunsoftware.mapper.OrdersDetailMapper;
import com.kunsoftware.mapper.OrdersMapper;
import com.kunsoftware.mapper.OrdersStatusMapper;
import com.kunsoftware.mapper.OrdersTravelListMapper;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.util.WebUtil;

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
		orderViewBean.setOrdersStatus(ordersStatusMapper.getOrdersStatusListAll(ordersId,null));
		orderViewBean.setOrdersCash(ordersCashMapper.getOrdersCashListAll(ordersId));
		orderViewBean.setOrdersAttachment(ordersAttachmentMapper.getOrdersAttachmentListAll(ordersId,null));
		orderViewBean.setOrdersTravel(ordersTravelMapper.getOrdersTravelListAll(ordersId));
		
		return orderViewBean;
	}
	
	public OrderViewBean getFrontOrdersView(Integer ordersId) {
		  
		OrderViewBean orderViewBean = new OrderViewBean();
	
		orderViewBean.setOrders(mapper.selectByPrimaryKey(ordersId));
		orderViewBean.setOrdersDetail(ordersDetailMapper.getOrdersDetailListAll(ordersId));
		orderViewBean.setOrdersStatus(ordersStatusMapper.getOrdersStatusListAll(ordersId,"1"));
		orderViewBean.setOrdersCash(ordersCashMapper.getOrdersCashListAll(ordersId));
		orderViewBean.setOrdersAttachment(ordersAttachmentMapper.getOrdersAttachmentListAll(ordersId,"1"));
		orderViewBean.setOrdersTravel(ordersTravelMapper.getOrdersTravelListAll(ordersId));
		
		return orderViewBean;
	}
	
	@Transactional
	public Orders insertMemberOrder(BuyBean buyBean,
			ProductResource productResource,
			FlightChedule flightChedule,
			List retList) throws KunSoftwareException {		 
		
		Orders record = new Orders();
		record.setProductId(productResource.getId().toString());
		record.setProductName(productResource.getName());
		record.setFlightCheduleId(flightChedule.getId());
		record.setFlightChedulePlanPriceId(buyBean.getFlightChedulePlanPriceId());
		record.setStatus("1");
		record.setSource("2");
		record.setType(productResource.getProductType());
		record.setQuantity(buyBean.getQuantity());
		record.setAmount(new BigDecimal(buyBean.getAllTotal()));
		record.setEarnest(new BigDecimal(productResource.getStandardPrice()));
		record.setBusinessDate(new Date());
		record.setUserId(WebUtil.getMemberId());
		record.setUserName(WebUtil.getMemberUserName());
		record.setLinkman(buyBean.getLinkman());
		record.setLinkmanMobile(buyBean.getLinkmanMobile());
		record.setOrderDate(new Date());
		record.setGuestRemark(buyBean.getRemark());
		
		mapper.insert(record);
		
		record.setCode(StringUtils.leftPad(record.getId().toString(), 10, "0"));		
		mapper.updateByPrimaryKeySelective(record);
		
		OrdersDetail ordersDetail = null;
		for(int i = 0;i < retList.size();i++) {
			ordersDetail = (OrdersDetail)retList.get(i);
			ordersDetail.setOrdersId(record.getId());
			ordersDetailMapper.insert(ordersDetail);
		}
		
		OrdersTravelList ordersTravelList = null;
		 
		if(buyBean.getName()!= null) {
			for(int i = 0; i < buyBean.getName().length;i++) {
				ordersTravelList = new OrdersTravelList();
				ordersTravelList.setOrdersId(record.getId());
				ordersTravelList.setName(buyBean.getName()[i]);
				ordersTravelList.setSex(buyBean.getSex()[i]);
				ordersTravelList.setBirthdate(buyBean.getBirthdate()[i]);
				ordersTravelList.setCertificateNum(buyBean.getCertificateNum()[i]);
				ordersTravelList.setCertificateType("2");
				ordersTravelList.setCertificateEndDate(buyBean.getCertificateEndDate()[i]);
				
				ordersTravelMapper.insert(ordersTravelList);			 
			}
		}
		return record;
	}
}
