package com.kunsoftware.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.MenuRequestBean;
import com.kunsoftware.entity.SysMenu;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.SysMenuMapper;
import com.kunsoftware.page.PageInfo;

@Service
public class MenuService {

	private static Logger logger = LoggerFactory.getLogger(MenuService.class);	
	
	@Autowired
	private SysMenuMapper mapper;
	
	public List<SysMenu> getMenuListPage(MenuRequestBean menuRequestBean,@Param("page") PageInfo page) {
		 
		logger.info("query");
		return mapper.getMenuListPage(menuRequestBean,page);
	}
	
	@Transactional
	public SysMenu insert(MenuRequestBean menuRequestBean) throws KunSoftwareException {
		 
		SysMenu record = new SysMenu();
		BeanUtils.copyProperties(menuRequestBean, record);
		mapper.insert(record);
		return record;
	}
	
	public SysMenu selectByPrimaryKey(Integer id) {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(MenuRequestBean menuRequestBean,Integer id) {
		
		SysMenu record = mapper.selectByPrimaryKey(id); 
		BeanUtils.copyProperties(menuRequestBean, record);
		 
		return mapper.updateByPrimaryKeySelective(record);
	}
	
	@Transactional
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public List<SysMenu> getMenuListRoot() {
		 
		logger.info("query");
		return mapper.getMenuListByTree("root");
	}
	
	public List<SysMenu> getMenuListByTree(String treeName) {
		 
		logger.info("query");
		return mapper.getMenuListByTree(treeName);
	}
}
