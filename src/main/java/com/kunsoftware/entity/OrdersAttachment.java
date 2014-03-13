package com.kunsoftware.entity;

import java.util.Date;

public class OrdersAttachment {
    private Integer id;

    private Integer ordersId;

    private String name;

    private String frontDesk;

    private String filePath;

    private Date dealDate;

    private Integer userId;

    private String userName;

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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
}