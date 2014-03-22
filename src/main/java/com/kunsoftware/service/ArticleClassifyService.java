package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.ArticleClassifyRequestBean;
import com.kunsoftware.entity.ArticleClassify;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.ArticleClassifyMapper;

@Service
public class ArticleClassifyService {

	private static Logger logger = LoggerFactory.getLogger(ArticleClassifyService.class);	
	
	@Autowired
	private ArticleClassifyMapper mapper;
	
	public List<ArticleClassify> getArticleClassifyListAll() {
		 
		logger.info("query");
		return mapper.getArticleClassifyListAll();
	}
	 
	@Transactional
	public ArticleClassify insert(ArticleClassifyRequestBean requestBean) throws KunSoftwareException {		 
		
		ArticleClassify record = new ArticleClassify();
		BeanUtils.copyProperties(requestBean, record); 		 
		
		mapper.insert(record);
		return record;
	}
	
	public ArticleClassify selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(ArticleClassifyRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		ArticleClassify record = mapper.selectByPrimaryKey(id); 
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
