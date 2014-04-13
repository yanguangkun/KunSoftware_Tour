package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.CommentsRequestBean;
import com.kunsoftware.entity.Comments;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.CommentsMapper;
import com.kunsoftware.page.PageInfo;

@Service
public class CommentsService {

	private static Logger logger = LoggerFactory.getLogger(CommentsService.class);	
	
	@Autowired
	private CommentsMapper mapper;
	
	public List<Comments> getCommentsListPage(String audit,String reply,PageInfo page) {
		 
		logger.info("query");
		return mapper.getCommentsListPage(audit,reply,null,page);
	}
	
	public List<Comments> getFrontCommentsListPage(Integer productResourceId,PageInfo page) {
		 
		logger.info("query");
		return mapper.getCommentsListPage(null,null,productResourceId,page);
	}
	 
	@Transactional
	public Comments insert(CommentsRequestBean requestBean) throws KunSoftwareException {		 
		
		Comments record = new Comments();
		BeanUtils.copyProperties(requestBean, record); 
		 
		mapper.insert(record);
		return record;
	}
	
	public Comments selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	public Comments selectByProduct(Integer productResourceId ,Integer memberId) throws KunSoftwareException {
		
		return mapper.selectByProduct(productResourceId,memberId);
	}
	
	@Transactional
	public int updateByProduct(CommentsRequestBean requestBean) throws KunSoftwareException {
		
		Comments record = mapper.selectByProduct(requestBean.getProductResourceId(),requestBean.getMemberId()); 
		if(record == null) {
			record = new Comments();
		}
		BeanUtils.copyProperties(requestBean, record); 
		
		if(record.getId() == null) {
			return mapper.insert(record);
		}else {
			return mapper.updateByPrimaryKeySelective(record);
		}		
	}
	
	@Transactional
	public int updateByPrimaryKey(CommentsRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		Comments record = mapper.selectByPrimaryKey(id); 
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
