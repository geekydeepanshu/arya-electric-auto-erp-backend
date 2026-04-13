package com.arya_electric_auto.erp.controller;

import com.arya_electric_auto.erp.dto.service.JobCardRequest;
import com.arya_electric_auto.erp.dto.service.JobCardResponse;
import com.arya_electric_auto.erp.dto.service.StatusUpdateRequest;
import com.arya_electric_auto.erp.entity.JobCard;
import com.arya_electric_auto.erp.mapper.JobCardMapper;
import com.arya_electric_auto.erp.service.JobCardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service/job-cards")
public class JobCardController {

    private final JobCardService service;

    public JobCardController(JobCardService service) {
        this.service = service;
    }

    // 🔹 CREATE
    @PostMapping
    public JobCardResponse create(@RequestBody JobCardRequest request) {
        JobCard jc = service.create(request);
        return JobCardMapper.toResponse(jc);
    }

    // 🔹 UPDATE STATUS
    @PutMapping("/{id}/status")
    public JobCardResponse updateStatus(@PathVariable Long id,
                                        @RequestBody StatusUpdateRequest request) {
        JobCard jc = service.updateStatus(id, request.getStatus());
        return JobCardMapper.toResponse(jc);
    }

    // 🔹 GET ALL (FILTERED)
    @GetMapping
    public List<JobCardResponse> getAll(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long assignedTo
    ) {

        List<JobCard> list = service.getJobCards(status, assignedTo);

        return list.stream()
                .map(JobCardMapper::toResponse)
                .toList();
    }

    // 🔹 GET BY ID
    @GetMapping("/{id}")
    public JobCardResponse getById(@PathVariable Long id) {
        JobCard jc = service.getById(id);
        return JobCardMapper.toResponse(jc);
    }

    // 🔹 GET BY VEHICLE
    @GetMapping("/vehicle/{vehicleId}")
    public List<JobCardResponse> getByVehicle(@PathVariable Long vehicleId) {

        List<JobCard> list = service.getByVehicle(vehicleId);

        return list.stream()
                .map(JobCardMapper::toResponse)
                .toList();
    }
}