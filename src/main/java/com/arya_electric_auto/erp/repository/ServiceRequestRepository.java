package com.arya_electric_auto.erp.repository;

import com.arya_electric_auto.erp.entity.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {

    List<ServiceRequest> findByStatus(String status);

    List<ServiceRequest> findByPersonId(Long personId);
}