package com.arya_electric_auto.erp.dto.service;

public class ServiceItemDto {

    private Long productId;
    private String itemName;
    private Integer quantity;
    private Double price;
    private Long inventoryUnitId;

    public ServiceItemDto() {}

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Long getInventoryUnitId() { return inventoryUnitId; }
    public void setInventoryUnitId(Long inventoryUnitId) { this.inventoryUnitId = inventoryUnitId; }
}