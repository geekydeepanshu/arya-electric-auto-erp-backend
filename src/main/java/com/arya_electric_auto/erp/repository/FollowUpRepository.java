package com.arya_electric_auto.erp.repository;

import com.arya_electric_auto.erp.entity.FollowUp;
import com.arya_electric_auto.erp.entity.FollowUpStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FollowUpRepository extends JpaRepository<FollowUp, Long> {

	List<FollowUp> findByDeletedAtIsNull();
	List<FollowUp> findByEmployeeIdAndDeletedAtIsNull(Long employeeId);
	
    List<FollowUp> findByInquiryIdAndDeletedAtIsNull(Long inquiryId);
    
    List<FollowUp> findByStatusAndNextFollowUpDateBetweenAndDeletedAtIsNull(
            FollowUpStatus status,
            LocalDateTime start,
            LocalDateTime end
    );

    // 🔹 Optional (if you want simpler inquiry fetch without deleted filter)
    List<FollowUp> findByInquiryId(Long inquiryId);
    
}