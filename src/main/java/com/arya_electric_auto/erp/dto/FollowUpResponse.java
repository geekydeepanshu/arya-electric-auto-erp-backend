package com.arya_electric_auto.erp.dto;

import java.time.LocalDateTime;

public class FollowUpResponse {

    private Long id;
    private Long inquiryId;
    private Long employeeId;
    private String notes;
    private String status;
    private LocalDateTime nextFollowUpDate;
    private LocalDateTime createdAt;

    public FollowUpResponse(Long id,
                            Long inquiryId,
                            Long employeeId,
                            String notes,
                            String status,
                            LocalDateTime nextFollowUpDate,
                            LocalDateTime createdAt) {
        this.id = id;
        this.inquiryId = inquiryId;
        this.employeeId = employeeId;
        this.notes = notes;
        this.status = status;
        this.nextFollowUpDate = nextFollowUpDate;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public Long getInquiryId() { return inquiryId; }
    public Long getEmployeeId() { return employeeId; }
    public String getNotes() { return notes; }
    public String getStatus() { return status; }
    public LocalDateTime getNextFollowUpDate() { return nextFollowUpDate; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}