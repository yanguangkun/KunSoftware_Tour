package com.kunsoftware.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.QuestionsRequestBean;
import com.kunsoftware.entity.Questions;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.QuestionsMapper;
import com.kunsoftware.page.PageInfo;

@Service
public class QuestionsService {

	private static Logger logger = LoggerFactory.getLogger(QuestionsService.class);	
	
	@Autowired
	private QuestionsMapper mapper;
	
	public List<Questions> getQuestionsListPage(String audit,String reply,PageInfo page) {
		 
		logger.info("query");
		return mapper.getQuestionsListPage(audit,reply,null,null,page);
	}
	
	public List<Questions> getFrontQuestionsListPage(Integer destination,String banner,PageInfo page) {
		 
		logger.info("query");
		return mapper.getQuestionsListPage("1",null,destination,banner,page);
	}
	 
	@Transactional
	public Questions insert(QuestionsRequestBean requestBean) throws KunSoftwareException {		 
		
		Questions record = new Questions();
		BeanUtils.copyProperties(requestBean, record); 
		record.setCreateTime(new Date());
		record.setAudit("1");
		 
		mapper.insert(record);
		return record;
	}
	
	public Questions selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(QuestionsRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		Questions record = mapper.selectByPrimaryKey(id); 
		BeanUtils.copyProperties(requestBean, record);		 
		record.setReplyTime(new Date());
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
