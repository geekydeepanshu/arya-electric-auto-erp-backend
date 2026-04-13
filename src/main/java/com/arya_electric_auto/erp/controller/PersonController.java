package com.arya_electric_auto.erp.controller;

import com.arya_electric_auto.erp.dto.PersonRequest;
import com.arya_electric_auto.erp.dto.PersonResponse;
import com.arya_electric_auto.erp.service.PersonService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/persons")

public class PersonController {

    private final PersonService personService;
    
    
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public PersonResponse create(@RequestBody PersonRequest request) {
        return personService.createOrGet(request);
    }
}
