package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.Ground;
import com.kunsoftware.entity.ValueSet;
import com.kunsoftware.page.PageInfo;

public interface GroundMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ground record);

    int insertSelective(Ground record);

    Ground selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ground record);

    int updateByPrimaryKey(Ground record);   
    
    List<Ground> getGroundListPage(@Param("destination") Integer destination,@Param("page") PageInfo page);
    
    List<ValueSet> selectValueSetList();
    
    List<ValueSet> getValueSetListByDestination(@Param("destination") Integer destination);
    
    List<Ground> getGroundByDestinationListPage(@Param("destination") Integer destination,@Param("page") PageInfo page);
    
}