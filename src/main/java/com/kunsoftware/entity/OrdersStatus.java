package com.kunsoftware.entity;

import java.util.Date;

public class OrdersStatus {
    private Integer id;

    private Integer ordersId;

    private String statusDescribe;

    private Date dealDate;

    private Integer userId;

    private String userName;

    private String frontDesk;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFrontDesk() {
        return frontDesk;
    }

    public void setFrontDesk(String frontDesk) {
        this.frontDesk = frontDesk;
    }
}