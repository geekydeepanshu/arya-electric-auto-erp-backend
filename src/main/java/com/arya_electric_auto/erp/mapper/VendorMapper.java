package com.arya_electric_auto.erp.mapper;

import com.arya_electric_auto.erp.dto.VendorResponse;
import com.arya_electric_auto.erp.entity.Vendor;

public class VendorMapper {

    private VendorMapper() {}

    public static VendorResponse toResponse(Vendor vendor) {

        VendorResponse response = new VendorResponse();
        response.setId(vendor.getId());
        response.setName(vendor.getName());
        response.setPhone(vendor.getPhone());
        response.setAddress(vendor.getAddress());

        if (vendor.getCreatedAt() != null) {
            response.setCreatedAt(vendor.getCreatedAt().toString());
        }

        return response;
    }
}
