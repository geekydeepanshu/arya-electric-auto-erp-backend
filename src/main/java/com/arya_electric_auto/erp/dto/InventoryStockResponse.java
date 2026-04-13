package com.arya_electric_auto.erp.dto;

public class InventoryStockResponse {

    private Long productId;
    private String productName;
    private Integer quantity;

    public InventoryStockResponse() {}

    public InventoryStockResponse(Long productId, String productName, Integer quantity) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
    }

    // getters
    public Long getProductId() { return productId; }
    public String getProductName() { return productName; }
    public Integer getQuantity() { return quantity; }
}