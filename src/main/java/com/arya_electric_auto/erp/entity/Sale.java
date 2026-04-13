package com.arya_electric_auto.erp.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 person
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    // 🔗 inquiry
    @ManyToOne
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry;

    // 🔗 employee
    @ManyToOne
    @JoinColumn(name = "handled_by")
    private Employee employee;

    // 🧾 NEW FIELD
    @Column(name = "invoice_number", unique = true)
    private String invoiceNumber;

    // 💰 amounts
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "final_amount")
    private BigDecimal finalAmount;

    // 📅 dates
    @Column(name = "sale_date")
    private LocalDateTime saleDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    // 💳 payment
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_mode")
    private PaymentMode paymentMode;

    // 📊 status
    @Enumerated(EnumType.STRING)
    private SaleStatus status;

    public Sale() {}

    // 🔹 GETTERS
    public Long getId() { return id; }
    public Person getPerson() { return person; }
    public Inquiry getInquiry() { return inquiry; }
    public Employee getEmployee() { return employee; }
    public String getInvoiceNumber() { return invoiceNumber; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public BigDecimal getDiscount() { return discount; }
    public BigDecimal getFinalAmount() { return finalAmount; }
    public LocalDateTime getSaleDate() { return saleDate; }
    public PaymentMode getPaymentMode() { return paymentMode; }
    public SaleStatus getStatus() { return status; }

    // 🔹 SETTERS
    public void setPerson(Person person) { this.person = person; }
    public void setInquiry(Inquiry inquiry) { this.inquiry = inquiry; }
    public void setEmployee(Employee employee) { this.employee = employee; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public void setDiscount(BigDecimal discount) { this.discount = discount; }
    public void setFinalAmount(BigDecimal finalAmount) { this.finalAmount = finalAmount; }
    public void setSaleDate(LocalDateTime saleDate) { this.saleDate = saleDate; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public void setDeletedAt(LocalDateTime deletedAt) { this.deletedAt = deletedAt; }
    public void setPaymentMode(PaymentMode paymentMode) { this.paymentMode = paymentMode; }
    public void setStatus(SaleStatus status) { this.status = status; }
}