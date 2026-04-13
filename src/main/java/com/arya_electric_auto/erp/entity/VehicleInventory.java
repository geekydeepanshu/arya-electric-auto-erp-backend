package com.arya_electric_auto.erp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle_inventory")
public class VehicleInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 model
    @ManyToOne
    @JoinColumn(name = "model_id")
    private VehicleModel model;

    private String chassisNumber;

    @Enumerated(EnumType.STRING)
    private InventoryStatus status;

    public VehicleInventory() {}

    // setters
    public void setModel(VehicleModel model) { this.model = model; }
    public void setChassisNumber(String chassisNumber) { this.chassisNumber = chassisNumber; }
    public void setStatus(InventoryStatus status) { this.status = status; }

    // getters
    public Long getId() { return id; }
    public InventoryStatus getStatus() { return status; }
}