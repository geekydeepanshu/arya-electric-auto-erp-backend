package com.arya_electric_auto.erp.controller;

import com.arya_electric_auto.erp.dto.CustomerAssetRequest;
import com.arya_electric_auto.erp.dto.CustomerAssetResponse;
import com.arya_electric_auto.erp.service.CustomerAssetService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
@CrossOrigin
public class CustomerAssetController {

    private final CustomerAssetService service;

    public CustomerAssetController(CustomerAssetService service) {
        this.service = service;
    }

    // 🔹 CREATE ASSET
    @PostMapping
    public CustomerAssetResponse create(@RequestBody CustomerAssetRequest request) {
        return service.create(request);
    }

    // 🔹 GET ASSETS BY PERSON
    @GetMapping("/person/{personId}")
    public List<CustomerAssetResponse> getByPerson(@PathVariable Long personId) {
        return service.getByPerson(personId);
    }
}