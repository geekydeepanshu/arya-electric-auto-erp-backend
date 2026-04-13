

package com.arya_electric_auto.erp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle_models")
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(name = "model_name")
    private String name;
    private String brand;
    @Column(name = "base_price")
    private Double price;

    public VehicleModel() {}

    // getters/setters
    public void setName(String name) { this.name = name; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setPrice(Double price) { this.price = price; }

    public Long getId() { return id; }
    public String getName() { return name; }
}