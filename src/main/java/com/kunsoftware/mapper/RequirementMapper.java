package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.Requirement;
import com.kunsoftware.page.PageInfo;

public interface RequirementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Requirement record);

    int insertSelective(Requirement record);

    Requirement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Requirement record);

    int updateByPrimaryKey(Requirement record);
    
    List<Requirement> getRequirementListPage(@Param("status") String status,
    		@Param("arriveDestination") String arriveDestination,
    		@Param("linkman") String linkman,
    		@Param("mobile") String mobile,
    		@Param("page") PageInfo page);
}