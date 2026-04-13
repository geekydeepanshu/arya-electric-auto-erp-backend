package com.arya_electric_auto.erp.controller;

import com.arya_electric_auto.erp.dto.FollowUpResponse;
import com.arya_electric_auto.erp.entity.FollowUp;
import com.arya_electric_auto.erp.entity.FollowUpStatus;
import com.arya_electric_auto.erp.service.FollowUpService;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/followups")
public class FollowUpController {

    private final FollowUpService followUpService;

    public FollowUpController(FollowUpService followUpService) {
        this.followUpService = followUpService;
    }

    // ✅ Create follow-up
    @PostMapping
    public FollowUp create(@RequestParam Long inquiryId,
                           @RequestParam Long employeeId,
                           @RequestParam String notes,
                           @RequestParam(required = false) FollowUpStatus status,
                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                            LocalDateTime nextDate) {

        return followUpService.create(inquiryId, employeeId, notes, nextDate, status);
    }
    
    
    // get all followUps
    @GetMapping
    public List<FollowUpResponse> getAll() {
        return followUpService.getAll();
    }

    // ✅ Get by inquiry
    @GetMapping("/inquiry/{inquiryId}")
    public List<FollowUpResponse> getByInquiry(@PathVariable Long inquiryId) {
        return followUpService.getByInquiry(inquiryId);
    }
    
    // follow up controller
    @GetMapping("/employee/{employeeId}")
    public List<FollowUpResponse> getByEmployee(@PathVariable Long employeeId) {
        return followUpService.getByEmployee(employeeId);
    }
    
    // follow up update 
    @PatchMapping("/{id}/status")
    public FollowUpResponse updateStatus(@PathVariable Long id,
                                         @RequestParam FollowUpStatus status) {
        return followUpService.updateStatus(id, status);
    }

    @GetMapping("/today")
    public List<FollowUpResponse> getTodaysFollowUps() {
        return followUpService.getTodaysFollowUps();
    }
}