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
    @JoinColumn(name = "model_id", nullable = false)
    private VehicleModel model;

    private LocalDateTime createdAt;

    public InquiryModel() {}

    // setters
    public void setInquiry(Inquiry inquiry) { this.inquiry = inquiry; }
    public void setModel(VehicleModel model) { this.model = model; }
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

	public VehicleModel getModel() {
		return model;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
    
    
}