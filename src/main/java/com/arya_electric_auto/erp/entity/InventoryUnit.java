package com.arya_electric_auto.erp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_units")
public class InventoryUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "serial_number", unique = true)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private InventoryStatus status;// AVAILABLE, SOLD, RESERVED

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public InventoryUnit() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	

	

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public InventoryStatus getStatus() {
		return status;
	}

	public void setStatus(InventoryStatus status) {
		this.status = status;
	}

    // Getters & Setters
    
    
}