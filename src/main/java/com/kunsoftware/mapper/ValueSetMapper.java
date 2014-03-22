package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.ValueSet;
import com.kunsoftware.page.PageInfo;

public interface ValueSetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ValueSet record);

    int insertSelective(ValueSet record);

    ValueSet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ValueSet record);

    int updateByPrimaryKey(ValueSet record);
    
    List<ValueSet> selectValueSetList(String code);
    ValueSet selectValueSet(ValueSet record);
    List<ValueSet> getValueSetListPage(@Param("code") String code,@Param("keyword") String keyword,@Param("page") PageInfo page);
    List<ValueSet> getValueSetListAll(@Param("code") String code);
    List<ValueSet> selectValueSetListByParentValue(@Param("parentValue") String parentValue);
    
    ValueSet selectValueSetByCode(@Param("code") String code);
}