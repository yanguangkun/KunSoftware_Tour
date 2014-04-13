package com.kunsoftware.bean;

import java.util.Date;

public class BuyBean {

	private Integer id;
	
	private Integer tplId;
	
	private String cheduleMonth;
	
	private String cheduleDay;
	
	private Integer num1;
	
	private Integer num2;
	
	private Integer num3;
	
	private Integer num4;
	
	private Integer num5;
	
	private Integer num6;
	
	private String remark;
	
	private String linkman;
	
	private String linkmanMobile;
	
	private double allTotal;
	
	private String[] name;
	
	private String[] sex;
	
	private String[] passportAddr;
	
	private String[] passportNum;
	
	private String[] passportDate;
	
	private Date[] birthdate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTplId() {
		return tplId;
	}

	public void setTplId(Integer tplId) {
		this.tplId = tplId;
	}

	public String getCheduleMonth() {
		return cheduleMonth;
	}

	public void setCheduleMonth(String cheduleMonth) {
		this.cheduleMonth = cheduleMonth;
	}

	public String getCheduleDay() {
		return cheduleDay;
	}

	public void setCheduleDay(String cheduleDay) {
		this.cheduleDay = cheduleDay;
	}

	public Integer getNum1() {
		return num1;
	}

	public void setNum1(Integer num1) {
		this.num1 = num1;
	}

	public Integer getNum2() {
		return num2;
	}

	public void setNum2(Integer num2) {
		this.num2 = num2;
	}

	public Integer getNum3() {
		return num3;
	}

	public void setNum3(Integer num3) {
		this.num3 = num3;
	}

	public Integer getNum4() {
		return num4;
	}

	public void setNum4(Integer num4) {
		this.num4 = num4;
	}

	public Integer getNum5() {
		return num5;
	}

	public void setNum5(Integer num5) {
		this.num5 = num5;
	}

	public Integer getNum6() {
		return num6;
	}

	public void setNum6(Integer num6) {
		this.num6 = num6;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getLinkmanMobile() {
		return linkmanMobile;
	}

	public void setLinkmanMobile(String linkmanMobile) {
		this.linkmanMobile = linkmanMobile;
	}

	public String[] getName() {
		return name;
	}

	public void setName(String[] name) {
		this.name = name;
	}

	public String[] getSex() {
		return sex;
	}

	public void setSex(String[] sex) {
		this.sex = sex;
	}

	public String[] getPassportAddr() {
		return passportAddr;
	}

	public void setPassportAddr(String[] passportAddr) {
		this.passportAddr = passportAddr;
	}

	public String[] getPassportNum() {
		return passportNum;
	}

	public void setPassportNum(String[] passportNum) {
		this.passportNum = passportNum;
	}

	public String[] getPassportDate() {
		return passportDate;
	}

	public void setPassportDate(String[] passportDate) {
		this.passportDate = passportDate;
	}

	public Date[] getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date[] birthdate) {
		this.birthdate = birthdate;
	}

	public double getAllTotal() {
		return allTotal;
	}

	public void setAllTotal(double allTotal) {
		this.allTotal = allTotal;
	}
	
	public Integer getQuantity() {
		return num1 + num2 + num3 + num4 + num5 + num6;
	}
}
