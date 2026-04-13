package com.arya_electric_auto.erp.dto;

import java.math.BigDecimal;
import java.util.List;

public class PurchaseItemRequest {

    private Long productId;
    private Integer quantity;
    private BigDecimal price;
    private List<String> serialNumbers;

    // Constructors
    public PurchaseItemRequest() {}

    public PurchaseItemRequest(Long productId, Integer quantity, BigDecimal price, List<String> serialNumbers) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.serialNumbers = serialNumbers;
    }

    // Getters & Setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<String> getSerialNumbers() {
        return serialNumbers;
    }

    public void setSerialNumbers(List<String> serialNumbers) {
        this.serialNumbers = serialNumbers;
    }
}