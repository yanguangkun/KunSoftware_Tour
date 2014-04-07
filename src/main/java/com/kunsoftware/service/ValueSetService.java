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
import org.springframework.web.multipart.MultipartFile;

import com.kunsoftware.bean.ValueSetRequestBean;
import com.kunsoftware.entity.Ground;
import com.kunsoftware.entity.ValueSet;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.AirlineMapper;
import com.kunsoftware.mapper.ArticleClassifyMapper;
import com.kunsoftware.mapper.DestinationMapper;
import com.kunsoftware.mapper.GroundMapper;
import com.kunsoftware.mapper.HeadIconTitleMapper;
import com.kunsoftware.mapper.ValueSetMapper;
import com.kunsoftware.page.PageInfo;
import com.kunsoftware.util.FileUtil;

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
	
	@Autowired
	private ArticleClassifyMapper articleClassifyMapper;
	
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
	
	public List<ValueSet> selectValueSetArticleClassify() {
		 
		return articleClassifyMapper.selectValueSetList();
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
	
	public String selectValueSetByCode(String code) {
		
		ValueSet record = valueSetMapper.selectValueSetByCode(code);
		if(record != null && record.getValue() != null) {
			return record.getValue();
		}
		return "";
	}
	
	@Transactional
	public void saveGcolor(String color1,String color2,String color3,String color4) {
		
		ValueSet record = valueSetMapper.selectValueSetByCode("index_color1");
		if(record == null) record = new ValueSet();
		record.setValue(color1);
		record.setCode("index_color1");
		saveUpdate(record);
		
		record = valueSetMapper.selectValueSetByCode("index_color2");
		if(record == null) record = new ValueSet();
		record.setValue(color2);
		record.setCode("index_color2");
		saveUpdate(record);
		
		record = valueSetMapper.selectValueSetByCode("index_color3");
		if(record == null) record = new ValueSet();
		record.setValue(color3);
		record.setCode("index_color3");
		saveUpdate(record);
		
		record = valueSetMapper.selectValueSetByCode("index_color4");
		if(record == null) record = new ValueSet();
		record.setValue(color4);
		record.setCode("index_color4");
		saveUpdate(record);
		
	}
	
	public void saveCustomize(String num,MultipartFile image) throws KunSoftwareException{
		
		ValueSet record = valueSetMapper.selectValueSetByCode("customize_num");
		if(record == null) record = new ValueSet();
		record.setValue(num);
		record.setCode("customize_num");
		saveUpdate(record);		
		
		if(image != null) {
			record = valueSetMapper.selectValueSetByCode("customize_image");
			if(record == null) record = new ValueSet();			
			String imagePath = FileUtil.uploadFile(image);
			
			record.setValue(imagePath);
			record.setCode("customize_image");
			saveUpdate(record);
		}
	}
	
	public void saveHidePrice(String hidePrice) throws KunSoftwareException{
		
		ValueSet record = valueSetMapper.selectValueSetByCode("hide_price");
		if(record == null) record = new ValueSet();
		record.setValue(hidePrice);
		record.setCode("hide_price");
		saveUpdate(record);		
	}
	
	public void saveGiftad(String num,MultipartFile image) throws KunSoftwareException{
		
		ValueSet record = valueSetMapper.selectValueSetByCode("giftad_link");
		if(record == null) record = new ValueSet();
		record.setValue(num);
		record.setCode("giftad_link");
		saveUpdate(record);		
		
		if(image != null) {
			record = valueSetMapper.selectValueSetByCode("giftad_image");
			if(record == null) record = new ValueSet();			
			String imagePath = FileUtil.uploadFile(image);
			
			record.setValue(imagePath);
			record.setCode("giftad_image");
			saveUpdate(record);
		}
	}
	
	public void saveUpdate(ValueSet record) {
		
		if(record.getId() == null) {
			valueSetMapper.insert(record);
		} else {
			valueSetMapper.updateByPrimaryKey(record);
		}
		
	}
}
