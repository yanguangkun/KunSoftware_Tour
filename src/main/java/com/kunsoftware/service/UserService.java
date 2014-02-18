package com.kunsoftware.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kunsoftware.entity.SysUser;
import com.kunsoftware.mapper.SysUserMapper;

@Service
public class UserService {

private static Logger logger = LoggerFactory.getLogger(UserService.class);	
	
	@Autowired
	private SysUserMapper userMapper;
	
	public SysUser selectByUserName(String userName) {
		
		logger.info("查询用户");
		return userMapper.selectByUserName(userName);
	}
}
