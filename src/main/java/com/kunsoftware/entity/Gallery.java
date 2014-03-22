package com.kunsoftware.entity;

public class Gallery {
    private Integer id;

    private String type;

    private Integer productResourceId;

    private String productResourceName;

    private String title1;

    private String title2;

    private Integer orderValue;

    private String link;

    private Integer destination;

    private String imagePath;

    private String enable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getProductResourceId() {
        return productResourceId;
    }

    public void setProductResourceId(Integer productResourceId) {
        this.productResourceId = productResourceId;
    }

    public String getProductResourceName() {
        return productResourceName;
    }

    public void setProductResourceName(String productResourceName) {
        this.productResourceName = productResourceName;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public Integer getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(Integer orderValue) {
        this.orderValue = orderValue;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getDestination() {
        return destination;
    }

    public void setDestination(Integer destination) {
        this.destination = destination;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}