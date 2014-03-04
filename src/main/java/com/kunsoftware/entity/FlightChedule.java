package com.kunsoftware.entity;

import java.util.Date;

public class FlightChedule {
    private Integer id;

    private Integer productResourceId;

    private Date startDate;

    private String valid;

    private String audit;

    private String status;

    private Date lastReservationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductResourceId() {
        return productResourceId;
    }

    public void setProductResourceId(Integer productResourceId) {
        this.productResourceId = productResourceId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastReservationDate() {
        return lastReservationDate;
    }

    public void setLastReservationDate(Date lastReservationDate) {
        this.lastReservationDate = lastReservationDate;
    }
}