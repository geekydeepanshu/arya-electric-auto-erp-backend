package com.arya_electric_auto.erp.repository;

import com.arya_electric_auto.erp.entity.InquiryModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryModelRepository extends JpaRepository<InquiryModel, Long> {
	
	List<InquiryModel> findByInquiryId(Long inquiryId);
}