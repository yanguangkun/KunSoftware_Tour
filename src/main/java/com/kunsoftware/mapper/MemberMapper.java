package com.kunsoftware.mapper;

import java.util.List;

import com.kunsoftware.entity.Member;

public interface MemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);
    
    Member selectByUserName(String userName);
    
    List<Member> getMemberListAll();
}