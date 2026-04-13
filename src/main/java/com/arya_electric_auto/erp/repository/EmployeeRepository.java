package com.arya_electric_auto.erp.repository;


import com.arya_electric_auto.erp.entity.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByDeletedAtIsNull();
}
