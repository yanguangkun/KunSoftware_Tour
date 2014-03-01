package com.kunsoftware.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Flight {
    private Integer id;

    private String name;

    private String instruction;

    private String priceInstruction;

    private String startCountry;

    private String startCity;

    private String arriveCountry;

    private String arriveCity;

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

    public String getStartCountry() {
        return startCountry;
    }

    public void setStartCountry(String startCountry) {
        this.startCountry = startCountry;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getArriveCountry() {
        return arriveCountry;
    }

    public void setArriveCountry(String arriveCountry) {
        this.arriveCountry = arriveCountry;
    }

    public String getArriveCity() {
        return arriveCity;
    }

    public void setArriveCity(String arriveCity) {
        this.arriveCity = arriveCity;
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