package com.arya_electric_auto.erp.mapper;

import com.arya_electric_auto.erp.dto.service.CustomerVehicleResponse;
import com.arya_electric_auto.erp.entity.CustomerVehicle;

public class CustomerVehicleMapper {

    private CustomerVehicleMapper() {}

    public static CustomerVehicleResponse toResponse(CustomerVehicle vehicle) {

        if (vehicle == null) return null;

        CustomerVehicleResponse response = new CustomerVehicleResponse();
        response.setId(vehicle.getId());
        response.setVehicleName(vehicle.getVehicleName());
        response.setVehicleType(vehicle.getVehicleType());
        response.setSerialNumber(vehicle.getSerialNumber());
        response.setIsFromSale(vehicle.getIsFromSale());

        if (vehicle.getPerson() != null) {
            response.setPersonName(vehicle.getPerson().getFullName());
        }

        if (vehicle.getPurchaseDate() != null) {
            response.setPurchaseDate(vehicle.getPurchaseDate().toString());
        }

        return response;
    }
}
