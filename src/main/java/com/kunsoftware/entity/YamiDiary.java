package com.kunsoftware.entity;

import java.util.Date;

public class YamiDiary {
    private Integer id;

    private String title;

    private String bgcolor;

    private String indexRecommend;

    private String content;

    private String imagePath;

    private String indexImagePath;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }

    public String getIndexRecommend() {
        return indexRecommend;
    }

    public void setIndexRecommend(String indexRecommend) {
        this.indexRecommend = indexRecommend;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getIndexImagePath() {
        return indexImagePath;
    }

    public void setIndexImagePath(String indexImagePath) {
        this.indexImagePath = indexImagePath;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}