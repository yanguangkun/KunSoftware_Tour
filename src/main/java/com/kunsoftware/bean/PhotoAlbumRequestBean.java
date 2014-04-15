package com.kunsoftware.bean;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class PhotoAlbumRequestBean {

	private String title;

    private Date startDate;

    private String summary;

    private Integer destination;

    private MultipartFile imageFile;

    private String frontDesk;

    private String indexRecommend;

    private MultipartFile indexImageFile;

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

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
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

	public MultipartFile getIndexImageFile() {
		return indexImageFile;
	}

	public void setIndexImageFile(MultipartFile indexImageFile) {
		this.indexImageFile = indexImageFile;
	}
    
    
}
