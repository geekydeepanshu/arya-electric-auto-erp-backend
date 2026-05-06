package com.arya_electric_auto.erp.repository;


import com.arya_electric_auto.erp.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByPhone(String phone);
    Optional<Person> findByPhoneAndDeletedAtIsNull(String phone);
}