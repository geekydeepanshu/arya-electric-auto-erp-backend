package com.arya_electric_auto.erp.service;

import com.arya_electric_auto.erp.dto.EmployeeRequest;
import com.arya_electric_auto.erp.dto.EmployeeResponse;
import com.arya_electric_auto.erp.entity.Employee;
import com.arya_electric_auto.erp.mapper.EmployeeMapper;
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
    public EmployeeResponse create(EmployeeRequest request) {
        Employee employee = EmployeeMapper.toEntity(request);

        if (employee.getIsActive() == null) {
            employee.setIsActive(true);
        }
        employee.setCreatedAt(LocalDateTime.now());
        return EmployeeMapper.toResponse(employeeRepository.save(employee));
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
    public EmployeeResponse update(Long id, EmployeeRequest request) {

        Employee e = getEntityById(id);

        EmployeeMapper.apply(request, e);

        e.setUpdatedAt(LocalDateTime.now());

        return EmployeeMapper.toResponse(employeeRepository.save(e));
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
        return EmployeeMapper.toResponse(e);
    }
}
