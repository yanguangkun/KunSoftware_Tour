package com.kunsoftware.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Flight {
    private Integer id;

    private String name;

    private String instruction;

    private String priceInstruction;

    private Integer startCountryId;

    private Integer startCityId;

    private Integer arriveCountryId;

    private Integer arriveCityId;

    private Integer arriveDestination;

    private Date deadline;

    private BigDecimal adultPrice;

    private BigDecimal childPrice;

    private String enable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getPriceInstruction() {
        return priceInstruction;
    }

    public void setPriceInstruction(String priceInstruction) {
        this.priceInstruction = priceInstruction;
    }

    public Integer getStartCountryId() {
        return startCountryId;
    }

    public void setStartCountryId(Integer startCountryId) {
        this.startCountryId = startCountryId;
    }

    public Integer getStartCityId() {
        return startCityId;
    }

    public void setStartCityId(Integer startCityId) {
        this.startCityId = startCityId;
    }

    public Integer getArriveCountryId() {
        return arriveCountryId;
    }

    public void setArriveCountryId(Integer arriveCountryId) {
        this.arriveCountryId = arriveCountryId;
    }

    public Integer getArriveCityId() {
        return arriveCityId;
    }

    public void setArriveCityId(Integer arriveCityId) {
        this.arriveCityId = arriveCityId;
    }

    public Integer getArriveDestination() {
        return arriveDestination;
    }

    public void setArriveDestination(Integer arriveDestination) {
        this.arriveDestination = arriveDestination;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public BigDecimal getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(BigDecimal adultPrice) {
        this.adultPrice = adultPrice;
    }

    public BigDecimal getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(BigDecimal childPrice) {
        this.childPrice = childPrice;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}