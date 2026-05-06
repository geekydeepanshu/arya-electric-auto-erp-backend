package com.arya_electric_auto.erp.repository;

import com.arya_electric_auto.erp.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    // 🔹 Get invoice by job card
    Optional<Invoice> findByJobCardId(Long jobCardId);
}