package com.arya_electric_auto.erp.dto.service;


import jakarta.validation.constraints.NotBlank;

public class InvoicePayRequest {

    @NotBlank(message = "Payment mode is required")
    private String paymentMode; // CASH / UPI / CARD

    public InvoicePayRequest() {}

    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }
}