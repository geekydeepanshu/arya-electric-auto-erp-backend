package com.arya_electric_auto.erp.controller;

import com.arya_electric_auto.erp.dto.InquiryResponse;
import com.arya_electric_auto.erp.entity.Inquiry;
import com.arya_electric_auto.erp.entity.InquirySource;
import com.arya_electric_auto.erp.entity.InquiryStatus;
import com.arya_electric_auto.erp.service.InquiryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inquiries")
public class InquiryController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    // ✅ Create Inquiry
    @PostMapping
    public Inquiry create(@RequestParam String name,
                          @RequestParam String phone,
                          @RequestParam String city,
                          @RequestParam String address,
                          @RequestParam InquirySource source,
                          @RequestParam List<Long> modelIds,
                          @RequestParam(required = false) String notes) {

        return inquiryService.create(name, phone, city,address, source, notes, modelIds);
    }

    // ✅ Get all
    @GetMapping
    public List<InquiryResponse> getAll() {

        return inquiryService.getAll()
                .stream()
                .map((Inquiry inquiry) -> inquiryService.toResponse(inquiry))
                .toList();
    }
    
    // ✅ Get by ID
    @GetMapping("/{id}")
    public InquiryResponse getById(@PathVariable Long id) {
        return inquiryService.getById(id);
    }

    // ✅ Update status
    @PutMapping("/{id}/status")
    public Inquiry updateStatus(@PathVariable Long id,
                                @RequestParam InquiryStatus status) {
        return inquiryService.updateStatus(id, status);
    }

    // ✅ Assign employee
    @PutMapping("/{id}/assign")
    public Inquiry assign(@PathVariable Long id,
                          @RequestParam Long employeeId) {
        return inquiryService.assignEmployee(id, employeeId);
    }

    // ✅ Soft delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        inquiryService.delete(id);
    }
}