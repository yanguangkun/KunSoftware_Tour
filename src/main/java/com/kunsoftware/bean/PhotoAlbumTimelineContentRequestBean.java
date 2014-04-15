package com.kunsoftware.bean;

import org.springframework.web.multipart.MultipartFile;

public class PhotoAlbumTimelineContentRequestBean {

	private Integer photoAlbumTimelineId;

    private Integer orderValue;

    private String style;

    private String title;

    private MultipartFile imageFile;

    private String content;

	public Integer getPhotoAlbumTimelineId() {
		return photoAlbumTimelineId;
	}

	public void setPhotoAlbumTimelineId(Integer photoAlbumTimelineId) {
		this.photoAlbumTimelineId = photoAlbumTimelineId;
	}

	public Integer getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
    
}
