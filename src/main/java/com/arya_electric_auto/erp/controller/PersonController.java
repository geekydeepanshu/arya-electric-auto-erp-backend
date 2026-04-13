package com.arya_electric_auto.erp.controller;

import com.arya_electric_auto.erp.entity.Person;
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
    public Person create(@RequestParam String name,
                         @RequestParam String phone,
                         @RequestParam String city,
                         @RequestParam String address) {

        return personService.createOrGet(name, phone, city, address);
    }
}
