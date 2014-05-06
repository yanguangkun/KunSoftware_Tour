package com.kunsoftware.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kunsoftware.bean.MemberRequestBean;
import com.kunsoftware.entity.Member;
import com.kunsoftware.exception.KunSoftwareException;
import com.kunsoftware.mapper.MemberMapper;

@Service
public class MemberService {

	private static Logger logger = LoggerFactory.getLogger(MemberService.class);	
	
	@Autowired
	private MemberMapper mapper; 
	
	public Member selectByUserName(String userName) {
		
		logger.info("查询用户");
		return mapper.selectByUserName(userName);
	}
	
	public Member selectByEmail(String email) {
		
		logger.info("查询用户");
		return mapper.selectByEmail(email);
	}

	public List<Member> getMemberListAll() {
		 
		logger.info("query");
		return mapper.getMemberListAll();
	}
	 
	@Transactional
	public Member insert(MemberRequestBean requestBean) throws KunSoftwareException {		 
		
		Member record = new Member();
		BeanUtils.copyProperties(requestBean, record); 
		  
		mapper.insert(record);
		return record;
	}
	
	public Member selectByPrimaryKey(Integer id) throws KunSoftwareException {
		
		return mapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int updateByPrimaryKey(MemberRequestBean requestBean,Integer id) throws KunSoftwareException {
		
		Member record = mapper.selectByPrimaryKey(id); 
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
