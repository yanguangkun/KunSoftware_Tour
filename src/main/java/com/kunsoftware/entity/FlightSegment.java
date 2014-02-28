package com.kunsoftware.entity;

public class FlightSegment {
    private Integer id;

    private Integer flightId;

    private Integer orderValue;

    private String serialNumber;

    private Integer airlineId;

    private String flightNum;

    private Integer startCountryId;

    private Integer startCityId;

    private String startAirport;

    private String startTime;

    private Integer arriveCountryId;

    private Integer arriveCityId;

    private Integer arriveAirport;

    private String arriveTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Integer getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(Integer orderValue) {
        this.orderValue = orderValue;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Integer airlineId) {
        this.airlineId = airlineId;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
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

    public String getStartAirport() {
        return startAirport;
    }

    public void setStartAirport(String startAirport) {
        this.startAirport = startAirport;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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

    public Integer getArriveAirport() {
        return arriveAirport;
    }

    public void setArriveAirport(Integer arriveAirport) {
        this.arriveAirport = arriveAirport;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }
}