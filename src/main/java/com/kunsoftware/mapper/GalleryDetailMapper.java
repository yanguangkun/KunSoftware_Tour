package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.GalleryDetail;

public interface GalleryDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GalleryDetail record);

    int insertSelective(GalleryDetail record);

    GalleryDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GalleryDetail record);

    int updateByPrimaryKey(GalleryDetail record);
    
    List<GalleryDetail> getGalleryDetailListAll(@Param("galleryId") Integer galleryId); 
    int deleteByGalleryId(@Param("galleryId") Integer galleryId); 
}