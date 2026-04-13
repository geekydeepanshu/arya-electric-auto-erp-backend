package com.arya_electric_auto.erp.dto.service;

import java.util.List;

public class ServiceRecordResponse {

    private Long id;
    private String invoiceNumber;
    private Long jobCardId;
    private String customerName;
    private String vehicleName;

    private List<ServiceItemDto> items;

    private Double totalAmount;
    private Double discount;
    private Double finalAmount;

    private String paymentMode;
    private String date;

    public ServiceRecordResponse() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Long getJobCardId() {
		return jobCardId;
	}

	public void setJobCardId(Long jobCardId) {
		this.jobCardId = jobCardId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public List<ServiceItemDto> getItems() {
		return items;
	}

	public void setItems(List<ServiceItemDto> items) {
		this.items = items;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(Double finalAmount) {
		this.finalAmount = finalAmount;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

    // getters & setters (same pattern as above)
    
    
}