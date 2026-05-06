package com.arya_electric_auto.erp.repository;

import com.arya_electric_auto.erp.entity.JobCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobCardRepository extends JpaRepository<JobCard, Long> {

    List<JobCard> findByStatus(String status);

    List<JobCard> findByAssignedToId(Long employeeId);

    List<JobCard> findByStatusAndAssignedToId(String status, Long employeeId);

    List<JobCard> findByVehicleId(Long vehicleId);
    
    List<JobCard> findByAssetId(Long assetId);
}