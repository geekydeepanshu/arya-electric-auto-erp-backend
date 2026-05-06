package com.arya_electric_auto.erp.service;

import com.arya_electric_auto.erp.dto.PersonRequest;
import com.arya_electric_auto.erp.dto.PersonResponse;
import com.arya_electric_auto.erp.dto.PersonSearchResponse;
import com.arya_electric_auto.erp.entity.Person;
import com.arya_electric_auto.erp.entity.PersonType;
import com.arya_electric_auto.erp.mapper.PersonMapper;
import com.arya_electric_auto.erp.repository.PersonRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // 🔹 NEW: SEARCH FLOW (for UI first step)
    public PersonSearchResponse searchByPhone(String phone) {

        Optional<Person> optional = personRepository.findByPhone(phone);

        PersonSearchResponse res = new PersonSearchResponse();

        if (optional.isPresent()) {
            Person p = optional.get();
            res.setExists(true);
            res.setId(p.getId());
            res.setName(p.getFullName());
            res.setPhone(p.getPhone());
        } else {
            res.setExists(false);
        }

        return res;
    }

    // 🔹 EXISTING FLOW (kept intact)
    public PersonResponse createOrGet(PersonRequest request) {
        return PersonMapper.toResponse(createOrGetEntity(
                request.getName(),
                request.getPhone(),
                request.getCity(),
                request.getAddress()
        ));
    }

    // 🔹 CORE METHOD (USED BY BOTH FLOWS)
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

    // 🔹 NEW: EXPLICIT CREATE (used after search step)
    public PersonResponse create(PersonRequest request) {

        // prevent duplicate (important for new flow)
        Optional<Person> existing = personRepository.findByPhone(request.getPhone());

        if (existing.isPresent()) {
            return PersonMapper.toResponse(existing.get());
        }

        Person person = new Person();
        person.setFullName(request.getName());
        person.setPhone(request.getPhone());
        person.setCity(request.getCity());
        person.setAddress(request.getAddress());
        person.setType(PersonType.LEAD);

        return PersonMapper.toResponse(personRepository.save(person));
    }
}