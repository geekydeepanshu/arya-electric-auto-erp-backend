package com.arya_electric_auto.erp.entity;

import java.math.BigDecimal;

public class ProductRequest {
    public String name;
    public ProductType type;
    public String brand;
    public BigDecimal price;
    public Boolean isSerialized;
    
    
    
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ProductType getType() {
		return type;
	}
	public void setType(ProductType type) {
		this.type = type;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Boolean getIsSerialized() {
		return isSerialized;
	}
	public void setIsSerialized(Boolean isSerialized) {
		this.isSerialized = isSerialized;
	}
    
    
    
}