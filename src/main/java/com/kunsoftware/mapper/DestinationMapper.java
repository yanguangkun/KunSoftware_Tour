package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.Destination;
import com.kunsoftware.entity.ValueSet;
import com.kunsoftware.page.PageInfo;

public interface DestinationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Destination record);

    int insertSelective(Destination record);

    Destination selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Destination record);

    int updateByPrimaryKey(Destination record);    
    
    List<Destination> getDestinationListPage(@Param("keyword") String kewword,@Param("page") PageInfo page);
    List<ValueSet> selectValueSetList();
}