package com.arya_electric_auto.erp.repository;

import com.arya_electric_auto.erp.entity.CustomerVehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerVehicleRepository extends JpaRepository<CustomerVehicle, Long> {

    List<CustomerVehicle> findByPersonId(Long personId);
}