package com.arya_electric_auto.erp.service;

import com.arya_electric_auto.erp.dto.service.JobCardRequest;
import com.arya_electric_auto.erp.entity.*;
import com.arya_electric_auto.erp.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobCardService {

    private final JobCardRepository jobCardRepo;
    private final PersonRepository personRepo;
    private final CustomerVehicleRepository vehicleRepo;
    private final EmployeeRepository employeeRepo;
    private final ServiceRequestRepository requestRepo;

    public JobCardService(JobCardRepository jobCardRepo,
                          PersonRepository personRepo,
                          CustomerVehicleRepository vehicleRepo,
                          EmployeeRepository employeeRepo,
                          ServiceRequestRepository requestRepo) {
        this.jobCardRepo = jobCardRepo;
        this.personRepo = personRepo;
        this.vehicleRepo = vehicleRepo;
        this.employeeRepo = employeeRepo;
        this.requestRepo = requestRepo;
    }

    // 🔹 CREATE
    public JobCard create(JobCardRequest req) {

        Person person = personRepo.findById(req.getPersonId())
                .orElseThrow(() -> new RuntimeException("Person not found"));

        CustomerVehicle vehicle = vehicleRepo.findById(req.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        Employee emp = null;
        if (req.getAssignedTo() != null) {
            emp = employeeRepo.findById(req.getAssignedTo())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
        }

        JobCard jc = new JobCard();
        jc.setPerson(person);
        jc.setVehicle(vehicle);
        jc.setComplaint(req.getComplaint());
        jc.setAssignedTo(emp);
        jc.setStatus("OPEN");
        jc.setCreatedAt(LocalDateTime.now());
        jc.setUpdatedAt(LocalDateTime.now());

        if (req.getServiceRequestId() != null) {
            ServiceRequest sr = requestRepo.findById(req.getServiceRequestId())
                    .orElseThrow(() -> new RuntimeException("Request not found"));

            jc.setServiceRequest(sr);
            sr.setStatus("CONVERTED");
            requestRepo.save(sr);
        }

        return jobCardRepo.save(jc);
    }

    // 🔹 UPDATE STATUS
    public JobCard updateStatus(Long id, String status) {
        JobCard jc = getById(id);
        jc.setStatus(status);
        jc.setUpdatedAt(LocalDateTime.now());
        return jobCardRepo.save(jc);
    }

    // 🔹 GET FILTERED LIST
    public List<JobCard> getJobCards(String status, Long assignedTo) {

        if (status != null && assignedTo != null) {
            return jobCardRepo.findByStatusAndAssignedToId(status, assignedTo);
        }

        if (status != null) {
            return jobCardRepo.findByStatus(status);
        }

        if (assignedTo != null) {
            return jobCardRepo.findByAssignedToId(assignedTo);
        }

        return jobCardRepo.findAll();
    }

    // 🔹 GET BY ID
    public JobCard getById(Long id) {
        return jobCardRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Job card not found"));
    }

    // 🔹 GET BY VEHICLE
    public List<JobCard> getByVehicle(Long vehicleId) {
        return jobCardRepo.findByVehicleId(vehicleId);
    }
}