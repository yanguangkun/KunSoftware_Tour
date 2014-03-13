package com.kunsoftware.bean;

public class OrdersStatusRequestBean {

	private Integer ordersId;

    private String statusDescribe;
    
    private String frontDesk;

	public Integer getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
	}

	public String getStatusDescribe() {
		return statusDescribe;
	}

	public void setStatusDescribe(String statusDescribe) {
		this.statusDescribe = statusDescribe;
	}

	public String getFrontDesk() {
		return frontDesk;
	}

	public void setFrontDesk(String frontDesk) {
		this.frontDesk = frontDesk;
	}
    
    
}
