package com.arya_electric_auto.erp.service;



import com.arya_electric_auto.erp.dto.EmployeeResponse;
import com.arya_electric_auto.erp.entity.Employee;
import com.arya_electric_auto.erp.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // ✅ Create
    public Employee create(Employee employee) {

        if (employee.getIsActive() == null) {
            employee.setIsActive(true);
        }
        employee.setCreatedAt(LocalDateTime.now());
        return employeeRepository.save(employee);
    }

    // ✅ Get All
    public List<EmployeeResponse> getAll() {
        return employeeRepository.findByDeletedAtIsNull()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // ✅ Get by ID (DTO)
    public EmployeeResponse getById(Long id) {
        Employee e = getEntityById(id);
        return toResponse(e);
    }

    // ✅ Internal (Entity)
    public Employee getEntityById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // ✅ Update
    public Employee update(Long id, Employee updated) {

        Employee e = getEntityById(id);

        e.setFullName(updated.getFullName());
        e.setRole(updated.getRole());
        e.setPhone(updated.getPhone());
        e.setIsActive(updated.getIsActive());

        e.setUpdatedAt(LocalDateTime.now());

        return employeeRepository.save(e);
    }

    // ✅ Deactivate (Soft delete alternative)
    public void deactivate(Long id) {
        Employee e = getEntityById(id);
        e.setIsActive(false);
        e.setUpdatedAt(LocalDateTime.now());
        employeeRepository.save(e);
    }

    // ✅ Soft Delete
    public void delete(Long id) {
        Employee e = getEntityById(id);
        e.setDeletedAt(LocalDateTime.now());
        employeeRepository.save(e);
    }

    // 
    public EmployeeResponse toResponse(Employee e) {
        return new EmployeeResponse(
                e.getId(),
                e.getFullName(),
                e.getRole().name(),
                e.getPhone(),
                e.getIsActive(),
                e.getCreatedAt()
        );
    }
}
