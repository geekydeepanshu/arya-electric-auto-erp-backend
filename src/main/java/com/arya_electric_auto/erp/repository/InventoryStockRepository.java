package com.arya_electric_auto.erp.repository;

import com.arya_electric_auto.erp.entity.InventoryStock;
import com.arya_electric_auto.erp.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryStockRepository extends JpaRepository<InventoryStock, Long> {

    Optional<InventoryStock> findByProductId(Long productId);
    Optional<InventoryStock> findByProduct(Product product);
    
}