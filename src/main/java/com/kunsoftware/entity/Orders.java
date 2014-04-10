package com.kunsoftware.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Orders {
    private Integer id;

    private String productId;

    private String productName;

    private Integer flightCheduleId;

    private String arriveCountry;

    private Integer arriveDestination;

    private String source;

    private String type;

    private String code;

    private Integer quantity;

    private BigDecimal amount;

    private BigDecimal earnest;

    private BigDecimal paidAmount;

    private BigDecimal arrearsAmount;

    private Date businessDate;

    private Integer userId;

    private String userName;

    private String linkman;

    private String linkmanMobile;

    private String fullAmount;

    private Date orderDate;

    private String guestRemark;

    private String serviceRemark;

    private Integer flightChedulePlanPriceId;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getFlightCheduleId() {
        return flightCheduleId;
    }

    public void setFlightCheduleId(Integer flightCheduleId) {
        this.flightCheduleId = flightCheduleId;
    }

    public String getArriveCountry() {
        return arriveCountry;
    }

    public void setArriveCountry(String arriveCountry) {
        this.arriveCountry = arriveCountry;
    }

    public Integer getArriveDestination() {
        return arriveDestination;
    }

    public void setArriveDestination(Integer arriveDestination) {
        this.arriveDestination = arriveDestination;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getEarnest() {
        return earnest;
    }

    public void setEarnest(BigDecimal earnest) {
        this.earnest = earnest;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public BigDecimal getArrearsAmount() {
        return arrearsAmount;
    }

    public void setArrearsAmount(BigDecimal arrearsAmount) {
        this.arrearsAmount = arrearsAmount;
    }

    public Date getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(Date businessDate) {
        this.businessDate = businessDate;
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

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkmanMobile() {
        return linkmanMobile;
    }

    public void setLinkmanMobile(String linkmanMobile) {
        this.linkmanMobile = linkmanMobile;
    }

    public String getFullAmount() {
        return fullAmount;
    }

    public void setFullAmount(String fullAmount) {
        this.fullAmount = fullAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getGuestRemark() {
        return guestRemark;
    }

    public void setGuestRemark(String guestRemark) {
        this.guestRemark = guestRemark;
    }

    public String getServiceRemark() {
        return serviceRemark;
    }

    public void setServiceRemark(String serviceRemark) {
        this.serviceRemark = serviceRemark;
    }

    public Integer getFlightChedulePlanPriceId() {
        return flightChedulePlanPriceId;
    }

    public void setFlightChedulePlanPriceId(Integer flightChedulePlanPriceId) {
        this.flightChedulePlanPriceId = flightChedulePlanPriceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}