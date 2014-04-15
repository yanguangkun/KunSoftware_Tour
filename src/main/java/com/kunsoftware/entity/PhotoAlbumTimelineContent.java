package com.kunsoftware.entity;

public class PhotoAlbumTimelineContent {
    private Integer id;

    private Integer photoAlbumTimelineId;

    private Integer orderValue;

    private String style;

    private String title;

    private String imagePath;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}