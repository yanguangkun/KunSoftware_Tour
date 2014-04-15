package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.PhotoAlbumTimeline;

public interface PhotoAlbumTimelineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PhotoAlbumTimeline record);

    int insertSelective(PhotoAlbumTimeline record);

    PhotoAlbumTimeline selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PhotoAlbumTimeline record);

    int updateByPrimaryKey(PhotoAlbumTimeline record);
    
    List<PhotoAlbumTimeline> getPhotoAlbumTimelineListAll(@Param("photoAlbumId") Integer photoAlbumId);
}