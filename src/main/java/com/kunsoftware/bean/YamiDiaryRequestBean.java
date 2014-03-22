package com.kunsoftware.bean;

import org.springframework.web.multipart.MultipartFile;

public class YamiDiaryRequestBean {

	private String title;

    private String bgcolor;

    private String indexRecommend;

    private String content;

    private MultipartFile imageFile;

    private MultipartFile indexImageFile;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBgcolor() {
		return bgcolor;
	}

	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}

	public String getIndexRecommend() {
		return indexRecommend;
	}

	public void setIndexRecommend(String indexRecommend) {
		this.indexRecommend = indexRecommend;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public MultipartFile getIndexImageFile() {
		return indexImageFile;
	}

	public void setIndexImageFile(MultipartFile indexImageFile) {
		this.indexImageFile = indexImageFile;
	}

    
}
