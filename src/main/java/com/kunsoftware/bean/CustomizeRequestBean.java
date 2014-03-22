package com.kunsoftware.bean;

import org.springframework.web.multipart.MultipartFile;

public class CustomizeRequestBean {

	private String title1;

    private String title2;

    private String title3;

    private String summary;

    private Integer destination;

    private Integer productResourceId;

    private String productResourceName;

    private MultipartFile  imageFile;

    private String frontDesk;

	public String getTitle1() {
		return title1;
	}

	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	public String getTitle2() {
		return title2;
	}

	public void setTitle2(String title2) {
		this.title2 = title2;
	}

	public String getTitle3() {
		return title3;
	}

	public void setTitle3(String title3) {
		this.title3 = title3;
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

	public Integer getProductResourceId() {
		return productResourceId;
	}

	public void setProductResourceId(Integer productResourceId) {
		this.productResourceId = productResourceId;
	}

	public String getProductResourceName() {
		return productResourceName;
	}

	public void setProductResourceName(String productResourceName) {
		this.productResourceName = productResourceName;
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
    
    
}
