package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.HeadIconTitle;
import com.kunsoftware.entity.ValueSet;

public interface HeadIconTitleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HeadIconTitle record);

    int insertSelective(HeadIconTitle record);

    HeadIconTitle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HeadIconTitle record);

    int updateByPrimaryKey(HeadIconTitle record);
    
    List<HeadIconTitle> getHeadIconTitleListAll(@Param("type") String type);
    
    List<ValueSet> selectValueSetList(@Param("type") String type);
    
    List<ValueSet> getValueSetListBySex(@Param("type") String type,@Param("sex") String sex);
    
    HeadIconTitle selectMemberInfo();
}