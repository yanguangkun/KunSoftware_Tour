package com.kunsoftware.entity;

public class Ground {
    private Integer id;

    private String name;

    private Integer destination;

    private String type;

    private String groundDescribe;

    private String linkMain;

    private String remark;

    private String imagePath;

    private String qualificationImagePath;

    private String frontDeskRecommend;

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

    public String getGroundDescribe() {
        return groundDescribe;
    }

    public void setGroundDescribe(String groundDescribe) {
        this.groundDescribe = groundDescribe;
    }

    public String getLinkMain() {
        return linkMain;
    }

    public void setLinkMain(String linkMain) {
        this.linkMain = linkMain;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getQualificationImagePath() {
        return qualificationImagePath;
    }

    public void setQualificationImagePath(String qualificationImagePath) {
        this.qualificationImagePath = qualificationImagePath;
    }

    public String getFrontDeskRecommend() {
        return frontDeskRecommend;
    }

    public void setFrontDeskRecommend(String frontDeskRecommend) {
        this.frontDeskRecommend = frontDeskRecommend;
    }
}