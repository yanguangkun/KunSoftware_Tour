package com.kunsoftware.mapper;

import com.kunsoftware.entity.GalleryDetail;

public interface GalleryDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GalleryDetail record);

    int insertSelective(GalleryDetail record);

    GalleryDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GalleryDetail record);

    int updateByPrimaryKey(GalleryDetail record);
}