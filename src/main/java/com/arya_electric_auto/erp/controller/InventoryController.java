package com.arya_electric_auto.erp.controller;


import com.arya_electric_auto.erp.service.InventoryService;
import com.arya_electric_auto.erp.dto.InventoryUnitResponse;
import com.arya_electric_auto.erp.dto.InventoryStockResponse;
import com.arya_electric_auto.erp.dto.InventorySummaryResponse;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
@GetMapping("/units")
public List<InventoryUnitResponse> getAvailableUnits() {
    return inventoryService.getAvailableUnits();
}

@GetMapping("/units/product/{productId}")
public List<InventoryUnitResponse> getUnitsByProduct(@PathVariable Long productId) {
    return inventoryService.getAvailableUnitsByProduct(productId);
}

@GetMapping("/stock")
public List<InventoryStockResponse> getStock() {
    return inventoryService.getAllStock();
}



@GetMapping("/summary")
public List<InventorySummaryResponse> getSummary() {
    return inventoryService.getInventorySummary();
}
}