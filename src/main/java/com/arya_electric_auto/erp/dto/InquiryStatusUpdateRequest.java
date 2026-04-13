package com.arya_electric_auto.erp.dto;

import com.arya_electric_auto.erp.entity.InquiryStatus;

public class InquiryStatusUpdateRequest {

    private InquiryStatus status;

    public InquiryStatusUpdateRequest() {}

    public InquiryStatus getStatus() {
        return status;
    }

    public void setStatus(InquiryStatus status) {
        this.status = status;
    }
}
