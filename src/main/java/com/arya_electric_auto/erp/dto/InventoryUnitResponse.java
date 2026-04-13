package com.arya_electric_auto.erp.dto;

import com.arya_electric_auto.erp.entity.InventoryStatus;

public class InventoryUnitResponse {

    private Long id;
    private String serialNumber;
    private InventoryStatus status;

    private Long productId;
    private String productName;

    public InventoryUnitResponse() {}

    public InventoryUnitResponse(Long id,
            String serialNumber,
            InventoryStatus status,
            Long productId,
            String productName) {
this.id = id;
this.serialNumber = serialNumber;
this.status = status;
this.productId = productId;
this.productName = productName;
}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public InventoryStatus getStatus() {
		return status;
	}

	public void setStatus(InventoryStatus status) {
		this.status = status;
	}

    
}