package com.kunsoftware.service;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kunsoftware.entity.ValueSet;
import com.kunsoftware.mapper.GroundMapper;
import com.kunsoftware.mapper.ValueSetMapper;
import com.kunsoftware.util.WebUtil;

@Service
public class CascadeService {

	private static Logger logger = LoggerFactory.getLogger(CascadeService.class);	
	
	@Autowired
	private GroundMapper groundMapper;
	
	@Autowired
	private ValueSetMapper valueSetMapper;
	
	public String getSelectResult(String cascadeCode,String cascadeValue,String defaultValue) {
		 
		logger.info("query");
		
		List<ValueSet> list = null;
		
		if("ground".equals(cascadeCode)) {
			list = getGroundList(cascadeValue);
		} else if("city".equals(cascadeCode)) {
			list = getCityList(cascadeValue);
		}
		StringBuilder str = new StringBuilder();
		for(ValueSet valueSet:list) {
			str.append(WebUtil.option(valueSet.getName(), valueSet.getValue(), defaultValue));
		}
		
		return str.toString();
	}
	
	public List<ValueSet> getGroundList(String cascadeValue) {
		
		Integer destination= NumberUtils.toInt(cascadeValue);
		List<ValueSet> list = groundMapper.getValueSetListByDestination(destination);
		return list;
		
	}
	
	public List<ValueSet> getCityList(String cascadeValue) { 
		 
		List<ValueSet> list = valueSetMapper.selectValueSetListByParentValue(cascadeValue);
		return list;
		
	}
}
