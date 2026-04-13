package com.arya_electric_auto.erp.dto.service;

import java.util.List;

public class ServiceRecordRequest {

    private Long jobCardId;
    private Double discount;
    private String paymentMode;
    private List<ServiceItemDto> items;

    public ServiceRecordRequest() {}

    public Long getJobCardId() { return jobCardId; }
    public void setJobCardId(Long jobCardId) { this.jobCardId = jobCardId; }

    public Double getDiscount() { return discount; }
    public void setDiscount(Double discount) { this.discount = discount; }

    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }

    public List<ServiceItemDto> getItems() { return items; }
    public void setItems(List<ServiceItemDto> items) { this.items = items; }
}