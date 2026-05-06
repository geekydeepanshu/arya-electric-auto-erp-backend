package com.arya_electric_auto.erp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "job_card_items")
public class JobCardItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔹 Link to JobCard
    @ManyToOne
    @JoinColumn(name = "job_card_id", nullable = false)
    private JobCard jobCard;

    // 🔹 SERVICE / PART
    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String description;

    // 🔹 Only for PART
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    
    @ManyToMany
    @JoinTable(
        name = "job_card_item_units",
        joinColumns = @JoinColumn(name = "job_card_item_id"),
        inverseJoinColumns = @JoinColumn(name = "inventory_unit_id")
    )
    private List<InventoryUnit> units;

    private Integer quantity;

    private Double unitPrice;

    private Double totalPrice;

    private LocalDateTime createdAt;

    public JobCardItem() {
        this.createdAt = LocalDateTime.now();
    }

    // getters & setters

    public Long getId() { return id; }

    public JobCard getJobCard() { return jobCard; }
    public void setJobCard(JobCard jobCard) { this.jobCard = jobCard; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }

    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }

    public LocalDateTime getCreatedAt() { return createdAt; }

	public List<InventoryUnit> getUnits() {
		return units;
	}

	public void setUnits(List<InventoryUnit> units) {
		this.units = units;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
    
    
}