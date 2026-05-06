package com.arya_electric_auto.erp.repository;

import com.arya_electric_auto.erp.entity.JobCardComplaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobCardComplaintRepository extends JpaRepository<JobCardComplaint, Long> {

    List<JobCardComplaint> findByJobCardId(Long jobCardId);
}