package com.arya_electric_auto.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arya_electric_auto.erp.entity.VehicleInventory;

public interface VehicleInventoryRepository extends JpaRepository<VehicleInventory, Long> {}