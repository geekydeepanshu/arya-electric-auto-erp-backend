package com.arya_electric_auto.erp.dto;

public class InventorySummaryResponse {

    private Long productId;
    private String productName;
    private Boolean isSerialized;

    // for serialized
    private Long availableUnits;

    // for bulk
    private Integer stock;

    public InventorySummaryResponse() {}

    public InventorySummaryResponse(Long productId, String productName,
                                    Boolean isSerialized,
                                    Long availableUnits, Integer stock) {
        this.productId = productId;
        this.productName = productName;
        this.isSerialized = isSerialized;
        this.availableUnits = availableUnits;
        this.stock = stock;
    }

    // getters
    public Long getProductId() { return productId; }
    public String getProductName() { return productName; }
    public Boolean getIsSerialized() { return isSerialized; }
    public Long getAvailableUnits() { return availableUnits; }
    public Integer getStock() { return stock; }
}