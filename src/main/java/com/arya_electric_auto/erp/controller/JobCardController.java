package com.arya_electric_auto.erp.controller;

import com.arya_electric_auto.erp.dto.service.JobCardRequest;
import com.arya_electric_auto.erp.dto.service.JobCardResponse;
import com.arya_electric_auto.erp.service.JobCardService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-cards")
@CrossOrigin
public class JobCardController {

    private final JobCardService service;

    public JobCardController(JobCardService service) {
        this.service = service;
    }

    // 🔹 CREATE
    @PostMapping
    public JobCardResponse create(@RequestBody JobCardRequest request) {
        return service.create(request);
    }

    // 🔹 UPDATE STATUS
    @PutMapping("/{id}/status")
    public JobCardResponse updateStatus(@PathVariable Long id,
                                        @RequestParam String status) {
        return service.updateStatus(id, status);
    }

    // 🔹 LIST
    @GetMapping
    public List<JobCardResponse> getJobCards(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long assignedTo
    ) {
        return service.getJobCards(status, assignedTo);
    }

    // 🔹 GET BY ID
    @GetMapping("/{id}")
    public JobCardResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // 🔹 GET BY ASSET
    @GetMapping("/asset/{assetId}")
    public List<JobCardResponse> getByAsset(@PathVariable Long assetId) {
        return service.getByAsset(assetId);
    }
}