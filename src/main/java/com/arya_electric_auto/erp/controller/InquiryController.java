package com.arya_electric_auto.erp.controller;

import com.arya_electric_auto.erp.dto.InquiryAssignRequest;
import com.arya_electric_auto.erp.dto.InquiryCreateRequest;
import com.arya_electric_auto.erp.dto.InquiryResponse;
import com.arya_electric_auto.erp.dto.InquiryStatusUpdateRequest;
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
    public InquiryResponse create(@RequestBody InquiryCreateRequest request) {
        return inquiryService.create(request);
    }

    // ✅ Get all
    @GetMapping
    public List<InquiryResponse> getAll() {
        return inquiryService.getAll()
                .stream()
                .map(inquiryService::toResponse)
                .toList();
    }
    
    // ✅ Get by ID
    @GetMapping("/{id}")
    public InquiryResponse getById(@PathVariable Long id) {
        return inquiryService.getById(id);
    }

    // ✅ Update status
    @PutMapping("/{id}/status")
    public InquiryResponse updateStatus(@PathVariable Long id,
                                @RequestBody InquiryStatusUpdateRequest request) {
        return inquiryService.updateStatus(id, request.getStatus());
    }

    // ✅ Assign employee
    @PutMapping("/{id}/assign")
    public InquiryResponse assign(@PathVariable Long id,
                          @RequestBody InquiryAssignRequest request) {
        return inquiryService.assignEmployee(id, request.getEmployeeId());
    }

    // ✅ Soft delete
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        inquiryService.delete(id);
    }
}
