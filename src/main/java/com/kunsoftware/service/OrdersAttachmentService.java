package com.kunsoftware.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.OrdersAttachmentRequestBean;
import com.kunsoftware.entity.OrdersAttachment;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.OrdersAttachmentMapper;
import com.kunsoftware.util.FileUtil;
import com.kunsoftware.util.WebUtil;

@Service
public class OrdersAttachmentService {

	private static Logger logger = LoggerFactory.getLogger(OrdersAttachmentService.class);	
	
	@Autowired
	private OrdersAttachmentMapper mapper;
	
	public List<OrdersAttachment> getOrdersAttachmentListAll(Integer ordersId) {
		 
		logger.info("query");
		return mapper.getOrdersAttachmentListAll(ordersId,null);
	}
	 
	@Transactional
	public OrdersAttachment insert(OrdersAttachmentRequestBean requestBean) throws KunSoftwareException {		 
		
		OrdersAttachment record = new OrdersAttachment();
		BeanUtils.copyProperties(requestBean, record); 
		
		if(requestBean.getFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getFile());
			record.setFilePath(imagePath);
		}
		
		record.setDealDate(new Date());
		record.setUserId(WebUtil.getUserId());
		record.setUserName(WebUtil.getUserName());
		
		mapper.insert(record);
		return record;
	}
	
	public OrdersAttachment selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(OrdersAttachmentRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		OrdersAttachment record = mapper.selectByPrimaryKey(id); 
		BeanUtils.copyProperties(requestBean, record);
		
		if(requestBean.getFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getFile());
			record.setFilePath(imagePath);
		}
		
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
