package com.kunsoftware.bean;

import org.springframework.web.multipart.MultipartFile;

public class OrdersAttachmentRequestBean {

	private Integer ordersId;

    private String name;

    private String frontDesk;

    private MultipartFile file;

	public Integer getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFrontDesk() {
		return frontDesk;
	}

	public void setFrontDesk(String frontDesk) {
		this.frontDesk = frontDesk;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
 
}
