package com.arya_electric_auto.erp.mapper;

import com.arya_electric_auto.erp.dto.ProductResponse;
import com.arya_electric_auto.erp.entity.Product;

public class ProductMapper {

    private ProductMapper() {}

    public static ProductResponse toResponse(Product product) {

        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setBrand(product.getBrand());
        response.setPrice(product.getPrice());
        response.setIsSerialized(product.getIsSerialized());

        if (product.getType() != null) {
            response.setType(product.getType().name());
        }

        return response;
    }
}
