package com.kunsoftware.mapper;

import java.util.List;

import com.kunsoftware.entity.ArticleClassify;
import com.kunsoftware.entity.ValueSet;

public interface ArticleClassifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleClassify record);

    int insertSelective(ArticleClassify record);

    ArticleClassify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleClassify record);

    int updateByPrimaryKey(ArticleClassify record);
    
    List<ArticleClassify> getArticleClassifyListAll();
    
    List<ValueSet> selectValueSetList();
}