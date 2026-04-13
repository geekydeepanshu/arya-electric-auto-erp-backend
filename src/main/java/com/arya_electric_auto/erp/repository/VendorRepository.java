package com.arya_electric_auto.erp.repository;

import com.arya_electric_auto.erp.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}