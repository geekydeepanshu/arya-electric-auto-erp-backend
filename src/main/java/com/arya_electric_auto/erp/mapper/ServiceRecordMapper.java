package com.arya_electric_auto.erp.mapper;

import com.arya_electric_auto.erp.dto.service.ServiceRecordResponse;
import com.arya_electric_auto.erp.dto.service.ServiceItemDto;
import com.arya_electric_auto.erp.entity.ServiceRecord;
import com.arya_electric_auto.erp.entity.ServiceItem;

import java.util.ArrayList;
import java.util.List;

public class ServiceRecordMapper {

    private ServiceRecordMapper() {}

    public static ServiceRecordResponse toResponse(ServiceRecord record, List<ServiceItem> items) {

        ServiceRecordResponse res = new ServiceRecordResponse();

        res.setId(record.getId());
        res.setInvoiceNumber(record.getInvoiceNumber());
        res.setJobCardId(record.getJobCard().getId());

        res.setCustomerName(record.getJobCard().getPerson().getFullName());
        res.setVehicleName(record.getJobCard().getVehicle().getVehicleName());

        res.setTotalAmount(record.getTotalAmount());
        res.setDiscount(record.getDiscount());
        res.setFinalAmount(record.getFinalAmount());
        res.setPaymentMode(record.getPaymentMode());

        if (record.getCreatedAt() != null) {
            res.setDate(record.getCreatedAt().toString());
        }

        // map items
        List<ServiceItemDto> dtoList = new ArrayList<>();

        for (ServiceItem item : items) {
            ServiceItemDto dto = new ServiceItemDto();

            dto.setItemName(item.getItemName());
            dto.setQuantity(item.getQuantity());
            dto.setPrice(item.getPrice());

            if (item.getProduct() != null) {
                dto.setProductId(item.getProduct().getId());
            }

            if (item.getInventoryUnit() != null) {
                dto.setInventoryUnitId(item.getInventoryUnit().getId());
            }

            dtoList.add(dto);
        }

        res.setItems(dtoList);

        return res;
    }
}