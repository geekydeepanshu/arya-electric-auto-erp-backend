package com.arya_electric_auto.erp.repository;

import com.arya_electric_auto.erp.entity.JobCardItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobCardItemRepository extends JpaRepository<JobCardItem, Long> {

    // 🔹 Get all items for a job card
    List<JobCardItem> findByJobCardId(Long jobCardId);

    // 🔹 (Optional) Delete all items for a job card
    void deleteByJobCardId(Long jobCardId);
    boolean existsByProductId(Long productId);
    
}