package com.arya_electric_auto.erp.controller;

import com.arya_electric_auto.erp.dto.service.CustomerVehicleRequest;
import com.arya_electric_auto.erp.dto.service.CustomerVehicleResponse;
import com.arya_electric_auto.erp.entity.CustomerVehicle;
import com.arya_electric_auto.erp.mapper.CustomerVehicleMapper;
import com.arya_electric_auto.erp.service.CustomerVehicleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service/vehicles")
public class CustomerVehicleController {

    private final CustomerVehicleService service;

    public CustomerVehicleController(CustomerVehicleService service) {
        this.service = service;
    }

    @PostMapping
    public CustomerVehicleResponse create(@RequestBody CustomerVehicleRequest request) {
        CustomerVehicle vehicle = service.create(request);
        return CustomerVehicleMapper.toResponse(vehicle);
    }
}
