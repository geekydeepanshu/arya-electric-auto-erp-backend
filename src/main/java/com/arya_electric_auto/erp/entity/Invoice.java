package com.arya_electric_auto.erp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔹 One invoice per job card
    @OneToOne
    @JoinColumn(name = "job_card_id", nullable = false, unique = true)
    private JobCard jobCard;

    private Double totalAmount;

    private Double discount;

    private Double finalAmount;

    // DRAFT / PAID
    private String status;

    // CASH / UPI / CARD
    private String paymentMode;

    private LocalDateTime createdAt;

    public Invoice() {
        this.createdAt = LocalDateTime.now();
    }

    // getters & setters

    public Long getId() { return id; }

    public JobCard getJobCard() { return jobCard; }
    public void setJobCard(JobCard jobCard) { this.jobCard = jobCard; }

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

    public LocalDateTime getCreatedAt() { return createdAt; }
}