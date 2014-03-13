package com.kunsoftware.service;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.ValueSetRequestBean;
import com.kunsoftware.entity.Ground;
import com.kunsoftware.entity.ValueSet;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.AirlineMapper;
import com.kunsoftware.mapper.DestinationMapper;
import com.kunsoftware.mapper.GroundMapper;
import com.kunsoftware.mapper.HeadIconTitleMapper;
import com.kunsoftware.mapper.ValueSetMapper;
import com.kunsoftware.page.PageInfo;

@Repository 
public class ValueSetService {

	private static Logger logger = LoggerFactory.getLogger(ValueSetService.class);	
	
	@Autowired
	private ValueSetMapper valueSetMapper;
	
	@Autowired
	private DestinationMapper destinationMapper;
	
	@Autowired
	private AirlineMapper airlineMapper;
	
	@Autowired
	private GroundMapper groundMapper;
	
	@Autowired
	private HeadIconTitleMapper headIconTitleMapper;
	
	
	public List<ValueSet> getValueSetListAll(@Param("code") String code) {
		 
		logger.info("query");
		return valueSetMapper.getValueSetListAll(code);
	}
	
	public List<ValueSet> getValueSetListPage(@Param("code") String code,@Param("keyword") String keyword,@Param("page") PageInfo page) {
		 
		logger.info("query");
		return valueSetMapper.getValueSetListPage(code,keyword,page);
	}
	
	public List<ValueSet> selectValueSetList(String valueSetCode) {
		
		return valueSetMapper.selectValueSetList(valueSetCode);
	}
	
	public List<ValueSet> selectValueSetDestinationList() {
		
		return destinationMapper.selectValueSetList();
	}
	
	public List<ValueSet> selectValueSetAirlineList() {
		
		return airlineMapper.selectValueSetList();
	}
	
	public List<ValueSet> selectValueSetGround() {
		 
		return groundMapper.selectValueSetList();
	}
	
	public List<ValueSet> selectValueSetHeadIconTitle(String type) {
		 
		return headIconTitleMapper.selectValueSetList(type);
	}

	public String getGroundName(String value) {
		
		Ground entity = groundMapper.selectByPrimaryKey(NumberUtils.toInt(value));
		if(entity != null) return entity.getName();
		return "";
	}
	
	public ValueSet selectValueSet(String valueSetCode,String valueSetValue) {
		
		ValueSet record = new ValueSet();
		record.setCode(valueSetCode);
		record.setValue(valueSetValue);
		
		return valueSetMapper.selectValueSet(record);
	} 

	@Transactional
	public ValueSet insert(ValueSetRequestBean valueSetRequestBean) throws KunSoftwareException {
		 
		ValueSet record = new ValueSet();
		BeanUtils.copyProperties(valueSetRequestBean, record); 
		valueSetMapper.insert(record);
		record.setValue(record.getId().toString());
		valueSetMapper.updateByPrimaryKeySelective(record);
		return record;
	}
	
	public ValueSet selectByPrimaryKey(Integer id) {
		
		return valueSetMapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(ValueSetRequestBean valueSetRequestBean,Integer id) {
		
		ValueSet record = valueSetMapper.selectByPrimaryKey(id); 
		BeanUtils.copyProperties(valueSetRequestBean, record);
		 
		return valueSetMapper.updateByPrimaryKeySelective(record);
	}
	
	@Transactional
	public int deleteByPrimaryKey(Integer id) {
		return valueSetMapper.deleteByPrimaryKey(id);
	}
	
	@Transactional
	public void deleteByPrimaryKey(Integer[] id) throws KunSoftwareException {

		for(int i = 0;i < id.length;i++) {
			valueSetMapper.deleteByPrimaryKey(id[i]);
		} 
	}
}
