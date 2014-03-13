package com.kunsoftware.mapper;

import java.util.List;

import com.kunsoftware.entity.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    
    SysUser selectByUserName(String userName);
    
    List<SysUser> getSysUserListAll();
}