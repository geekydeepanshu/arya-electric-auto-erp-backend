package com.arya_electric_auto.erp.dto.service;

import java.util.List;

import jakarta.validation.constraints.*;

public class JobCardItemRequest {

    @NotBlank(message = "Type is required")
    private String type; // SERVICE / PART

    @NotBlank(message = "Description is required")
    private String description;

    private Long productId; // required for PART

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull(message = "Unit price is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Unit price must be >= 0")
    private Double unitPrice;
    
    private List<Long> unitIds; // ONLY for serialized products
    

    public JobCardItemRequest() {}

    // getters & setters

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }

	public List<Long> getUnitIds() {
		return unitIds;
	}

	public void setUnitIds(List<Long> unitIds) {
		this.unitIds = unitIds;
	}
    
    
    
    
}