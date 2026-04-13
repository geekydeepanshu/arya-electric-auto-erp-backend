package com.arya_electric_auto.erp.repository;

import com.arya_electric_auto.erp.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}