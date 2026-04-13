package com.arya_electric_auto.erp.service;

import com.arya_electric_auto.erp.dto.FollowUpResponse;
import com.arya_electric_auto.erp.entity.*;
import com.arya_electric_auto.erp.repository.FollowUpRepository;
import com.arya_electric_auto.erp.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowUpService {

    private final FollowUpRepository followUpRepository;
    private final InquiryService inquiryService;
    private final EmployeeRepository employeeRepository;

    public FollowUpService(FollowUpRepository followUpRepository,
                           InquiryService inquiryService,
                           EmployeeRepository employeeRepository) {
        this.followUpRepository = followUpRepository;
        this.inquiryService = inquiryService;
        this.employeeRepository = employeeRepository;
    }

    // ✅ Create follow-up
    public FollowUp create(Long inquiryId, Long employeeId,
                           String notes, LocalDateTime nextDate, FollowUpStatus status) {

        Inquiry inquiry = inquiryService.getEntityById(inquiryId);

        /*
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
                */

        FollowUp followUp = new FollowUp();
        followUp.setInquiry(inquiry);
        followUp.setEmployeeId(employeeId);
        followUp.setNotes(notes);
        followUp.setStatus(
        	    status != null ? status : FollowUpStatus.PENDING
        	);
        followUp.setNextFollowUpDate(nextDate);
        followUp.setCreatedAt(LocalDateTime.now());

        return followUpRepository.save(followUp);
    }
    
    
    // get all follow-ups 
    public List<FollowUpResponse> getAll() {
        return followUpRepository.findByDeletedAtIsNull()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    // follow up status update 
    public FollowUpResponse updateStatus(Long id, FollowUpStatus status) {

        FollowUp followUp = followUpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FollowUp not found"));

        followUp.setStatus(status);

        return toResponse(followUpRepository.save(followUp));
    }

    // ✅ Get follow-ups by inquiry
    public List<FollowUpResponse> getByInquiry(Long inquiryId) {
        return followUpRepository.findByInquiryIdAndDeletedAtIsNull(inquiryId)
                .stream()
                .map(f -> toResponse(f))
                .collect(Collectors.toList());
    }
    
    
    public List<FollowUpResponse> getTodaysFollowUps() {

        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = LocalDate.now().atTime(23, 59, 59);

        return followUpRepository
        		.findByStatusAndNextFollowUpDateBetweenAndDeletedAtIsNull(
        			    FollowUpStatus.PENDING, start, end
        			)
                .stream()
                .map(f -> toResponse(f))
                .collect(Collectors.toList());
    }
    // get follow up assigned to requested employee
    public List<FollowUpResponse> getByEmployee(Long employeeId) {
        return followUpRepository
                .findByEmployeeIdAndDeletedAtIsNull(employeeId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    
    
    
    public FollowUpResponse toResponse(FollowUp f) {
        return new FollowUpResponse(
                f.getId(),
                f.getInquiry().getId(),
                f.getEmployeeId(),
                f.getNotes(),
                f.getStatus().name(),
                f.getNextFollowUpDate(),
                f.getCreatedAt()
        );
    }
    
}