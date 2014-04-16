package com.kunsoftware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kunsoftware.entity.PhotoAlbum;
import com.kunsoftware.page.PageInfo;

public interface PhotoAlbumMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PhotoAlbum record);

    int insertSelective(PhotoAlbum record);

    PhotoAlbum selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PhotoAlbum record);

    int updateByPrimaryKey(PhotoAlbum record);
    
    List<PhotoAlbum> getPhotoAlbumListPage(@Param("destination") String destination,
    		@Param("keyword") String kewword,   
    		@Param("frontDesk") String frontDesk,  
    		@Param("page") PageInfo page);
    
    PhotoAlbum selectByIndexRecommend(@Param("indexRecommend") String indexRecommend);
}