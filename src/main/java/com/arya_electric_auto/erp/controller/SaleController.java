package com.arya_electric_auto.erp.controller;

import com.arya_electric_auto.erp.dto.ApiResponse;
import com.arya_electric_auto.erp.dto.SaleRequest;
import com.arya_electric_auto.erp.dto.SaleResponse;
import com.arya_electric_auto.erp.service.SaleService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    // 🔥 CREATE SALE (MAIN ENTRY POINT)
    @PostMapping
    public ResponseEntity<?> createSale(@RequestBody SaleRequest request) {

        try {
            Long saleId = saleService.createSale(request);

            return ResponseEntity.ok(
                    new ApiResponse(true, "Sale created successfully", saleId)
            );

        } catch (RuntimeException ex) {

            return ResponseEntity.badRequest().body(
                    new ApiResponse(false, ex.getMessage(), null)
            );
        }
    }
    
    @GetMapping
    public List<SaleResponse> getAllSales() {
        return saleService.getAllSales();
    }
    
    @GetMapping("/{id}/invoice")
    public ResponseEntity<?> getInvoice(@PathVariable Long id) {

        try {
            return ResponseEntity.ok(saleService.getInvoiceBySaleId(id));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}