package com.kunsoftware.bean;

import org.springframework.web.multipart.MultipartFile;

public class ProductRequestBean {

	private String name;

    private String type;

    private String customMade;

    private String startCountry;

    private String startCity;

    private String arriveCountry;

    private String arriveCity;

    private Integer arriveDestination;

    private String managerComment;

    private Integer galleryId;

    private String galleryName;

    private MultipartFile  imageFile;
    
    private String remark;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCustomMade() {
		return customMade;
	}

	public void setCustomMade(String customMade) {
		this.customMade = customMade;
	}

	public String getStartCountry() {
		return startCountry;
	}

	public void setStartCountry(String startCountry) {
		this.startCountry = startCountry;
	}

	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public String getArriveCountry() {
		return arriveCountry;
	}

	public void setArriveCountry(String arriveCountry) {
		this.arriveCountry = arriveCountry;
	}

	public String getArriveCity() {
		return arriveCity;
	}

	public void setArriveCity(String arriveCity) {
		this.arriveCity = arriveCity;
	}

	public Integer getArriveDestination() {
		return arriveDestination;
	}

	public void setArriveDestination(Integer arriveDestination) {
		this.arriveDestination = arriveDestination;
	}

	public String getManagerComment() {
		return managerComment;
	}

	public void setManagerComment(String managerComment) {
		this.managerComment = managerComment;
	}

	public Integer getGalleryId() {
		return galleryId;
	}

	public void setGalleryId(Integer galleryId) {
		this.galleryId = galleryId;
	}
 
	public String getGalleryName() {
		return galleryName;
	}

	public void setGalleryName(String galleryName) {
		this.galleryName = galleryName;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
    
}
