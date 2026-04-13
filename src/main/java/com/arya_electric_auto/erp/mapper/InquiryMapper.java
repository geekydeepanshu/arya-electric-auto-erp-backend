package com.arya_electric_auto.erp.mapper;

import com.arya_electric_auto.erp.dto.InquiryResponse;
import com.arya_electric_auto.erp.entity.Inquiry;

import java.util.List;

public class InquiryMapper {

    private InquiryMapper() {}

    public static InquiryResponse toResponse(Inquiry inquiry, List<String> models) {
        return new InquiryResponse(
                inquiry.getId(),
                inquiry.getPerson().getFullName(),
                inquiry.getPerson().getPhone(),
                inquiry.getPerson().getAddress(),
                inquiry.getPerson().getCity(),
                inquiry.getSource().name(),
                inquiry.getStatus().name(),
                inquiry.getInquiryDate(),
                models
        );
    }
}
