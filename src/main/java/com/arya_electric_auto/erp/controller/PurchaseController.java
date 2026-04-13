package com.arya_electric_auto.erp.controller;

import com.arya_electric_auto.erp.dto.ApiResponse;
import com.arya_electric_auto.erp.dto.PurchaseRequest;
import com.arya_electric_auto.erp.service.PurchaseService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.arya_electric_auto.erp.dto.PurchaseResponse;
import java.util.List;


@RestController
@RequestMapping("/api/purchases")
@CrossOrigin
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    // 🔥 CREATE PURCHASE
    @PostMapping
    public ResponseEntity<?> createPurchase(@RequestBody PurchaseRequest request) {

        try {
            Long purchaseId = purchaseService.createPurchase(request);

            return ResponseEntity.ok().body(
                new ApiResponse(true, "Purchase created successfully", purchaseId)
            );

        } catch (RuntimeException ex) {

            return ResponseEntity.badRequest().body(
                new ApiResponse(false, ex.getMessage(), null)
            );
        }
    }
    

    @GetMapping
    public List<PurchaseResponse> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }
}