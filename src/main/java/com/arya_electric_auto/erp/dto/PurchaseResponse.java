package com.arya_electric_auto.erp.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PurchaseResponse {

    private Long id;
    private String vendorName;
    private LocalDateTime purchaseDate;
    private BigDecimal totalAmount;

    public PurchaseResponse() {}

    public PurchaseResponse(Long id, String vendorName,
                            LocalDateTime purchaseDate,
                            BigDecimal totalAmount) {
        this.id = id;
        this.vendorName = vendorName;
        this.purchaseDate = purchaseDate;
        this.totalAmount = totalAmount;
    }

    // getters
    public Long getId() { return id; }
    public String getVendorName() { return vendorName; }
    public LocalDateTime getPurchaseDate() { return purchaseDate; }
    public BigDecimal getTotalAmount() { return totalAmount; }
}