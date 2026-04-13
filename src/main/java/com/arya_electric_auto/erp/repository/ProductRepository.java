package com.arya_electric_auto.erp.repository;

import com.arya_electric_auto.erp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}