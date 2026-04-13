package com.arya_electric_auto.erp.dto;

import java.math.BigDecimal;

public class ProductResponse {

    private Long id;
    private String name;
    private String type;
    private String brand;
    private BigDecimal price;
    private Boolean isSerialized;

    public ProductResponse() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
