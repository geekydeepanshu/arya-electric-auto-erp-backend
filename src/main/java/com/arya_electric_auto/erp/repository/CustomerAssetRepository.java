package com.arya_electric_auto.erp.repository;

import com.arya_electric_auto.erp.entity.CustomerAsset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerAssetRepository extends JpaRepository<CustomerAsset, Long> {

    List<CustomerAsset> findByPersonIdAndDeletedAtIsNull(Long personId);

    Optional<CustomerAsset> findByIdentifierAndDeletedAtIsNull(String identifier);
}