package com.arya_electric_auto.erp.repository;

import com.arya_electric_auto.erp.entity.ServiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, Long> {

    Optional<ServiceRecord> findByJobCardId(Long jobCardId);

    Optional<ServiceRecord> findByInvoiceNumber(String invoiceNumber);
}