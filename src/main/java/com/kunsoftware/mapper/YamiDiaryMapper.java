package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.YamiDiary;
import com.kunsoftware.page.PageInfo;

public interface YamiDiaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YamiDiary record);

    int insertSelective(YamiDiary record);

    YamiDiary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YamiDiary record);

    int updateByPrimaryKey(YamiDiary record);
    
    List<YamiDiary> getYamiDiaryListPage(@Param("keyword") String kewword,@Param("page") PageInfo page);
}