package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.Customize;
import com.kunsoftware.page.PageInfo;

public interface CustomizeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Customize record);

    int insertSelective(Customize record);

    Customize selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Customize record);

    int updateByPrimaryKey(Customize record);
    
    List<Customize> getCustomizeListPage(@Param("destination") String destination,
   		 @Param("keyword") String keyword,
   		 @Param("page") PageInfo page);
    
    List<Customize> getFrontCustomizeListPage(@Param("destination") String destination,
      		 @Param("page") PageInfo page);
}