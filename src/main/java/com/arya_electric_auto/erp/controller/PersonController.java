package com.arya_electric_auto.erp.controller;

import com.arya_electric_auto.erp.dto.PersonRequest;
import com.arya_electric_auto.erp.dto.PersonResponse;
import com.arya_electric_auto.erp.dto.PersonSearchResponse;
import com.arya_electric_auto.erp.service.PersonService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/persons")
@CrossOrigin
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    // 🔹 NEW FLOW: SEARCH BY PHONE
    @GetMapping("/search")
    public PersonSearchResponse search(@RequestParam String phone) {
        return service.searchByPhone(phone);
    }

    // 🔹 NEW FLOW: EXPLICIT CREATE
    @PostMapping
    public PersonResponse create(@RequestBody PersonRequest request) {
        return service.create(request);
    }

}