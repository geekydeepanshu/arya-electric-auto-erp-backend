package com.arya_electric_auto.erp.service;

import com.arya_electric_auto.erp.dto.PersonRequest;
import com.arya_electric_auto.erp.dto.PersonResponse;
import com.arya_electric_auto.erp.entity.Person;
import com.arya_electric_auto.erp.entity.PersonType;
import com.arya_electric_auto.erp.mapper.PersonMapper;
import com.arya_electric_auto.erp.repository.PersonRepository;

import org.springframework.stereotype.Service;

@Service

public class PersonService {

    private final PersonRepository personRepository;
    
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonResponse createOrGet(PersonRequest request) {
        return PersonMapper.toResponse(createOrGetEntity(
                request.getName(),
                request.getPhone(),
                request.getCity(),
                request.getAddress()
        ));
    }

    public Person createOrGetEntity(String name, String phone, String city, String address) {

        return personRepository.findByPhone(phone)
                .orElseGet(() -> {
                	Person person = new Person();
                	person.setFullName(name);
                	person.setPhone(phone);
                	person.setCity(city);
                	person.setAddress(address);
                	person.setType(PersonType.LEAD);

                    return personRepository.save(person);
                });
    }
}
