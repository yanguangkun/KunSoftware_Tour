package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.Gallery;
import com.kunsoftware.page.PageInfo;

public interface GalleryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Gallery record);

    int insertSelective(Gallery record);

    Gallery selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Gallery record);

    int updateByPrimaryKey(Gallery record);
    
    List<Gallery> getGalleryListPage(@Param("keyword") String keyword,@Param("page") PageInfo page);
    
    List<Gallery> getGalleryListAll(@Param("type") String type);
}