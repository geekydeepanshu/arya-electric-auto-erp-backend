package com.arya_electric_auto.erp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "service_items")
public class ServiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_record_id", nullable = false)
    private ServiceRecord serviceRecord;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "inventory_unit_id")
    private InventoryUnit inventoryUnit;

    public ServiceItem() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public ServiceRecord getServiceRecord() { return serviceRecord; }
    public void setServiceRecord(ServiceRecord serviceRecord) { this.serviceRecord = serviceRecord; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public InventoryUnit getInventoryUnit() { return inventoryUnit; }
    public void setInventoryUnit(InventoryUnit inventoryUnit) { this.inventoryUnit = inventoryUnit; }
}