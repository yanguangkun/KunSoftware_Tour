package com.kunsoftware.mapper;

import com.kunsoftware.entity.Gallery;

public interface GalleryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Gallery record);

    int insertSelective(Gallery record);

    Gallery selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Gallery record);

    int updateByPrimaryKey(Gallery record);
}