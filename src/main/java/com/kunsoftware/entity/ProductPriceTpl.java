package com.kunsoftware.entity;

import java.math.BigDecimal;

public class ProductPriceTpl {
    private Integer id;

    private Integer productResourceId;

    private String name;

    private String priceDescribe;

    private Integer orderValue;

    private String chargeUnit;

    private BigDecimal price;

    private String remark;

    private String selected;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductResourceId() {
        return productResourceId;
    }

    public void setProductResourceId(Integer productResourceId) {
        this.productResourceId = productResourceId;
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

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}
    
    
}