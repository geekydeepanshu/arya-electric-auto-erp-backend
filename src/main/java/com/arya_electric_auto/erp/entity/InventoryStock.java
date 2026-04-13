package com.arya_electric_auto.erp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory_stock")
public class InventoryStock {

    @Id
    private Long productId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    public InventoryStock() {}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

    // Getters & Setters
    
    
}