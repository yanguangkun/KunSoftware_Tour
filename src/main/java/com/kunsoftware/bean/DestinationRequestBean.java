package com.kunsoftware.bean;

import org.springframework.web.multipart.MultipartFile;

public class DestinationRequestBean {

	private String name;

    private String nameEn;

    private String countryValue;

    private Integer galleryId;
    
    private String galleryName;

    private String imagePath; 
    
    private MultipartFile  imageFile;

    private String enable;
    
    private Integer orderValue;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getCountryValue() {
		return countryValue;
	}

	public void setCountryValue(String countryValue) {
		this.countryValue = countryValue;
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

	public String getImagePath() {
		return imagePath;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public Integer getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}
    
    
}
