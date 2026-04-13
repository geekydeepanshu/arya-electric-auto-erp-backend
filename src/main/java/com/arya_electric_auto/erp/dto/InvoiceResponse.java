package com.arya_electric_auto.erp.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class InvoiceResponse {

    // 🔹 Header
    private String invoiceNumber;
    private LocalDateTime date;

    private String customerName;
    private String customerPhone;

    // 🔹 Items
    private List<InvoiceItemResponse> items;

    // 🔹 Summary
    private BigDecimal totalAmount;
    private BigDecimal discount;
    private BigDecimal finalAmount;

    private String paymentMode;

    public InvoiceResponse() {}

    public InvoiceResponse(String invoiceNumber,
                           LocalDateTime date,
                           String customerName,
                           String customerPhone,
                           List<InvoiceItemResponse> items,
                           BigDecimal totalAmount,
                           BigDecimal discount,
                           BigDecimal finalAmount,
                           String paymentMode) {

        this.invoiceNumber = invoiceNumber;
        this.date = date;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.items = items;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.finalAmount = finalAmount;
        this.paymentMode = paymentMode;
    }

    // getters
    public String getInvoiceNumber() { return invoiceNumber; }
    public LocalDateTime getDate() { return date; }
    public String getCustomerName() { return customerName; }
    public String getCustomerPhone() { return customerPhone; }
    public List<InvoiceItemResponse> getItems() { return items; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public BigDecimal getDiscount() { return discount; }
    public BigDecimal getFinalAmount() { return finalAmount; }
    public String getPaymentMode() { return paymentMode; }
}