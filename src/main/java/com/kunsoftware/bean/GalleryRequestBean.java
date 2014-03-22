package com.kunsoftware.bean;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;


public class GalleryRequestBean {

	private String type;

    private Integer productResourceId;

    private String productResourceName;

    private String title1;

    private String title2;

    private Integer orderValue;

    private String link;

    private Integer destination;

    private MultipartFile imageFile;

    private String enable;
    
	private String name;

    private String remark;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Integer getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

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
    
    
}
