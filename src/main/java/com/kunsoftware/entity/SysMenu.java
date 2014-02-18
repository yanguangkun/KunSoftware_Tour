package com.kunsoftware.entity;

public class SysMenu {
    private Integer id;

    private String menuTreeName;

    private String menuEname;

    private String menuCname;

    private String menuUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuTreeName() {
        return menuTreeName;
    }

    public void setMenuTreeName(String menuTreeName) {
        this.menuTreeName = menuTreeName;
    }

    public String getMenuEname() {
        return menuEname;
    }

    public void setMenuEname(String menuEname) {
        this.menuEname = menuEname;
    }

    public String getMenuCname() {
        return menuCname;
    }

    public void setMenuCname(String menuCname) {
        this.menuCname = menuCname;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }
}