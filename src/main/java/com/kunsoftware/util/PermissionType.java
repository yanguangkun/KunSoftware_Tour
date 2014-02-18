package com.kunsoftware.util;

public enum PermissionType {
	ADMIN("系统管理员", 1), 
	USER("登录用户", 2),
	;
	private String name;
	private int index;

	private PermissionType(String name, int index) {
	    this.name = name;
	    this.index = index;
	}
	public String getName() {
	    return name;
	}
	public void setName(String name) {
	    this.name = name;
	}
	public int getIndex() {
	    return index;
	}
	public void setIndex(int index) {
	    this.index = index;
	}
}
