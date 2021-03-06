package com.kunsoftware.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kunsoftware.entity.ValueSet;
import com.kunsoftware.mapper.FlightMapper;
import com.kunsoftware.mapper.GroundMapper;
import com.kunsoftware.mapper.GroundTagMapper;
import com.kunsoftware.mapper.HeadIconTitleMapper;
import com.kunsoftware.mapper.ProductMapper;
import com.kunsoftware.mapper.ValueSetMapper;
import com.kunsoftware.util.WebUtil;

@Service
public class CascadeService {

	private static Logger logger = LoggerFactory.getLogger(CascadeService.class);	
	
	@Autowired
	private GroundMapper groundMapper;
	
	@Autowired
	private ValueSetMapper valueSetMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private FlightMapper flightMapper;
	
	@Autowired
	private GroundTagMapper groundTagMapper;	
	
	@Autowired
	private HeadIconTitleMapper headIconTitleMapper;
	
	public String getSelectResult(String cascadeCode,String cascadeValue,String defaultValue) {
		 
		logger.info("query");
		
		if("tag".equals(cascadeCode)) return tags(cascadeValue,defaultValue);
		if("head_icon".equals(cascadeCode)) return headIcon(cascadeValue,defaultValue);
		
		List<ValueSet> list = null;
		
		if("ground".equals(cascadeCode)) {
			list = getGroundList(cascadeValue);
		} else if("city".equals(cascadeCode)) {
			list = getCityList(cascadeValue);
		} else if("product".equals(cascadeCode)) {
			list = getProductList(cascadeValue);
		} else if("flight".equals(cascadeCode)) {
			list = getFlightList(cascadeValue);
		} else if("head_title".equals(cascadeCode)) {
			try {
			list = getHeadTitleIconList("2",cascadeValue);
			} catch(Exception e) {
				e.printStackTrace();
			}
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
	
	public List<ValueSet> getProductList(String cascadeValue) { 
		 
		List<ValueSet> list = productMapper.getValueSetListByType(cascadeValue);
		return list;
		
	}
	
	public List<ValueSet> getFlightList(String cascadeValue) { 
		 
		Integer destination= NumberUtils.toInt(cascadeValue);
		List<ValueSet> list = flightMapper.getValueSetListByDestination(destination);
		return list;
		
	}
	
	public List<ValueSet> getHeadTitleIconList(String type,String cascadeValue) { 
		 
		List<ValueSet> list = headIconTitleMapper.getValueSetListBySex(type, cascadeValue);
		return list;
		
	}
	
	public String tags(String cascadeValue,String defaultValue) {
		
		String[] s = StringUtils.split(cascadeValue,","); 
		Integer destination= NumberUtils.toInt(s[0]);
		Integer groundId= NumberUtils.toInt(s[1]);
		List<ValueSet> list = groundTagMapper.getValueSetListByGround(destination, groundId);
		
		List<String> selectedList = new ArrayList<String>();
		if(StringUtils.isNotBlank(defaultValue))
			selectedList.addAll(Arrays.asList(StringUtils.split(defaultValue,",")));
		
		StringBuilder str = new StringBuilder();
		ValueSet valueSet = null;
		String name = null;
		String value = null;
		String[] values = null;
		for(int i = 0;i < list.size();i++) {
			valueSet = list.get(i);
			name = valueSet.getName();
			value = valueSet.getValue();
			if(value == null) continue;
			values = StringUtils.split(value,",");
			str.append("<li><span>"+valueSet.getName()+"</span>");
			for(int j = 0;j < values.length;j++) {
				str.append("<label class=\"radio-inline\">");
				str.append(WebUtil.radioTag(name + i, name + i, values[j], values[j], selectedList.contains(values[j])?values[j]:""));
				str.append("</label>");
			}
			str.append("</li>");			
		}
		
		return str.toString();
	}
	
	public String headIcon(String cascadeValue,String defaultValue) {
		
		List<ValueSet> list = getHeadTitleIconList("1",cascadeValue);
		StringBuilder str = new StringBuilder();
		StringBuilder tr1 = new StringBuilder();
		StringBuilder tr2 = new StringBuilder();
		ValueSet valueSet = null;
		String name = null;
		String value = null;
		str.append("<table border=\"0\" cellspacing=\"3\" cellpadding=\"3\">");
		tr1.append("<tr>");
		tr2.append("<tr>");
		for(int i = 0;i < list.size();i++) {
			valueSet = list.get(i);
			name = valueSet.getName();
			value = valueSet.getValue();
			if(value == null) continue;
			tr1.append("<td> <img src=\""+WebUtil.getContextPath()+"/images/uploadDir"+name+"\" width=\"180\" height=\"100\"></td>");
			tr2.append("<td align=\"center\">"+WebUtil.radio("headIconId","headIconId","", value, defaultValue)+"</td>");
		}
		tr1.append("</tr>");
		tr2.append("</tr>");
		str.append(tr1);
		str.append(tr2);
		str.append("</table>");
		
		return str.toString();
	}
}
