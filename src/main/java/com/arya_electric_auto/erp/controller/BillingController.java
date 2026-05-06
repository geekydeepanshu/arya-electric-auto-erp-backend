package com.arya_electric_auto.erp.controller;

import com.arya_electric_auto.erp.dto.service.InvoiceDiscountRequest;
import com.arya_electric_auto.erp.dto.service.InvoicePayRequest;
import com.arya_electric_auto.erp.dto.service.InvoiceResponse;
import com.arya_electric_auto.erp.dto.service.JobCardItemRequest;
import com.arya_electric_auto.erp.dto.service.JobCardItemResponse;
import com.arya_electric_auto.erp.service.BillingService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class BillingController {

    private final BillingService service;

    public BillingController(BillingService service) {
        this.service = service;
    }

    // =====================================================
    // 🔹 1. ADD ITEM (FROM JOB CARD)
    // =====================================================

    @PostMapping("/job-cards/{jobCardId}/items")
    public JobCardItemResponse addItem(
            @PathVariable Long jobCardId,
            @Valid @RequestBody JobCardItemRequest request
    ) {
        return service.addItem(jobCardId, request);
    }

    // =====================================================
    // 🔹 2. GET ITEMS
    // =====================================================

    @GetMapping("/job-cards/{jobCardId}/items")
    public List<JobCardItemResponse> getItems(@PathVariable Long jobCardId) {
        return service.getItems(jobCardId);
    }

    // =====================================================
    // 🔹 3. GENERATE INVOICE (DRAFT)
    // =====================================================

    @PostMapping("/job-cards/{jobCardId}/invoice")
    public InvoiceResponse generateInvoice(@PathVariable Long jobCardId) {
        return service.generateInvoice(jobCardId);
    }

    // =====================================================
    // 🔹 4. GET INVOICE BY JOB CARD
    // =====================================================

    @GetMapping("/job-cards/{jobCardId}/invoice")
    public InvoiceResponse getInvoiceByJobCard(@PathVariable Long jobCardId) {
        return service.generateInvoice(jobCardId); // reuse (safe for now)
    }

    // =====================================================
    // 🔹 5. APPLY DISCOUNT
    // =====================================================

    @PutMapping("/invoices/{invoiceId}/discount")
    public InvoiceResponse applyDiscount(
            @PathVariable Long invoiceId,
            @Valid @RequestBody InvoiceDiscountRequest request
    ) {
        return service.applyDiscount(invoiceId, request);
    }

    // =====================================================
    // 🔹 6. PAY (FINAL STEP)
    // =====================================================

    @PutMapping("/invoices/{invoiceId}/pay")
    public InvoiceResponse pay(
            @PathVariable Long invoiceId,
            @Valid @RequestBody InvoicePayRequest request
    ) {
        return service.pay(invoiceId, request);
    }
}
