package com.arya_electric_auto.erp.repository;

import com.arya_electric_auto.erp.entity.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {
}