package com.arya_electric_auto.erp.service;

import com.arya_electric_auto.erp.dto.service.JobCardRequest;
import com.arya_electric_auto.erp.dto.service.JobCardResponse;
import com.arya_electric_auto.erp.entity.*;
import com.arya_electric_auto.erp.mapper.JobCardMapper;
import com.arya_electric_auto.erp.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobCardService {

    private final JobCardRepository jobCardRepo;
    private final CustomerAssetRepository assetRepo;
    private final EmployeeRepository employeeRepo;
    private final ServiceRequestRepository requestRepo;
    private final JobCardComplaintRepository complaintRepo;

    public JobCardService(JobCardRepository jobCardRepo,
                          CustomerAssetRepository assetRepo,
                          EmployeeRepository employeeRepo,
                          ServiceRequestRepository requestRepo,
                          JobCardComplaintRepository complaintRepo) {
        this.jobCardRepo = jobCardRepo;
        this.assetRepo = assetRepo;
        this.employeeRepo = employeeRepo;
        this.requestRepo = requestRepo;
        this.complaintRepo = complaintRepo;
    }

    // 🔹 CREATE
    public JobCardResponse create(JobCardRequest req) {

        CustomerAsset asset = assetRepo.findById(req.getAssetId())
                .orElseThrow(() -> new RuntimeException("Asset not found"));

        Employee emp = null;
        if (req.getAssignedTo() != null) {
            emp = employeeRepo.findById(req.getAssignedTo())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
        }

        JobCard jc = new JobCard();
        jc.setAsset(asset);
        jc.setPerson(asset.getPerson()); // 🔥 derive
        jc.setAssignedTo(emp);
        jc.setStatus("OPEN");
        jc.setCreatedAt(LocalDateTime.now());
        jc.setUpdatedAt(LocalDateTime.now());

        // optional summary
        if (req.getComplaints() != null && !req.getComplaints().isEmpty()) {
            jc.setComplaint(req.getComplaints().size() + " issues reported");
        }

        // 🔹 SERVICE REQUEST LINK
        if (req.getServiceRequestId() != null) {
            ServiceRequest sr = requestRepo.findById(req.getServiceRequestId())
                    .orElseThrow(() -> new RuntimeException("Request not found"));

            jc.setServiceRequest(sr);
            sr.setStatus("CONVERTED");
            requestRepo.save(sr);
        }

        JobCard saved = jobCardRepo.save(jc);

        // 🔹 SAVE COMPLAINTS
        if (req.getComplaints() != null) {
            for (String c : req.getComplaints()) {

                JobCardComplaint comp = new JobCardComplaint();
                comp.setJobCard(saved);
                comp.setDescription(c);
                comp.setStatus("PENDING");

                complaintRepo.save(comp);
            }
        }

        return buildResponse(saved);
    }

    // 🔹 UPDATE STATUS
    public JobCardResponse updateStatus(Long id, String status) {
        JobCard jc = getEntity(id);
        jc.setStatus(status);
        jc.setUpdatedAt(LocalDateTime.now());
        return buildResponse(jobCardRepo.save(jc));
    }

    // 🔹 GET LIST
    public List<JobCardResponse> getJobCards(String status, Long assignedTo) {

        List<JobCard> list;

        if (status != null && assignedTo != null) {
            list = jobCardRepo.findByStatusAndAssignedToId(status, assignedTo);
        } else if (status != null) {
            list = jobCardRepo.findByStatus(status);
        } else if (assignedTo != null) {
            list = jobCardRepo.findByAssignedToId(assignedTo);
        } else {
            list = jobCardRepo.findAll();
        }

        List<JobCardResponse> res = new ArrayList<>();

        for (JobCard jc : list) {
            res.add(buildResponse(jc));
        }

        return res;
    }

    // 🔹 GET BY ID
    public JobCardResponse getById(Long id) {
        return buildResponse(getEntity(id));
    }

    // 🔹 GET BY ASSET
    public List<JobCardResponse> getByAsset(Long assetId) {

        List<JobCard> list = jobCardRepo.findByAssetId(assetId);

        List<JobCardResponse> res = new ArrayList<>();

        for (JobCard jc : list) {
            res.add(buildResponse(jc));
        }

        return res;
    }

    // 🔹 INTERNAL ENTITY FETCH
    private JobCard getEntity(Long id) {
    	System.out.println("Job Card Id is"+id);
        return jobCardRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Job card not found"));
    }

    // 🔹 BUILD RESPONSE (IMPORTANT)
    private JobCardResponse buildResponse(JobCard jc) {

        JobCardResponse res = JobCardMapper.toResponse(jc);

        // 🔥 ADD COMPLAINTS
        List<JobCardComplaint> complaints = complaintRepo.findByJobCardId(jc.getId());

        List<String> list = new ArrayList<>();

        for (JobCardComplaint c : complaints) {
            list.add(c.getDescription());
        }

        res.setComplaints(list);

        return res;
    }
}