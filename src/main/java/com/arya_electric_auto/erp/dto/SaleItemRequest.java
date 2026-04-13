package com.arya_electric_auto.erp.dto;

import java.math.BigDecimal;

public class SaleItemRequest {

    private Long productId;
    private Integer quantity;
    private BigDecimal price;
    private Long inventoryUnitId; // only for serialized

    public SaleItemRequest() {}

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

	public Long getInventoryUnitId() {
		return inventoryUnitId;
	}

	public void setInventoryUnitId(Long inventoryUnitId) {
		this.inventoryUnitId = inventoryUnitId;
	}

    // Getters & Setters
    
    
    
}