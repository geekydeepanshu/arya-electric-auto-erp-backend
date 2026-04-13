package com.arya_electric_auto.erp.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SaleResponse {

    private Long id;
    private String invoiceNumber;
    private LocalDateTime date;

    private String customerName;
    private String employeeName;

    private BigDecimal totalAmount;
    private BigDecimal discount;
    private BigDecimal finalAmount;

    private String paymentMode;
    private String status;

    public SaleResponse() {}

    public SaleResponse(Long id, String invoiceNumber, LocalDateTime date,
                        String customerName, String employeeName,
                        BigDecimal totalAmount, BigDecimal discount,
                        BigDecimal finalAmount,
                        String paymentMode, String status) {
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.date = date;
        this.customerName = customerName;
        this.employeeName = employeeName;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.finalAmount = finalAmount;
        this.paymentMode = paymentMode;
        this.status = status;
    }

    // getters
    public Long getId() { return id; }
    public String getInvoiceNumber() { return invoiceNumber; }
    public LocalDateTime getDate() { return date; }
    public String getCustomerName() { return customerName; }
    public String getEmployeeName() { return employeeName; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public BigDecimal getDiscount() { return discount; }
    public BigDecimal getFinalAmount() { return finalAmount; }
    public String getPaymentMode() { return paymentMode; }
    public String getStatus() { return status; }
}