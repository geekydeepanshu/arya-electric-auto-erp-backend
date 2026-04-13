package com.arya_electric_auto.erp.mapper;

import com.arya_electric_auto.erp.dto.PersonResponse;
import com.arya_electric_auto.erp.entity.Person;

public class PersonMapper {

    private PersonMapper() {}

    public static PersonResponse toResponse(Person person) {

        PersonResponse response = new PersonResponse();
        response.setId(person.getId());
        response.setFullName(person.getFullName());
        response.setPhone(person.getPhone());
        response.setEmail(person.getEmail());
        response.setAddress(person.getAddress());
        response.setCity(person.getCity());
        response.setType(person.getType() != null ? person.getType().name() : null);

        return response;
    }
}
