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
	private SysMenuMapper menuMapper;
	
	public List<SysMenu> getMenuListPage(@Param("page") PageInfo page) {
		 
		logger.info("query");
		return menuMapper.getMenuListPage(page);
	}
	
	@Transactional
	public int insert(MenuRequestBean menuRequestBean) throws KunSoftwareException {
		
		if("1".equals(menuRequestBean.getMenuTreeName())) {
			throw new KunSoftwareException("保存失败");
		}
		
		SysMenu record = new SysMenu();
		BeanUtils.copyProperties(menuRequestBean, record);
		 
		return menuMapper.insert(record);
	}
	
	public SysMenu selectByPrimaryKey(Integer id) {
		
		return menuMapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(MenuRequestBean menuRequestBean,Integer id) {
		
		SysMenu record = menuMapper.selectByPrimaryKey(id); 
		BeanUtils.copyProperties(menuRequestBean, record);
		 
		return menuMapper.updateByPrimaryKeySelective(record);
	}
	
	@Transactional
	public int deleteByPrimaryKey(Integer id) {
		return menuMapper.deleteByPrimaryKey(id);
	}
}
