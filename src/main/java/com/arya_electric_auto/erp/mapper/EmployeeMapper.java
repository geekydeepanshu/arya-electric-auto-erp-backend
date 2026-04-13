package com.arya_electric_auto.erp.mapper;

import com.arya_electric_auto.erp.dto.EmployeeRequest;
import com.arya_electric_auto.erp.dto.EmployeeResponse;
import com.arya_electric_auto.erp.entity.Employee;
import com.arya_electric_auto.erp.entity.Role;

public class EmployeeMapper {

    private EmployeeMapper() {}

    public static Employee toEntity(EmployeeRequest request) {
        Employee employee = new Employee();
        apply(request, employee);
        return employee;
    }

    public static void apply(EmployeeRequest request, Employee employee) {
        employee.setFullName(request.getFullName());
        employee.setPhone(request.getPhone());
        employee.setIsActive(request.getIsActive());

        if (request.getRole() != null) {
            employee.setRole(Role.valueOf(request.getRole()));
        }
    }

    public static EmployeeResponse toResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getFullName(),
                employee.getRole() != null ? employee.getRole().name() : null,
                employee.getPhone(),
                employee.getIsActive(),
                employee.getCreatedAt()
        );
    }
}
