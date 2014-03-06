package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.GroundTag;
import com.kunsoftware.entity.ValueSet;
import com.kunsoftware.page.PageInfo;

public interface GroundTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroundTag record);

    int insertSelective(GroundTag record);

    GroundTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroundTag record);

    int updateByPrimaryKeyWithBLOBs(GroundTag record);

    int updateByPrimaryKey(GroundTag record);
    
    List<GroundTag> getGroundTagListPage(@Param("destination") Integer destination,
    		@Param("groundId") Integer groundId,
    		@Param("page") PageInfo page);
    
    List<ValueSet> getValueSetListByGround(@Param("destination") Integer destination,@Param("groundId") Integer groundId);
}