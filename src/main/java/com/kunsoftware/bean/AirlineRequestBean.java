package com.kunsoftware.bean;

import org.springframework.web.multipart.MultipartFile;

public class AirlineRequestBean {

	private String name;

    private String imagePath;

    private MultipartFile  imageFile;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
    
    
}
