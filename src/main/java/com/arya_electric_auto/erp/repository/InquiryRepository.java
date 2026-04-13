package com.arya_electric_auto.erp.repository;

import com.arya_electric_auto.erp.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    List<Inquiry> findByDeletedAtIsNull();
}