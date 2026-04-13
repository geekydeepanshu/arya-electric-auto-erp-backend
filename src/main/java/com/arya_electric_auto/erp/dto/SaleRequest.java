package com.arya_electric_auto.erp.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class SaleRequest {

    private Long personId;
    private Long inquiryId;
    private Long handledBy;
    private LocalDateTime saleDate;
    private String paymentMode;
    private BigDecimal discount;
    private List<SaleItemRequest> items;

    public SaleRequest() {}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Long getInquiryId() {
		return inquiryId;
	}

	public void setInquiryId(Long inquiryId) {
		this.inquiryId = inquiryId;
	}

	public Long getHandledBy() {
		return handledBy;
	}

	public void setHandledBy(Long handledBy) {
		this.handledBy = handledBy;
	}

	public LocalDateTime getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(LocalDateTime saleDate) {
		this.saleDate = saleDate;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public List<SaleItemRequest> getItems() {
		return items;
	}

	public void setItems(List<SaleItemRequest> items) {
		this.items = items;
	}

    // Getters & Setters
    
    
}