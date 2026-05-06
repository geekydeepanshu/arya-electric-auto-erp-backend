package com.arya_electric_auto.erp.dto.service;


public class InvoiceResponse {

    private Long id;
    private Long jobCardId;

    private Double totalAmount;
    private Double discount;
    private Double finalAmount;

    private String status;       // DRAFT / PAID
    private String paymentMode;  // CASH / UPI / CARD

    public InvoiceResponse() {}

    // getters & setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getJobCardId() { return jobCardId; }
    public void setJobCardId(Long jobCardId) { this.jobCardId = jobCardId; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public Double getDiscount() { return discount; }
    public void setDiscount(Double discount) { this.discount = discount; }

    public Double getFinalAmount() { return finalAmount; }
    public void setFinalAmount(Double finalAmount) { this.finalAmount = finalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }
}