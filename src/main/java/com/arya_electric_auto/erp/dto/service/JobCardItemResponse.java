package com.arya_electric_auto.erp.dto.service;

import java.util.List;

public class JobCardItemResponse {

    private Long id;
    private Long jobCardId;

    private String type;
    private String description;

    private Long productId;

    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
    private List<Long> unitIds;

    public JobCardItemResponse() {}

    // getters & setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getJobCardId() { return jobCardId; }
    public void setJobCardId(Long jobCardId) { this.jobCardId = jobCardId; }

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

    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }

	public List<Long> getUnitIds() {
		return unitIds;
	}

	public void setUnitIds(List<Long> unitIds) {
		this.unitIds = unitIds;
	}
    
    
}