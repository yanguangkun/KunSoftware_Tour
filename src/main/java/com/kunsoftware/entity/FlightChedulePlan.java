package com.kunsoftware.entity;

import java.math.BigDecimal;

public class FlightChedulePlan {
    private Integer id;

    private Integer flightCheduleId;

    private Integer productPlanTplId;

    private String name;

    private String planDescribe;

    private Integer orderValue;

    private BigDecimal adultPrice;

    private BigDecimal adultExtraBedPrice;

    private BigDecimal childBedPrice;

    private BigDecimal childNoBedPrice;

    private BigDecimal singleRoom;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFlightCheduleId() {
        return flightCheduleId;
    }

    public void setFlightCheduleId(Integer flightCheduleId) {
        this.flightCheduleId = flightCheduleId;
    }

    public Integer getProductPlanTplId() {
        return productPlanTplId;
    }

    public void setProductPlanTplId(Integer productPlanTplId) {
        this.productPlanTplId = productPlanTplId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanDescribe() {
        return planDescribe;
    }

    public void setPlanDescribe(String planDescribe) {
        this.planDescribe = planDescribe;
    }

    public Integer getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(Integer orderValue) {
        this.orderValue = orderValue;
    }

    public BigDecimal getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(BigDecimal adultPrice) {
        this.adultPrice = adultPrice;
    }

    public BigDecimal getAdultExtraBedPrice() {
        return adultExtraBedPrice;
    }

    public void setAdultExtraBedPrice(BigDecimal adultExtraBedPrice) {
        this.adultExtraBedPrice = adultExtraBedPrice;
    }

    public BigDecimal getChildBedPrice() {
        return childBedPrice;
    }

    public void setChildBedPrice(BigDecimal childBedPrice) {
        this.childBedPrice = childBedPrice;
    }

    public BigDecimal getChildNoBedPrice() {
        return childNoBedPrice;
    }

    public void setChildNoBedPrice(BigDecimal childNoBedPrice) {
        this.childNoBedPrice = childNoBedPrice;
    }

    public BigDecimal getSingleRoom() {
        return singleRoom;
    }

    public void setSingleRoom(BigDecimal singleRoom) {
        this.singleRoom = singleRoom;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}