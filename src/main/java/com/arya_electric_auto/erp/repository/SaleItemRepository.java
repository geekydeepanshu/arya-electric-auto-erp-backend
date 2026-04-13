package com.arya_electric_auto.erp.repository;

import com.arya_electric_auto.erp.entity.SaleItem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
	List<SaleItem> findBySaleId(Long saleId);
}