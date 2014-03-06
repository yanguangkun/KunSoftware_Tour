package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.bean.MenuRequestBean;
import com.kunsoftware.entity.SysMenu;
import com.kunsoftware.page.PageInfo;

public interface SysMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
    
    List<SysMenu> getMenuListPage(@Param(value="menuRequestBean")MenuRequestBean menuRequestBean,@Param("page") PageInfo page);
    
    List<SysMenu> getMenuListByTree(@Param(value="treeName")String treeName);
}