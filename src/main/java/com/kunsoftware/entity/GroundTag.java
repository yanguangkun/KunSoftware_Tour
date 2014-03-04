package com.kunsoftware.entity;

public class GroundTag {
    private Integer id;

    private Integer groundId;

    private Integer destination;

    private String type;

    private String keyword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroundId() {
        return groundId;
    }

    public void setGroundId(Integer groundId) {
        this.groundId = groundId;
    }

    public Integer getDestination() {
        return destination;
    }

    public void setDestination(Integer destination) {
        this.destination = destination;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}