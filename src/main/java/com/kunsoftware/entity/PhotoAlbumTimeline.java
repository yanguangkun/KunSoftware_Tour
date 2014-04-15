package com.kunsoftware.entity;

import java.util.List;

public class PhotoAlbumTimeline {
    private Integer id;

    private Integer photoAlbumId;

    private Integer orderValue;

    private String title;
    
    private List<PhotoAlbumTimelineContent> contentList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPhotoAlbumId() {
        return photoAlbumId;
    }

    public void setPhotoAlbumId(Integer photoAlbumId) {
        this.photoAlbumId = photoAlbumId;
    }

    public Integer getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(Integer orderValue) {
        this.orderValue = orderValue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

	public List<PhotoAlbumTimelineContent> getContentList() {
		return contentList;
	}

	public void setContentList(List<PhotoAlbumTimelineContent> contentList) {
		this.contentList = contentList;
	}
    
    
}