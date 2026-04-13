package com.arya_electric_auto.erp.repository;

import com.arya_electric_auto.erp.entity.ServiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceItemRepository extends JpaRepository<ServiceItem, Long> {

    List<ServiceItem> findByServiceRecordId(Long serviceRecordId);
}