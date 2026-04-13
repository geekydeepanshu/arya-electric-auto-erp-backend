package com.arya_electric_auto.erp.controller;

import com.arya_electric_auto.erp.dto.VendorRequest;
import com.arya_electric_auto.erp.dto.VendorResponse;
import com.arya_electric_auto.erp.service.VendorService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
@CrossOrigin
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<?> create(@RequestBody VendorRequest request) {
        try {
            VendorResponse vendor = vendorService.create(request);
            return ResponseEntity.ok(vendor);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // ✅ GET ALL
    @GetMapping
    public List<VendorResponse> getAll() {
        return vendorService.getAll();
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public VendorResponse getById(@PathVariable Long id) {
        return vendorService.getById(id);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            vendorService.delete(id);
            return ResponseEntity.ok("Vendor deleted successfully");
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
