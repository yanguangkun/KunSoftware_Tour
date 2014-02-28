package com.kunsoftware.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kunsoftware.entity.News;
import com.kunsoftware.mapper.NewsMapper;
import com.kunsoftware.page.PageInfo;

@Service
public class NewsService {

	@Autowired
	private NewsMapper mapper;
	
	public List<News> getNewsListPage(@Param("page") PageInfo page) {
		 
		return mapper.getNewsListPage(page);
	}
	
	public void inserUser(News record) {
		mapper.insert(record);
	}
	
	public void inserUser(List<News> list) {
		
		for(News record:list) {
			if(mapper.titleMd5Count(record.getTitleMd5()) == 0) {
				inserUser(record);
			} 
		}
	}
	
	public void readNews(Integer id) {
		
		News record = mapper.selectByPrimaryKey(id);
		record.setIsRead("1");
		
		mapper.updateByPrimaryKey(record);
	}
}
