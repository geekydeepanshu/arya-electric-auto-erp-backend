package com.arya_electric_auto.erp.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PurchaseRequest {

    private Long vendorId;
    private LocalDateTime purchaseDate;
    private List<PurchaseItemRequest> items;

    // Constructors
    public PurchaseRequest() {}

    public PurchaseRequest(Long vendorId, LocalDateTime purchaseDate, List<PurchaseItemRequest> items) {
        this.vendorId = vendorId;
        this.purchaseDate = purchaseDate;
        this.items = items;
    }

    // Getters & Setters
    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public List<PurchaseItemRequest> getItems() {
        return items;
    }

    public void setItems(List<PurchaseItemRequest> items) {
        this.items = items;
    }
}