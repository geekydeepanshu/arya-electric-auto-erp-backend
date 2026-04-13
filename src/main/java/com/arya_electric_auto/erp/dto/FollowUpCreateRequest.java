package com.arya_electric_auto.erp.dto;

import com.arya_electric_auto.erp.entity.FollowUpStatus;

import java.time.LocalDateTime;

public class FollowUpCreateRequest {

    private Long inquiryId;
    private Long employeeId;
    private String notes;
    private FollowUpStatus status;
    private LocalDateTime nextDate;

    public FollowUpCreateRequest() {}

    public Long getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(Long inquiryId) {
        this.inquiryId = inquiryId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public FollowUpStatus getStatus() {
        return status;
    }

    public void setStatus(FollowUpStatus status) {
        this.status = status;
    }

    public LocalDateTime getNextDate() {
        return nextDate;
    }

    public void setNextDate(LocalDateTime nextDate) {
        this.nextDate = nextDate;
    }
}
