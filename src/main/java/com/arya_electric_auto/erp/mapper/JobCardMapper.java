package com.arya_electric_auto.erp.mapper;

import com.arya_electric_auto.erp.dto.service.JobCardResponse;
import com.arya_electric_auto.erp.entity.JobCard;

public class JobCardMapper {

    private JobCardMapper() {}

    public static JobCardResponse toResponse(JobCard jc) {

        if (jc == null) return null;

        JobCardResponse res = new JobCardResponse();

        res.setId(jc.getId());

        if (jc.getPerson() != null) {
            res.setPersonName(jc.getPerson().getFullName());
        }

        if (jc.getVehicle() != null) {
            res.setVehicleName(jc.getVehicle().getVehicleName());
        }

        res.setComplaint(jc.getComplaint());
        res.setStatus(jc.getStatus());

        if (jc.getAssignedTo() != null) {
            res.setAssignedToName(jc.getAssignedTo().getFullName());
        }

        if (jc.getCreatedAt() != null) {
            res.setCreatedAt(jc.getCreatedAt().toString());
        }

        return res;
    }
}