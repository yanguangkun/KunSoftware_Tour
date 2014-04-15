package com.kunsoftware.entity;

import java.util.Date;
import java.util.List;

public class PhotoAlbum {
    private Integer id;

    private String title;

    private Date startDate;

    private String summary;

    private Integer destination;

    private String imagePath;

    private String frontDesk;

    private String indexRecommend;

    private String indexImagePath;

    private List<PhotoAlbumTimeline> timelineList;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getDestination() {
        return destination;
    }

    public void setDestination(Integer destination) {
        this.destination = destination;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getFrontDesk() {
        return frontDesk;
    }

    public void setFrontDesk(String frontDesk) {
        this.frontDesk = frontDesk;
    }

    public String getIndexRecommend() {
        return indexRecommend;
    }

    public void setIndexRecommend(String indexRecommend) {
        this.indexRecommend = indexRecommend;
    }

    public String getIndexImagePath() {
        return indexImagePath;
    }

    public void setIndexImagePath(String indexImagePath) {
        this.indexImagePath = indexImagePath;
    }

	public List<PhotoAlbumTimeline> getTimelineList() {
		return timelineList;
	}

	public void setTimelineList(List<PhotoAlbumTimeline> timelineList) {
		this.timelineList = timelineList;
	}
    
    
}