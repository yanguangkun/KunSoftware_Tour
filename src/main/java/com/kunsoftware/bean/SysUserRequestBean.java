package com.kunsoftware.bean;

import java.util.Date;

public class SysUserRequestBean {

	private String userName;

    private String password;

    private Date loginTime;

    private String loginIp;

    private String nickName;

    private String role;

    private String sex;

    private Integer headTitleId;

    private Integer headIconId;

    private String enable;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getHeadTitleId() {
		return headTitleId;
	}

	public void setHeadTitleId(Integer headTitleId) {
		this.headTitleId = headTitleId;
	}

	public Integer getHeadIconId() {
		return headIconId;
	}

	public void setHeadIconId(Integer headIconId) {
		this.headIconId = headIconId;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}
    
    
}
