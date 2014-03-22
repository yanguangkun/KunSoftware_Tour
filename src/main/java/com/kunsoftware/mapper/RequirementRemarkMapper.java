package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.RequirementRemark;

public interface RequirementRemarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RequirementRemark record);

    int insertSelective(RequirementRemark record);

    RequirementRemark selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RequirementRemark record);

    int updateByPrimaryKey(RequirementRemark record);
    
    List<RequirementRemark> getRequirementRemarkListAll(@Param("requirementId") Integer requirementId);
}