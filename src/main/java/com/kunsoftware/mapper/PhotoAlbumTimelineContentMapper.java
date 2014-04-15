package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.PhotoAlbumTimelineContent;

public interface PhotoAlbumTimelineContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PhotoAlbumTimelineContent record);

    int insertSelective(PhotoAlbumTimelineContent record);

    PhotoAlbumTimelineContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PhotoAlbumTimelineContent record);

    int updateByPrimaryKey(PhotoAlbumTimelineContent record);
    
    List<PhotoAlbumTimelineContent> getPhotoAlbumTimelineContentListAll(@Param("photoAlbumTimelineId") Integer photoAlbumTimelineId);
}