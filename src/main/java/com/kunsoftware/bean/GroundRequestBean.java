package com.kunsoftware.bean;

import org.springframework.web.multipart.MultipartFile;

public class GroundRequestBean {

	private String name;

    private Integer destination;

    private String type;

    private String groundDescribe;

    private String linkMain;

    private String remark;

    private MultipartFile  imageFile;

    private MultipartFile  qualificationImageFile;

    private String frontDeskRecommend;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDestination() {
		return destination;
	}

	public void setDestination(Integer destination) {
		this.destination = destination;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGroundDescribe() {
		return groundDescribe;
	}

	public void setGroundDescribe(String groundDescribe) {
		this.groundDescribe = groundDescribe;
	}

	public String getLinkMain() {
		return linkMain;
	}

	public void setLinkMain(String linkMain) {
		this.linkMain = linkMain;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	} 

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public MultipartFile getQualificationImageFile() {
		return qualificationImageFile;
	}

	public void setQualificationImageFile(MultipartFile qualificationImageFile) {
		this.qualificationImageFile = qualificationImageFile;
	}

	public String getFrontDeskRecommend() {
		return frontDeskRecommend;
	}

	public void setFrontDeskRecommend(String frontDeskRecommend) {
		this.frontDeskRecommend = frontDeskRecommend;
	}
    
    
}
