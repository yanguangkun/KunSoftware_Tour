package com.kunsoftware.entity;

public class SysMenu {
    private Integer id;

    private String menuTreeName;

    private String menuEname;

    private String menuCname;

    private String menuUrl;

    private String menuDivider;

    private Integer orderValue;

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

    public String getMenuDivider() {
        return menuDivider;
    }

    public void setMenuDivider(String menuDivider) {
        this.menuDivider = menuDivider;
    }

    public Integer getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(Integer orderValue) {
        this.orderValue = orderValue;
    }
}