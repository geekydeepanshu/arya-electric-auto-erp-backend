package com.arya_electric_auto.erp.repository;

import com.arya_electric_auto.erp.entity.InventoryUnit;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InventoryUnitRepository extends JpaRepository<InventoryUnit, Long> {
	
	
	@Query("SELECT COUNT(i) FROM InventoryUnit i WHERE i.product.id = :productId AND i.status = 'AVAILABLE'")
	Long countAvailableByProduct(@Param("productId") Long productId);

    boolean existsBySerialNumber(String serialNumber);
    
    List<InventoryUnit> findByStatus(String status);

    List<InventoryUnit> findByProductIdAndStatus(Long productId, String status);
}