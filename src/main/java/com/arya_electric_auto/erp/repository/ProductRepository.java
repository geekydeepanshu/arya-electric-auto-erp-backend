package com.arya_electric_auto.erp.repository;

import com.arya_electric_auto.erp.entity.Product;
import com.arya_electric_auto.erp.entity.ProductType;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Modifying
	@Query("UPDATE Product p SET p.deletedAt = CURRENT_TIMESTAMP WHERE p.id = :id")
	void softDelete(Long id);
	
	List<Product> findByTypeAndDeletedAtIsNull(ProductType type);
	List<Product> findByDeletedAtIsNull();
	boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);
	
	
}