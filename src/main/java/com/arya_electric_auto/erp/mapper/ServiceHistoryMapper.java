package com.arya_electric_auto.erp.mapper;

import com.arya_electric_auto.erp.dto.service.ServiceHistoryItem;
import com.arya_electric_auto.erp.dto.service.ServiceHistoryResponse;
import com.arya_electric_auto.erp.entity.JobCard;
import com.arya_electric_auto.erp.entity.ServiceRecord;

import java.util.List;

public class ServiceHistoryMapper {

    private ServiceHistoryMapper() {}

    public static ServiceHistoryItem toItem(JobCard jobCard, ServiceRecord record) {

        ServiceHistoryItem item = new ServiceHistoryItem();
        item.setJobCardId(jobCard.getId());
        item.setComplaint(jobCard.getComplaint());
        item.setStatus(jobCard.getStatus());
        item.setFinalAmount(record.getFinalAmount());

        if (jobCard.getVehicle() != null) {
            item.setVehicleName(jobCard.getVehicle().getVehicleName());
        }

        if (jobCard.getCreatedAt() != null) {
            item.setDate(jobCard.getCreatedAt().toString());
        }

        return item;
    }

    public static ServiceHistoryResponse toResponse(List<ServiceHistoryItem> items) {
        ServiceHistoryResponse response = new ServiceHistoryResponse();
        response.setItems(items);
        return response;
    }
}
