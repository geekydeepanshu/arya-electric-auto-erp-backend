package com.arya_electric_auto.erp.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sale_items")
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 sale
    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    // 🔗 product
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // 🧾 NEW FIELD (IMPORTANT)
    @Column(name = "item_name")
    private String itemName;

    // 🔢 quantity
    private Integer quantity;

    // 💰 price
    private BigDecimal price;

    // 🔗 serialized reference (nullable)
    @Column(name = "inventory_unit_id")
    private Long inventoryUnitId;

    public SaleItem() {}

    // 🔹 GETTERS
    public Long getId() { return id; }
    public Sale getSale() { return sale; }
    public Product getProduct() { return product; }
    public String getItemName() { return itemName; }
    public Integer getQuantity() { return quantity; }
    public BigDecimal getPrice() { return price; }
    public Long getInventoryUnitId() { return inventoryUnitId; }

    // 🔹 SETTERS
    public void setSale(Sale sale) { this.sale = sale; }
    public void setProduct(Product product) { this.product = product; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setInventoryUnitId(Long inventoryUnitId) { this.inventoryUnitId = inventoryUnitId; }
}