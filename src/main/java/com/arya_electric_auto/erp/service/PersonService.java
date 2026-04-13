package com.arya_electric_auto.erp.service;

import com.arya_electric_auto.erp.entity.Person;
import com.arya_electric_auto.erp.entity.PersonType;
import com.arya_electric_auto.erp.repository.PersonRepository;

import org.springframework.stereotype.Service;

@Service

public class PersonService {

    private final PersonRepository personRepository;
    
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person createOrGet(String name, String phone, String city, String address) {

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
