package com.kunsoftware.bean;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;


public class GalleryRequestBean {

	private String name;

    private String remark;
    
    private List<MultipartFile>  imageFile;
    
    private String[] isFile;
    
    private String[]  imagePaths;
    
    private String[] orderValue;
    
    private String[] title;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<MultipartFile> getImageFile() {
		return imageFile;
	}

	public void setImageFile(List<MultipartFile> imageFile) {
		this.imageFile = imageFile;
	}

	public String[] getIsFile() {
		return isFile;
	}

	public void setIsFile(String[] isFile) {
		this.isFile = isFile;
	}

	public String[] getImagePaths() {
		return imagePaths;
	}

	public void setImagePaths(String[] imagePaths) {
		this.imagePaths = imagePaths;
	}

	public String[] getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(String[] orderValue) {
		this.orderValue = orderValue;
	}

	public String[] getTitle() {
		return title;
	}

	public void setTitle(String[] title) {
		this.title = title;
	}
    
    
}
