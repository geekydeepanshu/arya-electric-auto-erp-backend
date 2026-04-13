package com.arya_electric_auto.erp.service;

import com.arya_electric_auto.erp.dto.service.CustomerVehicleRequest;
import com.arya_electric_auto.erp.entity.CustomerVehicle;
import com.arya_electric_auto.erp.entity.Person;
import com.arya_electric_auto.erp.repository.CustomerVehicleRepository;
import com.arya_electric_auto.erp.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerVehicleService {

    private final CustomerVehicleRepository vehicleRepo;
    private final PersonRepository personRepo;

    public CustomerVehicleService(CustomerVehicleRepository vehicleRepo,
                                  PersonRepository personRepo) {
        this.vehicleRepo = vehicleRepo;
        this.personRepo = personRepo;
    }

    public CustomerVehicle create(CustomerVehicleRequest req) {

        Person person = personRepo.findById(req.getPersonId())
                .orElseThrow(() -> new RuntimeException("Person not found"));

        CustomerVehicle v = new CustomerVehicle();
        v.setPerson(person);
        v.setVehicleName(req.getVehicleName());
        v.setVehicleType(req.getVehicleType());
        v.setSerialNumber(req.getSerialNumber());
        v.setIsFromSale(req.getIsFromSale());
        v.setPurchaseDate(req.getPurchaseDate());
        v.setNotes(req.getNotes());
        v.setCreatedAt(LocalDateTime.now());

        return vehicleRepo.save(v);
    }
}