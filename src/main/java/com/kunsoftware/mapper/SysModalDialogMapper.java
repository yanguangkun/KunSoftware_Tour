package com.kunsoftware.mapper;

import com.kunsoftware.entity.SysModalDialog;

public interface SysModalDialogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysModalDialog record);

    int insertSelective(SysModalDialog record);

    SysModalDialog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysModalDialog record);

    int updateByPrimaryKey(SysModalDialog record);
    
    SysModalDialog selectByCode(String code);
}