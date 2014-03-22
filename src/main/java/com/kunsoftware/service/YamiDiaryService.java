package com.kunsoftware.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.YamiDiaryRequestBean;
import com.kunsoftware.entity.YamiDiary;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.YamiDiaryMapper;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.util.FileUtil;

@Service
public class YamiDiaryService {

	private static Logger logger = LoggerFactory.getLogger(YamiDiaryService.class);	
	
	@Autowired
	private YamiDiaryMapper mapper;
	
	public List<YamiDiary> getYamiDiaryListPage(@Param("keyword") String keyword,@Param("page") PageInfo page) {
		 
		logger.info("query");
		return mapper.getYamiDiaryListPage(keyword,page);
	}
	 
	@Transactional
	public YamiDiary insert(YamiDiaryRequestBean requestBean) throws KunSoftwareException {		 
		
		YamiDiary record = new YamiDiary();
		BeanUtils.copyProperties(requestBean, record); 
		
		if(requestBean.getImageFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getImageFile());
			record.setImagePath(imagePath);
		}
		
		if(requestBean.getIndexImageFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getIndexImageFile());
			record.setIndexImagePath(imagePath);
		}
		
		record.setCreateTime(new Date());
		mapper.insert(record);
		return record;
	}
	
	public YamiDiary selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(YamiDiaryRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		YamiDiary record = mapper.selectByPrimaryKey(id); 
		BeanUtils.copyProperties(requestBean, record);
		
		if(requestBean.getImageFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getImageFile());
			record.setImagePath(imagePath);
		}
		
		if(requestBean.getIndexImageFile() != null) {
			String imagePath = FileUtil.uploadFile(requestBean.getIndexImageFile());
			record.setIndexImagePath(imagePath);
		}
		
		record.setCreateTime(new Date());
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
