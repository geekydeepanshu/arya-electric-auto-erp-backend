package com.arya_electric_auto.erp.controller;

import com.arya_electric_auto.erp.dto.service.ServiceHistoryResponse;
import com.arya_electric_auto.erp.service.ServiceHistoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service/history")
public class ServiceHistoryController {

    private final ServiceHistoryService serviceHistoryService;

    public ServiceHistoryController(ServiceHistoryService serviceHistoryService) {
        this.serviceHistoryService = serviceHistoryService;
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ServiceHistoryResponse getVehicleHistory(@PathVariable Long vehicleId) {
        return serviceHistoryService.getVehicleHistory(vehicleId);
    }

    @GetMapping("/person/{personId}")
    public ServiceHistoryResponse getPersonHistory(@PathVariable Long personId) {
        return serviceHistoryService.getPersonHistory(personId);
    }
}
