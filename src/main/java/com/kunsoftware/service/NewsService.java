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
	private NewsMapper newsMapper;
	
	public List<News> getNewsListPage(@Param("page") PageInfo page) {
		 
		return newsMapper.getNewsListPage(page);
	}
	
	public void inserUser(News record) {
		newsMapper.insert(record);
	}
	
	public void inserUser(List<News> list) {
		
		for(News record:list) {
			if(newsMapper.titleMd5Count(record.getTitleMd5()) == 0) {
				inserUser(record);
			} 
		}
	}
	
	public void readNews(Integer id) {
		
		News record = newsMapper.selectByPrimaryKey(id);
		record.setIsRead("1");
		
		newsMapper.updateByPrimaryKey(record);
	}
}
