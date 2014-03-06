package com.kunsoftware.bean;

import java.math.BigDecimal;

public class FlightChedulePriceRequestBean {

	private Integer flightCheduleId;

    private Integer productPriceTplId;

    private String name;

    private String priceDescribe;

    private Integer orderValue;

    private String chargeUnit;

    private BigDecimal price;

    private String remark;

	public Integer getFlightCheduleId() {
		return flightCheduleId;
	}

	public void setFlightCheduleId(Integer flightCheduleId) {
		this.flightCheduleId = flightCheduleId;
	}

	public Integer getProductPriceTplId() {
		return productPriceTplId;
	}

	public void setProductPriceTplId(Integer productPriceTplId) {
		this.productPriceTplId = productPriceTplId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPriceDescribe() {
		return priceDescribe;
	}

	public void setPriceDescribe(String priceDescribe) {
		this.priceDescribe = priceDescribe;
	}

	public Integer getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}

	public String getChargeUnit() {
		return chargeUnit;
	}

	public void setChargeUnit(String chargeUnit) {
		this.chargeUnit = chargeUnit;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

    
}
