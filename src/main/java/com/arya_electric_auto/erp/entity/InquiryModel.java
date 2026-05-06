package com.arya_electric_auto.erp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inquiry_models")
public class InquiryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 inquiry
    @ManyToOne
    @JoinColumn(name = "inquiry_id", nullable = false)
    private Inquiry inquiry;

    // 🔗 model
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDateTime createdAt;

    public InquiryModel() {}

    // setters
    public void setInquiry(Inquiry inquiry) { this.inquiry = inquiry; }
    
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Inquiry getInquiry() {
		return inquiry;
	}

	

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
    
    
}