package com.arya_electric_auto.erp.dto.service;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class InvoiceDiscountRequest {

    @NotNull(message = "Discount is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Discount must be >= 0")
    private Double discount;

    public InvoiceDiscountRequest() {}

    public Double getDiscount() { return discount; }
    public void setDiscount(Double discount) { this.discount = discount; }
}
