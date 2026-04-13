package com.arya_electric_auto.erp.controller;

import com.arya_electric_auto.erp.dto.EmployeeRequest;
import com.arya_electric_auto.erp.dto.EmployeeResponse;
import com.arya_electric_auto.erp.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // ✅ Create Employee
    @PostMapping
    public EmployeeResponse create(@RequestBody EmployeeRequest request) {
        return employeeService.create(request);
    }

    // ✅ Get All Employees
    @GetMapping
    public List<EmployeeResponse> getAll() {
        return employeeService.getAll();
    }

    // ✅ Get Employee by ID
    @GetMapping("/{id}")
    public EmployeeResponse getById(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    // ✅ Update Employee
    @PutMapping("/{id}")
    public EmployeeResponse update(@PathVariable Long id,
                           @RequestBody EmployeeRequest request) {
        return employeeService.update(id, request);
    }

    // ✅ Deactivate Employee
    @PatchMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        employeeService.deactivate(id);
    }

    // ✅ Soft Delete Employee
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }
}
