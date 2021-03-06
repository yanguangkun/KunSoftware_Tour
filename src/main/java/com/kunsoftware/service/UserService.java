package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.SysUserRequestBean;
import com.kunsoftware.entity.SysUser;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.SysUserMapper;

@Service
public class UserService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);	
	
	@Autowired
	private SysUserMapper mapper;
	
	public SysUser selectByUserName(String userName) {
		
		logger.info("查询用户");
		return mapper.selectByUserName(userName);
	}

	
	public List<SysUser> getSysUserListAll() {
		 
		logger.info("query");
		return mapper.getSysUserListAll();
	}
	 
	@Transactional
	public SysUser insert(SysUserRequestBean requestBean) throws KunSoftwareException {		 
		
		SysUser record = new SysUser();
		BeanUtils.copyProperties(requestBean, record); 
		  
		mapper.insert(record);
		return record;
	}
	
	public SysUser selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(SysUserRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		SysUser record = mapper.selectByPrimaryKey(id); 
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
